package com.example.pole_digital_academy.Servlets;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet(name = "participantServlet",urlPatterns = "/participants")
public class ParticipantServlet extends HttpServlet {
    public String $url ;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/participants/allPrticipantsList.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        switch(req.getRequestURI()){
            case "/participants/add":
                //TODO:: handle add activity form data
                // validate then send to business layer to handle data

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
