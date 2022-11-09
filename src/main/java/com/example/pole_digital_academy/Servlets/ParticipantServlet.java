package com.example.pole_digital_academy.Servlets;


import com.example.pole_digital_academy.Entities.Participant;
import com.example.pole_digital_academy.Entities.User;
import com.example.pole_digital_academy.Services.Participant.IParticipantService;
import com.example.pole_digital_academy.Services.Participant.ParticipantServiceImp;
import com.example.pole_digital_academy.Services.ServicesFactory;
import com.example.pole_digital_academy.utils.Constants;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;


@WebServlet(name = "participantServlet", urlPatterns ={ "/participants","/participants/add"})
public class ParticipantServlet extends HttpServlet {
    public String $url ;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestUrl=req.getRequestURI().replace("/Pole_Digital_Academy_war","");
        switch (requestUrl){
            case "/participants":
                List<Participant> participantList = null;

                try {
                    participantList=ServicesFactory.getParticipantService().getAll();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                req.setAttribute(Constants.KEY_PARTICIPANTS_LIST,participantList);
                req.getRequestDispatcher("/WEB-INF/participants/list.jsp").forward(req,resp);
            break;

            case "/participants/add":
                req.getRequestDispatcher("/WEB-INF/participants/add.jsp").forward(req,resp);
                break;

            case "/participants/update":
                req.getRequestDispatcher("/WEB-INF/participants/update.jsp").forward(req,resp);
                break;

            default:
                resp.getWriter().write("no route mapping");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestUrl=req.getRequestURI().replace("/Pole_Digital_Academy_war","");
        switch(requestUrl){
            case "/participants/add":
                Participant participant = new Participant();
                participant.setFirstName(req.getParameter("firstname"));
                participant.setLastName(req.getParameter("lastname"));
                participant.setEmail(req.getParameter("email"));
                participant.setPhone(req.getParameter("phone"));
                participant.setRole(User.Role.PARTICIPANT);
                participant.setUserStatus(User.UserStatusEnum.ACTIVE);
                participant.setDomaine(req.getParameter("domaine"));

                try {
                    ServicesFactory.getParticipantService().insert(participant);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            case "/participants/edit":
                //TODO:: handle update form data
                // validate then send to business layer to handle data
                break;
            default:
                resp.getWriter().write("no route mapping yet");
        };

    }



}
