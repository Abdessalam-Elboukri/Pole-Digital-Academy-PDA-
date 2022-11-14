package com.example.pole_digital_academy.Servlets;


import com.example.pole_digital_academy.Entities.Activity;
import com.example.pole_digital_academy.Entities.Participant;
import com.example.pole_digital_academy.Entities.Participation;
import com.example.pole_digital_academy.Entities.User;
import com.example.pole_digital_academy.Services.ServicesFactory;
import com.example.pole_digital_academy.utils.Constants;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;




@WebServlet(name = "participationServlet", urlPatterns ={ "/participation","/participation/manage"})
public class ParticipationServlet extends HttpServlet {
    public String $url ;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestUrl=req.getRequestURI().replace("/Pole_Digital_Academy_war_exploded","");
        switch (requestUrl){
            case "/participation":
                try {
                    List<Activity>  activities =ServicesFactory.getActivityService().getAll();
                    req.setAttribute(Constants.KEY_ACTIVITIES_LIST,activities);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                req.getRequestDispatcher("/WEB-INF/participation/list.jsp").forward(req,resp);
                break;

            case "/participation/manage":
                try {
                    Activity activity=ServicesFactory.getActivityService().findById(Integer.parseInt(req.getParameter("id")));
                    //list of participants that participate in the current activity
                    List<Participant> participants_in =ServicesFactory.getParticipantService().getAllPNotInSelActivity(Integer.parseInt((req.getParameter("id"))));
                    //list of participants that not participate in the current activity
                    List<Participant> participants_out =ServicesFactory.getParticipantService().getAllPInSelActivity(Integer.parseInt((req.getParameter("id"))));
                    req.setAttribute(Constants.KEY_ACTIVITY_TO_MANAGE,activity);
                    req.setAttribute(Constants.KEY_PARTICIPANTS_IN_LIST,participants_in);
                    req.setAttribute(Constants.KEY_PARTICIPANTS_OUT_LIST,participants_out);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                req.getRequestDispatcher("/WEB-INF/participation/manage.jsp").forward(req,resp);
                break;

            default:
                resp.getWriter().write("no route mapping");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestUrl=req.getRequestURI().replace("/Pole_Digital_Academy_war_exploded","");

        switch(requestUrl){
            case "/participation/manage":
                try {
                    manageParticipation(req,resp);
                    getServletContext().getRequestDispatcher("/WEB-INF/participation/manage.jsp").forward (req, resp);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            default:
                resp.getWriter().write("no route mapping yet");
        };

    }

    private void manageParticipation(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        if(req.getParameter("submit") !=null){
            String[] participants = req.getParameterValues("participant");
            System.out.println(participants.length);
            for ( String participant : participants){
                System.out.println(participant);
            }
            Activity activity =ServicesFactory.getActivityService().findById(Integer.parseInt(req.getParameter(Activity.KEY_TITLE)));
            for (String participant : participants) {
                Participation participation = new Participation();
                Participant p = ServicesFactory.getParticipantService().findById(Integer.parseInt(participant));
                participation.setParticipant(p);
                participation.setActivity(activity);
                participation.setParticipationType(Participation.ParticipationTypeEnum.SIGNED_IN);
                //ServicesFactory.getParticipationService().insert(participation);
            }
        }

    }




    }

