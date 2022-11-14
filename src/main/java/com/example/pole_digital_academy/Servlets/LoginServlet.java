package com.example.pole_digital_academy.Servlets;
import com.example.pole_digital_academy.Entities.Admin;
import com.example.pole_digital_academy.Entities.User;
import com.example.pole_digital_academy.Services.Admin.AdminServiceImp;
import com.example.pole_digital_academy.Services.Admin.IAdminService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;


import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = "/admin-login")
public class LoginServlet extends HttpServlet {
          private IAdminService auth =new AdminServiceImp();

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request,response);
        }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String email = request.getParameter("Email");
            String password = request.getParameter("Password");
            try {
                if(auth.login(email, password)){
                    int adminId = new AdminServiceImp().findByEmail(email).getId();
                    User.Role authRole = new AdminServiceImp().findByEmail(email).getRole();
                    String adminName = new AdminServiceImp().findByEmail(email).getFirstName();
                    HttpSession session = request.getSession();
                    session.setAttribute("AdminId",adminId);
                    session.setAttribute("authRole",authRole);
                    session.setAttribute("adminName",adminName);
                    response.sendRedirect("activities");
                }else{
                    RequestDispatcher reject=request.getRequestDispatcher("login.jsp");
                    reject.include(request,response);
                    response.sendRedirect(request.getContextPath() + "/admin-login?message=Wrong Authentication information");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
}


