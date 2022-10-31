package com.example.pole_digital_academy;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "helloServlet", value = "/hello")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("default");
        EntityManager em=emf.createEntityManager();
        EntityTransaction transaction=em.getTransaction();
        transaction.begin();
        try {
            User u = new User();
            u.setName("nki");
            Account a=new Account();
            a.setBalance(100);
            u.setAccount(a);
            em.persist(u);

            out.println("account balance is :"+em.find(User.class,1).getAccount().getBalance());
            //em.createQuery()
            transaction.commit();
            out.println("<h1>transaction success</h1>");
        }catch (Exception e){
            transaction.rollback();
            out.println("<h1>transaction failed</h1>"+e.getMessage());
            e.printStackTrace();
        }finally {
            out.println("finished");
        }
        //em.close();

        // Hello
        out.println("</body></html>");

    }

    public void destroy() {
    }
}