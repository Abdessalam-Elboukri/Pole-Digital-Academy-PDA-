package com.example.pole_digital_academy.Servlets;



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
        String requestUrl=req.getRequestURI().replace(req.getContextPath(),"");
        switch (requestUrl){
            case "/responsibles":
                List<Responsible> responsibles ;
                try {
                    responsibles = ServicesFactory.getResponsibleService().getAll();
                    req.setAttribute(Constants.KEY_RESPONSIBLES,responsibles);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                req.getRequestDispatcher("/WEB-INF/responsible/list.jsp").forward(req,resp);
                break;

            case "/responsibles/add":
                try {
                    getResponsibleTypesData(req,resp);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                req.getRequestDispatcher("/WEB-INF/responsible/add.jsp").forward(req,resp);
                break;

            case "/responsibles/update":

                Responsible editResponsible =null;

                try {
                    getResponsibleTypesData(req,resp);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    editResponsible = ServicesFactory.getResponsibleService().findById(Integer.parseInt(req.getParameter("id")));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                req.setAttribute(Constants.KEY_RESPONSIBLE_TO_EDIT,editResponsible);
                System.out.println(editResponsible);
                req.getRequestDispatcher("/WEB-INF/responsible/update.jsp").forward(req,resp);
                break;

            default:
                resp.getWriter().write("no route mapping");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestUrl=req.getRequestURI().replace(req.getContextPath(),"");
        switch(requestUrl){
            case "/responsibles/add":
                Responsible responsible = new Responsible();
                responsible.setFirstName(req.getParameter("firstname"));
                responsible.setLastName(req.getParameter("lastname"));
                responsible.setEmail(req.getParameter("email"));
                responsible.setPhone(req.getParameter("phone"));
                responsible.setRole(User.Role.RESPONSIBLE);
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
                resp.sendRedirect(req.getContextPath() + "/responsibles");
                break;
            case "/responsibles/update":
                try {
                    updateResponsible(req,resp);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                resp.getWriter().write("no route mapping yet");
        };

    }


    private void  updateResponsible(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Responsible responsible_to_edit= ServicesFactory.getResponsibleService().findById(Integer.parseInt(req.getParameter("responsibleId")));

        responsible_to_edit.setFirstName(req.getParameter("firstname"));
        responsible_to_edit.setLastName(req.getParameter("lastname"));
        responsible_to_edit.setEmail(req.getParameter("email"));
        responsible_to_edit.setPhone(req.getParameter("phone"));
        responsible_to_edit.setDomaine(req.getParameter("domaine"));
        ResponsibleType restype  = ServicesFactory.getResponsibleTypeService().findById(Integer.parseInt(req.getParameter("responsableType")));

        System.out.println("============here");
        responsible_to_edit.setRes_type(restype);

        System.out.println("=============>" + responsible_to_edit);
        String message ="";
        try{
            ServicesFactory.getResponsibleService().update(responsible_to_edit);
        }catch (Exception e){
            e.printStackTrace();
            message="Error While updating this responsible";
        } finally {
            resp.sendRedirect(req.getContextPath() + "/responsibles");
        }
    }


    private void getResponsibleTypesData(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        List<ResponsibleType> responsibleTypesList;
        try {
            responsibleTypesList = ServicesFactory.getResponsibleTypeService().getAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException(e.getMessage());
        }
        req.setAttribute(Constants.KEY_RESPONSIBLE_TYPES,responsibleTypesList);
    }



}
