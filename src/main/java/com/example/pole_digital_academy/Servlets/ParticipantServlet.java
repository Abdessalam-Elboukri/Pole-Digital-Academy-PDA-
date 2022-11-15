package com.example.pole_digital_academy.Servlets;


import com.example.pole_digital_academy.Entities.Participant;
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


@WebServlet(name = "participantServlet", urlPatterns ={ "/participants","/participants/add","/participants/update","/participants/delete","/participants/status"})
public class ParticipantServlet extends HttpServlet {
    public String $url ;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestUrl=req.getRequestURI().replace("/Pole_Digital_Academy_war_exploded","");
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
                Participant editParticipant=null;
                try {
                    editParticipant = ServicesFactory.getParticipantService().findById(Integer.parseInt(req.getParameter("id")));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                req.setAttribute(Constants.PARTICIPANT_TO_Edit,editParticipant);
                System.out.println("============here=================");
                req.getRequestDispatcher("/WEB-INF/participants/update.jsp").forward(req,resp);
                break;
            case "/participants/status":
                Participant changeStatusParticipant=null;
                try {
                    changeStatusParticipant = ServicesFactory.getParticipantService().findById(Integer.parseInt(req.getParameter("id")));
                    changeStatusParticipant.setUserStatus(changeStatusParticipant.getUserStatus().equals(User.UserStatusEnum.ACTIVE)? User.UserStatusEnum.DISABLED: User.UserStatusEnum.ACTIVE);
                    ServicesFactory.getParticipantService().update(changeStatusParticipant);
                    resp.sendRedirect(req.getContextPath()+"/participants");
                } catch (Exception e) {
                    e.printStackTrace();

                }

                break;

            default:
                resp.getWriter().write("no route mapping");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestUrl=req.getRequestURI().replace("/Pole_Digital_Academy_war_exploded","");

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
            case "/participants/update":

                try {
                    UpdateParticipant(req,resp);
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;

            case "/participants/disable":
                try {
                    disableParticipant(req,resp);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                    finally {
                        resp.sendRedirect(req.getContextPath() + "/participants");
                }
                break;
            default:
                resp.getWriter().write("no route mapping yet");
        };

    }

    private void UpdateParticipant(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        Participant participant_to_Edit= ServicesFactory.getParticipantService().findById(Integer.parseInt(req.getParameter("id")));

        participant_to_Edit.setFirstName(req.getParameter("firstname"));
        participant_to_Edit.setLastName(req.getParameter("lastname"));
        participant_to_Edit.setEmail(req.getParameter("email"));
        participant_to_Edit.setPhone(req.getParameter("phone"));
        participant_to_Edit.setDomaine(req.getParameter("domaine"));

        String message ="";
        try{
            ServicesFactory.getParticipantService().update(participant_to_Edit);
        }catch (Exception e){
        e.printStackTrace();
        message="Error While updating participant";
        } finally {
            resp.sendRedirect(req.getContextPath() + "/participants");
        }
    }


    //Update status of participant to disabled
    private void disableParticipant(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Participant participant_to_disable = ServicesFactory.getParticipantService().findById(Integer.parseInt(req.getParameter("id")));

        participant_to_disable.setUserStatus(User.UserStatusEnum.DISABLED);
        ServicesFactory.getParticipantService().update(participant_to_disable);

    }


    }
