package com.example.pole_digital_academy.Servlets;
import com.example.pole_digital_academy.Entities.Admin;
import com.example.pole_digital_academy.Entities.User;
import com.example.pole_digital_academy.Services.Admin.AdminServiceImp;
import com.example.pole_digital_academy.Services.Admin.IAdminService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;


import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/admin-login")
public class LoginServlet extends HttpServlet {


          private IAdminService auth =new AdminServiceImp();

        /*public void init(){

        }*/

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request,response);
        }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String email = request.getParameter("Email");
            String password = request.getParameter("Password");

            //System.out.println(auth.login(email,password));
            try {
                if(auth.login(email, password)){
                    int adminId = new AdminServiceImp().findByEmail(email).getId();
                    User.Role authRole = new AdminServiceImp().findByEmail(email).getRole();
                    HttpSession session = request.getSession();
                    session.setAttribute("AdminId",adminId);
                    session.setAttribute("authRole",authRole );
                    response.sendRedirect("home");
                }else{
                    RequestDispatcher reject=request.getRequestDispatcher("login.jsp");
                    reject.include(request,response);
                    response.sendRedirect(request.getContextPath() + "/admin-login");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
}


