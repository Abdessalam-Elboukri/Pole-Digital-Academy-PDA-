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
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;


@WebServlet(name = "participationServlet", urlPatterns ={ "/participation","/participation/manage","/participation/manage/add_to_activity"})
public class ParticipationServlet extends HttpServlet {
    public String $url;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestUrl = req.getRequestURI().replace("/Pole_Digital_Academy_war_exploded", "");
        switch (requestUrl) {
            case "/participation":
                try {
                    List<Activity> activities = ServicesFactory.getActivityService().getAll();
                    req.setAttribute(Constants.KEY_ACTIVITIES_LIST, activities);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                req.getRequestDispatcher("/WEB-INF/participation/list.jsp").forward(req, resp);
                break;

            case "/participation/manage":
                try {
                    Activity activity = ServicesFactory.getActivityService().findById(Integer.parseInt(req.getParameter("id")));
                    //list of participants that participate in the current activity
                    List<Participant> participants_out = ServicesFactory.getParticipantService().getAllPNotInSelActivity(Integer.parseInt((req.getParameter("id"))));
                    //list of participants that not participate in the current activity
                    List<Participant> participants_in = ServicesFactory.getParticipantService().getAllPInSelActivity(Integer.parseInt((req.getParameter("id"))));
                    req.setAttribute(Constants.KEY_ACTIVITY_TO_MANAGE, activity);
                    req.setAttribute(Constants.KEY_PARTICIPANTS_IN_LIST, participants_in);
                    req.setAttribute(Constants.KEY_PARTICIPANTS_OUT_LIST, participants_out);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                req.getRequestDispatcher("/WEB-INF/participation/manage.jsp").forward(req, resp);
                break;

            case "/participation/manage/add_to_activity":
                String uri = "/participation/manage?id=" + req.getParameter("id");
                try {
                    Activity activity = ServicesFactory.getActivityService().findById(Integer.parseInt(req.getParameter("id")));
                    Participant participant = ServicesFactory.getParticipantService().findById(Integer.parseInt(req.getParameter("participant")));
                    Participation participation = new Participation();
                    participation.setParticipant(participant);
                    participation.setActivity(activity);
                    activity.getParticipation().add(participation);
                    participation.setParticipationType(Participation.ParticipationTypeEnum.SIGNED_IN);
                    ServicesFactory.getParticipationService().insert(participation);
                    resp.sendRedirect(req.getContextPath() + uri);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;


            default:
                resp.getWriter().write("no route mapping");

        }
    }

        @Override
        protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String requestUrl = req.getRequestURI().replace("/Pole_Digital_Academy_war_exploded", "");

            switch (requestUrl) {
                case "/participation/manage":
                    String uri2 = "/participation/manage?id=" + req.getParameter("activity");
                    String uri3 = "/participation/participation";
                    try {
                        Participation participation_to_changeStatus = ServicesFactory.getParticipationService().findById(Integer.parseInt(req.getParameter("participation_type")));
                        String status = req.getParameter("new_status");
                        Participation.ParticipationTypeEnum foundParticipationType=Stream.of(Participation.ParticipationTypeEnum.values()).filter(pt->pt.name().equals(status)).findAny().get();
                        participation_to_changeStatus.setParticipationType(foundParticipationType);
                        ServicesFactory.getParticipationService().update(participation_to_changeStatus);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }finally {
                         resp.sendRedirect(uri2);
                    }
                    break;

                default:
                    resp.getWriter().write("no route mapping yet");
            }
            ;

        }

}





