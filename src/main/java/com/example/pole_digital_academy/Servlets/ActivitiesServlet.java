package com.example.pole_digital_academy.Servlets;

import com.example.pole_digital_academy.Entities.Activity;
import com.example.pole_digital_academy.Entities.Responsible;
import com.example.pole_digital_academy.Services.ServicesFactory;
import com.example.pole_digital_academy.Services.activity.IActivityService;
import com.example.pole_digital_academy.utils.Constants;
import com.example.pole_digital_academy.utils.EntityManagerFactory;
import com.example.pole_digital_academy.utils.InputValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "activitiesServlet",urlPatterns = {"/activities","/activities/add","/activities/delete","/activities/edit","/"})
public class ActivitiesServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestUrl=req.getRequestURI().replace(req.getContextPath(),"");
        //TODO call responsible service instead
        List<Responsible> responsibles = null;
        try {
            responsibles = ServicesFactory.getResponsibleService().getNonOccupedResponsibles();
            req.setAttribute(Constants.KEY_RESPONSIBLES,responsibles);
        } catch (Exception e) {
            e.printStackTrace();
        }


        switch(requestUrl) {
            case"/":
            case "/activities":
                List<Activity> activities = new ArrayList<>();
                boolean searchCriteriaPresent;
                try {
                    searchCriteriaPresent = false;
                    LocalDate startDate = null;
                    String startDateStr = req.getParameter(Constants.KEY_ACTIVITY_SEARCH_START_DATE);
                    if (startDateStr != null && !startDateStr.isEmpty()) {
                        startDate = LocalDate.parse(startDateStr);
                        searchCriteriaPresent = true;
                    }
                    LocalDate endDate = null;
                    String endDateStr = req.getParameter(Constants.KEY_ACTIVITY_SEARCH_END_DATE);
                    if (endDateStr != null && !endDateStr.isEmpty()) {
                        endDate = LocalDate.parse(endDateStr);
                        searchCriteriaPresent = true;
                    }

                    Activity.ActivityTypeEnum activityType = null;
                    String activityTypeStr = req.getParameter(Constants.KEY_ACTIVITY_SEARCH_ACTIVITY_TYPE);
                    if (activityTypeStr != null && !activityTypeStr.isEmpty()) {
                        searchCriteriaPresent = true;
                        activityType = Activity.ActivityTypeEnum.values()[Integer.parseInt(activityTypeStr)];
                    }
                    activities = ServicesFactory.getActivityService().search(startDate, endDate, activityType);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new IOException(e.getMessage());
                }
                req.setAttribute(Constants.KEY_IS_SEARCHING, searchCriteriaPresent);
                req.setAttribute(Constants.KEY_ACTIVITIES_LIST, activities);
                req.getRequestDispatcher("/WEB-INF/activities/list.jsp").forward(req, resp);
                break;
            case "/activities/add":

                try {
                    //List<Responsible> responsibles = ServicesFactory.getResponsibleService().getNonOccupedResponsibles();
                    req.setAttribute(Constants.KEY_RESPONSIBLES, responsibles);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                req.getRequestDispatcher("/WEB-INF/activities/add.jsp").forward(req, resp);

                break;
            case "/activities/edit":
                Activity activityToEdit = null;
                try {

                    activityToEdit = ServicesFactory.getActivityService().findById(Integer.parseInt(req.getParameter("id")));
                    System.out.println("found activity has a name of " + activityToEdit.getTitle());
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("error while looking for activity !");
                }
                req.setAttribute(Constants.KEY_ACTIVITY_TO_EDIT, activityToEdit);
                req.getRequestDispatcher("/WEB-INF/activities/edit.jsp").forward(req, resp);
                break;
            case "/activities/delete":
                IActivityService activityService = ServicesFactory.getActivityService();
                String message = "";
                try {
                    ServicesFactory.getActivityService().delete(Integer.parseInt(req.getParameter("id")));
                    message = "activity deleted successfully!";
                } catch (Exception e) {
                    e.printStackTrace();
                    message = "Error deleting service";
                } finally {
                    resp.sendRedirect(req.getContextPath() + "/activities?message=" + message);
                }

                break;
            default:
                resp.getWriter().write("no route mapping");
        };
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        String requestUrl=req.getRequestURI().replace(req.getContextPath(),"");
        switch(requestUrl){
            case "/activities/add":
                  try {
                    processAddActivity(req,resp);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "/activities/edit":
                try {
                    processEditActivity(req,resp);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                resp.getWriter().write("no route mapping yet");
        };

    }

    private void processEditActivity(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Activity activity=ServicesFactory.getActivityService().findById(Integer.parseInt(req.getParameter(Activity.KEY_ID)));

        activity.setTitle(req.getParameter(Activity.KEY_TITLE));
        try {
            activity.setActivityType(Activity.ActivityTypeEnum.values()[Integer.parseInt(req.getParameter(Activity.KEY_ACTIVITY_TYPE))]);
        }catch (Exception exception){
            exception.printStackTrace();
        }
        try{
            String status=req.getParameter(Activity.KEY_ACTIVITY_STATUS);
            if(status==null)
                throw new IllegalArgumentException("status is not set");
            activity.setStatus(Activity.ActivityStatusEnum.values()[Integer.parseInt(status)]);
        }catch (Exception ex){
            ex.printStackTrace();
            activity.setStatus(Activity.ActivityStatusEnum.ACTIVE);
        }

        try {
            activity.setStartDate(LocalDate.parse(req.getParameter(Activity.KEY_START_DATE)));

        }catch (DateTimeParseException e){
            e.printStackTrace();
            activity.setStartDate(LocalDate.now());
        }
        try {
            activity.setEndDate(LocalDate.parse(req.getParameter(Activity.KEY_END_DATE)));

        }catch (DateTimeParseException e){
            e.printStackTrace();
            activity.setEndDate(LocalDate.now());
        }
        activity.setDescription(req.getParameter(Activity.KEY_DESCRIPTION));
        try {
            activity.setStatus(Activity.ActivityStatusEnum.values()[Integer.parseInt(req.getParameter(Activity.KEY_ACTIVITY_STATUS))]);
        }catch (Exception e){
            e.printStackTrace();
        }
        String responisibleId=req.getParameter(Activity.KEY_RESPONSIBLE_ID);
        Responsible responsible=null;
        if(responisibleId!=null )
            responsible=ServicesFactory.getResponsibleService().findById(Integer.parseInt(responisibleId));
        activity.setResponsible(responsible);
        List<String> validationErrors=new ArrayList<>();
        if(InputValidator.isActivityValid(activity,validationErrors) ){
            //if the data is valid we add the new  activity and redirect the user to the activities list page
            resp.getWriter().write("activity edited successfully!");
            System.out.println("activity updated");
            String message="";
            try {
                ServicesFactory.getActivityService().update(activity);
                message="activity edited successfully!";
            }catch (Exception e){
                e.printStackTrace();
                message="Error updating activity";
            }
            finally {
                resp.sendRedirect(req.getContextPath()+"/activities?message="+message);
            }

        }else
        {
            //if the data is not valid we redirect the user to activity creation form with validation errors
            System.out.println("failed to update activity");
            List<Responsible> responsibles=ServicesFactory.getResponsibleService().getNonOccupedResponsibles();
            req.setAttribute(Constants.KEY_RESPONSIBLES,responsibles);
            req.setAttribute(Constants.KEY_VALIDATION_ERRORS,validationErrors);
            Activity activityToEdit=null;
            try {

                activityToEdit=ServicesFactory.getActivityService().findById(Integer.parseInt(req.getParameter(Activity.KEY_ID)));
                System.out.println("found activity has a name of "+activityToEdit.getTitle());
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("error while looking for activity !");
            }
            req.setAttribute(Constants.KEY_ACTIVITY_TO_EDIT,activityToEdit);

            req.getRequestDispatcher("/WEB-INF/activities/edit.jsp").forward(req,resp);
        };
    }

    private void processAddActivity(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Activity activity=new Activity();
        activity.setTitle(req.getParameter(Activity.KEY_TITLE));
        try {
            activity.setActivityType(Activity.ActivityTypeEnum.values()[Integer.parseInt(req.getParameter(Activity.KEY_ACTIVITY_TYPE))]);
        }catch (Exception exception){
            exception.printStackTrace();
        }
        activity.setStatus(Activity.ActivityStatusEnum.ACTIVE);

        try {
            activity.setStartDate(LocalDate.parse(req.getParameter(Activity.KEY_START_DATE)));

        }catch (DateTimeParseException e){
            e.printStackTrace();
            activity.setStartDate(LocalDate.now());
        }
        try {
            activity.setEndDate(LocalDate.parse(req.getParameter(Activity.KEY_END_DATE)));

        }catch (DateTimeParseException e){
            e.printStackTrace();
            activity.setEndDate(LocalDate.now());
        }
        activity.setDescription(req.getParameter(Activity.KEY_DESCRIPTION));
            activity.setStatus(Activity.ActivityStatusEnum.ACTIVE);
        //TODO:: move this logic to the ResponsibleService
        String responisibleId=req.getParameter(Activity.KEY_RESPONSIBLE_ID);
        Responsible responsible=null;
        if(responisibleId!=null )
            responsible=ServicesFactory.getResponsibleService().findById(Integer.parseInt(responisibleId));

        activity.setResponsible(responsible);
        List<String> validationErrors=new ArrayList<>();
        if(InputValidator.isActivityValid(activity,validationErrors) ){
            //if the data is valid we add the new  activity and redirect the user to the activities list page
            String message="";
            try {
                ServicesFactory.getActivityService().insert(activity);
                message="activity inserted successfully!";
            }catch (Exception e){
                e.printStackTrace();
                message="Error adding activity";
            }
            finally {
                resp.sendRedirect(req.getContextPath()+"/activities?message="+message);
            }


        }else
        {
            //if the data is not valid we redirect the user to activity creation form with validation errors
            System.out.println("failed to add activity");
            List<Responsible> responsibles=ServicesFactory.getResponsibleService().getNonOccupedResponsibles();
            req.setAttribute(Constants.KEY_RESPONSIBLES,responsibles);
            req.setAttribute(Constants.KEY_VALIDATION_ERRORS,validationErrors);
                  req.getRequestDispatcher("/WEB-INF/activities/add.jsp").forward(req,resp);
        };
    }


}
