package com.example.pole_digital_academy.Servlets;



import com.example.pole_digital_academy.Entities.Activity;
import com.example.pole_digital_academy.Entities.Responsible;
import com.example.pole_digital_academy.Entities.ResponsibleType;
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


@WebServlet(name = "ResponsibleServlet", urlPatterns ={ "/responsibles","/responsibles/add","/responsibles/update"})
public class ResponsibleServlet extends HttpServlet {
    public String $url ;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestUrl=req.getRequestURI().replace("/Pole_Digital_Academy_war","");
        switch (requestUrl){
            case "/responsibles":
                req.getRequestDispatcher("/WEB-INF/responsible/list.jsp").forward(req,resp);
                break;

            case "/responsibles/add":
                List<ResponsibleType> responsibleTypesList;
                try {
                    responsibleTypesList = ServicesFactory.getResponsibleTypeService().getAll();
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new IOException(e.getMessage());
                }
                req.setAttribute(Constants.KEY_RESPONSIBLE_TYPES,responsibleTypesList);
                req.getRequestDispatcher("/WEB-INF/responsible/add.jsp").forward(req,resp);
                break;

            case "/responsibles/update":
                req.getRequestDispatcher("/WEB-INF/responsible/update.jsp").forward(req,resp);
                break;

            default:
                resp.getWriter().write("no route mapping");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestUrl=req.getRequestURI().replace("/Pole_Digital_Academy_war","");
        switch(requestUrl){
            case "/responsibles/add":
                Responsible responsible = new Responsible();
                responsible.setFirstName(req.getParameter("firstname"));
                responsible.setLastName(req.getParameter("lastname"));
                responsible.setEmail(req.getParameter("email"));
                responsible.setPhone(req.getParameter("phone"));
                responsible.setRole(User.Role.PARTICIPANT);
                responsible.setUserStatus(User.UserStatusEnum.ACTIVE);
                try {
                    ResponsibleType restype  = ServicesFactory.getResponsibleTypeService().findById(Integer.parseInt(req.getParameter("responsableType")));
                    System.out.println(restype);
                    responsible.setRes_type(restype);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                responsible.setDomaine(req.getParameter("domaine"));

                try {
                    ServicesFactory.getResponsibleService().insert(responsible);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            case "/responsibles/edit":
                //TODO:: handle update form data
                // validate then send to business layer to handle data
                break;
            default:
                resp.getWriter().write("no route mapping yet");
        };

    }



}
