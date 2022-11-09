package com.example.pole_digital_academy.middleware;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(filterName = "auth",urlPatterns = {"/home","/activities","/activities/add",
                                                "/activities/delete", "/activities/edit"})

public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        if (!request.getRequestURI().contains("/admin-login")) {
            RequestDispatcher dispatcher;
            HttpSession session = request.getSession();
            if (session != null) {
                if (session.getAttribute("AdminId") == null || session.getAttribute("authRole") == null) {
                    response.sendRedirect(request.getContextPath() + "/admin-login");
                    return;
                }
            }
            else {
                request.setAttribute("error", "Session timed out!");
                dispatcher = request.getRequestDispatcher("login.jsp");
                dispatcher.forward(request, response);
                return;
            }
        }
        filterChain.doFilter(request,response);
    }
    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
