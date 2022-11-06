package com.example.pole_digital_academy.Servlets;

import com.example.pole_digital_academy.Entities.Activity;
import com.example.pole_digital_academy.Entities.Responsible;
import com.example.pole_digital_academy.Services.ServicesFactory;
import com.example.pole_digital_academy.Services.activity.IActivityService;
import com.example.pole_digital_academy.utils.Constants;
import com.example.pole_digital_academy.utils.EntityManagerFactory;
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

        switch(req.getRequestURI()){
            case "/activities":
                req.getRequestDispatcher("/WEB-INF/activities/list.jsp").forward(req,resp);
                break;
            case "/activities/add":
                //TODO call the responsibles service instead here
                List<Responsible> responsibles=EntityManagerFactory.getEntityManager().createQuery("from Responsible").getResultList();
                req.setAttribute(Constants.KEY_RESPONSIBLES,responsibles);
                req.getRequestDispatcher("/WEB-INF/activities/add.jsp").forward(req,resp);
                //resp.getWriter().write("ddd");
                break;
                case "/activities/edit":
                req.getRequestDispatcher("/WEB-INF/activities/edit.jsp").forward(req,resp);
                break;
            default:
                resp.getWriter().write("no route mapping");
        };
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        switch(req.getRequestURI()){
            case "/activities/add":
                //TODO:: handle add activity form data
                // validate then send to business layer to handle data
                processAddActivity(req,resp);

                break;
            case "/activities/edit":
                //TODO:: handle update form data
                // validate then send to business layer to handle data
                break;
            default:
                resp.getWriter().write("no route mapping yet");
        };

    }
    private void processAddActivity(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Activity activity=new Activity();
        activity.setTitle(req.getParameter(Activity.KEY_TITLE));
        try {
            activity.setActivityType(Activity.ActivityTypeEnum.values()[Integer.parseInt(req.getParameter(Activity.KEY_ACTIVITY_TYPE))]);
        }catch (Exception exception){
            exception.printStackTrace();
        }
        activity.setTitle("te");
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
        //TODO:: move this logic to the ResponsibleDAO
        Responsible responsible= EntityManagerFactory.getEntityManager().find(Responsible.class,Integer.parseInt(req.getParameter(Activity.KEY_RESPONSIBLE_ID)));
        activity.setResponsible(responsible);
        List<String> validationErrors=new ArrayList<>();
        if(InputValidator.isActivityValid(activity,validationErrors) ){
            //if the data is valid we add the new  activity and redirect the user to the activities list page
            ServicesFactory.getActivityService().insert(activity);
            resp.getWriter().write("activity inserted successfully!");
            System.out.println("activity added");
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
