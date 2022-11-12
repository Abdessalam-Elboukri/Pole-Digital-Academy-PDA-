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

@WebServlet(name = "activitiesServlet",urlPatterns = {"/activities","/activities/add","/activities/delete","/activities/edit"})
public class ActivitiesServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        String requestUrl=req.getRequestURI().replace("/Pole_Digital_Academy_war_exploded","");
        //TODO call responsible service instead
        List<Responsible> responsibles=EntityManagerFactory.getEntityManager().createQuery("from Responsible").getResultList();
        req.setAttribute(Constants.KEY_RESPONSIBLES,responsibles);

        switch(requestUrl){
            case "/activities":
                List<Activity> activities;
                try {
                    activities=ServicesFactory.getActivityService().getAll();
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new IOException(e.getMessage());
                }

                req.setAttribute(Constants.KEY_ACTIVITIES_LIST,activities);
                req.getRequestDispatcher("/WEB-INF/activities/list.jsp").forward(req,resp);
                break;
            case "/activities/add":
                    req.getRequestDispatcher("/WEB-INF/activities/add.jsp").forward(req,resp);
                break;
                case "/activities/edit":
                    Activity activityToEdit=null;
                    try {

                        activityToEdit=ServicesFactory.getActivityService().findById(Integer.parseInt(req.getParameter("id")));
                        System.out.println("found activity has a name of "+activityToEdit.getTitle());
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("error while looking for activity !");
                    }
                    req.setAttribute(Constants.KEY_ACTIVITY_TO_EDIT,activityToEdit);
                    req.getRequestDispatcher("/WEB-INF/activities/edit.jsp").forward(req,resp);
                break;
                case "/activities/delete":
                    IActivityService activityService=ServicesFactory.getActivityService();
                    String message="";
                    try {
                            ServicesFactory.getActivityService().delete(Integer.parseInt(req.getParameter("id")));
                            message="activity deleted successfully!";
                    } catch (Exception e) {
                        e.printStackTrace();
                            message="Error deleting service";
                    }finally {
                        resp.sendRedirect("/activities?message="+message);
                    }

                break;
            default:
                resp.getWriter().write("no route mapping");
        };
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        String requestUrl=req.getRequestURI().replace("/Pole_Digital_Academy_war_exploded","");
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
        //TODO:: move this logic to the ResponsibleService
        Responsible responsible= EntityManagerFactory.getEntityManager().find(Responsible.class,Integer.parseInt(req.getParameter(Activity.KEY_RESPONSIBLE_ID)));
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
                resp.sendRedirect("/activities?message="+message);
            }

        }else
        {
            //if the data is not valid we redirect the user to activity creation form with validation errors
            System.out.println("failed to update activity");
            List<Responsible> responsibles=EntityManagerFactory.getEntityManager().createQuery("from Responsible").getResultList();
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
        try {
            activity.setStatus(Activity.ActivityStatusEnum.values()[Integer.parseInt(req.getParameter(Activity.KEY_ACTIVITY_STATUS))]);
        }catch (Exception e){
            e.printStackTrace();
        }
        //TODO:: move this logic to the ResponsibleService
        Responsible responsible= EntityManagerFactory.getEntityManager().find(Responsible.class,Integer.parseInt(req.getParameter(Activity.KEY_RESPONSIBLE_ID)));
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
                resp.sendRedirect("/activities?message="+message);
            }


        }else
        {
            //if the data is not valid we redirect the user to activity creation form with validation errors
            System.out.println("failed to add activity");
            List<Responsible> responsibles=EntityManagerFactory.getEntityManager().createQuery("from Responsible").getResultList();
            req.setAttribute(Constants.KEY_RESPONSIBLES,responsibles);
            req.setAttribute(Constants.KEY_VALIDATION_ERRORS,validationErrors);
                  req.getRequestDispatcher("/WEB-INF/activities/add.jsp").forward(req,resp);
        };
    }


}
