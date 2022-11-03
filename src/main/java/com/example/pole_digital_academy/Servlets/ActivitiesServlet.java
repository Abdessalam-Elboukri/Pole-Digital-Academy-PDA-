package com.example.pole_digital_academy.Servlets;

import com.example.pole_digital_academy.Entities.Activity;
import com.example.pole_digital_academy.Entities.Exercice;
import com.example.pole_digital_academy.Services.ServicesFactory;
import com.example.pole_digital_academy.Services.activity.ActivityServiceImp;
import com.example.pole_digital_academy.Services.activity.IActivityService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "activitiesServlet",urlPatterns = {"/activities","/activities/add","/activities/delete","/activities/edit"})
public class ActivitiesServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        switch(req.getRequestURI()){
            case "/activities":
                req.getRequestDispatcher("WEB-INF/activities/list.jsp").forward(req,resp);
                break;
            case "/activities/add":
                req.getRequestDispatcher("WEB-INF/activities/add.jsp").forward(req,resp);
                break;
            case "/activities/edit":
                req.getRequestDispatcher("WEB-INF/activities/edit.jsp").forward(req,resp);
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
    private void processAddActivity(HttpServletRequest req, HttpServletResponse resp){
        Activity activity=new Activity();
        try {
            activity.setActivityType(Activity.ActivityTypeEnum.values()[Integer.parseInt(req.getParameter(Activity.KEY_ACTIVITY_TYPE))]);
        }catch (Exception exception){
            exception.printStackTrace();
        }
        activity.setTitle("te");
        activity.setStatus(Activity.ActivityStatusEnum.ACTIVE);
        activity.setStartDate(LocalDate.now());
        activity.setEndDate(LocalDate.now().plusDays(12));
        activity.setDescription("some activity description");

        Exercice e1=new Exercice();
        e1.setActivity(activity);
        e1.setYear(2022);
        e1.setStartDate(LocalDate.now().plusDays(10));
        e1.setEndDate(LocalDate.now().plusDays(12));
        e1.setTitle("exercice 1");

        Exercice e2=new Exercice();
        e2.setActivity(activity);
        e2.setYear(2022);
        e2.setStartDate(LocalDate.now().plusDays(10));
        e2.setEndDate(LocalDate.now().plusDays(12));
        e2.setTitle("exercice 2");
        List<String> errors=new ArrayList<>();
        if(InputValidator.isActivityValid(activity,errors) ){
            IActivityService as=ServicesFactory.getActivityService();
            ;
        }else
        {

        };
    }


}
