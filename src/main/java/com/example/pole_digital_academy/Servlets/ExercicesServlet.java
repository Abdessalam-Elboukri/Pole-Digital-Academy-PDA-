package com.example.pole_digital_academy.Servlets;

import com.example.pole_digital_academy.Entities.Exercice;
import com.example.pole_digital_academy.Services.ServicesFactory;
import com.example.pole_digital_academy.Services.exercice.IExerciceService;
import com.example.pole_digital_academy.utils.Constants;
import com.example.pole_digital_academy.utils.InputValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "exercicesServlet",urlPatterns = {"/exercices","/exercices/add","/exercices/delete","/exercices/edit","/exercices/activity"})
public class ExercicesServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestUrl=req.getRequestURI().replace("/Pole_Digital_Academy_war_exploded","");
        //TODO call responsible service instead

        switch(requestUrl){
            case "/exercices":
                List<Exercice> exercices;
                try {
                    String activityId=req.getParameter("activity_id");
                    if(activityId!=null)
                        exercices=ServicesFactory.getExercicesService().getAllForActivity(Integer.parseInt(activityId));
                    else
                    exercices=ServicesFactory.getExercicesService().getAll();
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new IOException(e.getMessage());
                }
                req.setAttribute(Constants.KEY_EXERCICES_LIST,exercices);
                req.getRequestDispatcher("/WEB-INF/exercices/list.jsp").forward(req,resp);
                break;
            case "/exercices/activity":

                List<Exercice> exercicesForActivity;
                try {
                    exercicesForActivity=ServicesFactory.getExercicesService().getAllForActivity(Integer.parseInt(req.getParameter("id")));
                    req.setAttribute(Constants.KEY_EXERCISES_FOR_ACTIVITY,ServicesFactory.getActivityService().findById(Integer.parseInt(req.getParameter("id"))).getTitle());
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new IOException(e.getMessage());
                }

                req.setAttribute(Constants.KEY_EXERCICES_LIST,exercicesForActivity);
                req.getRequestDispatcher("/WEB-INF/exercices/list.jsp").forward(req,resp);
                break;
            case "/exercices/add":
                try {
                    req.setAttribute(Constants.KEY_ACTIVITIES_LIST,ServicesFactory.getActivityService().getAll());
                    req.getRequestDispatcher("/WEB-INF/exercices/add.jsp").forward(req,resp);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "/exercices/edit":
                    Exercice exerciceToEdit=null;
                    try {
                        exerciceToEdit=ServicesFactory.getExercicesService().findById(Integer.parseInt(req.getParameter("id")));
                        req.setAttribute(Constants.KEY_ACTIVITIES_LIST,ServicesFactory.getActivityService().getAll());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    req.setAttribute(Constants.KEY_EXERCICE_TO_EDIT,exerciceToEdit);
                    req.getRequestDispatcher("/WEB-INF/exercices/edit.jsp").forward(req,resp);
                break;
                case "/exercices/delete":
                    IExerciceService exerciceService=ServicesFactory.getExercicesService();
                    String message="";
                    try {
                        System.out.println("delete exercice : "+exerciceService.delete(Integer.parseInt(req.getParameter("id"))));
                            message="exercice deleted successfully!";
                    } catch (Exception e) {;
                        System.out.println("Error:"+e.getCause());
                        message="Error deleting exercice";
                    }finally {
                        resp.sendRedirect("/exercices?message="+message);
                    }

                break;
            default:
                resp.getWriter().write("no route mapping");
        };
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestUrl=req.getRequestURI().replace("/Pole_Digital_Academy_war_exploded","");
        switch(requestUrl){
            case "/exercices/add":
                  try {
                    processAddExercice(req,resp);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "/exercices/edit":
                try {
                    processEditExercice(req,resp);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                resp.getWriter().write("no route mapping yet");
        };

    }

    private void processEditExercice(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Exercice exercice=ServicesFactory.getExercicesService().findById(Integer.parseInt(req.getParameter(Exercice.KEY_ID)));

        exercice.setTitle(req.getParameter(exercice.KEY_TITLE));
        try{
            String status=req.getParameter(exercice.KEY_STATUS);
            if(status==null)
                throw new IllegalArgumentException("status is not set");
            exercice.setStatus(Exercice.ExerciceStatusEnum.values()[Integer.parseInt(status)]);
        }catch (Exception ex){
            ex.printStackTrace();
            //exercice.setStatus(Exercice.ExerciceStatusEnum.IN_PROGRESS);
        }

        try {
            exercice.setStartDate(LocalDate.parse(req.getParameter(exercice.KEY_START_DATE)));

        }catch (DateTimeParseException e){
            e.printStackTrace();
            exercice.setStartDate(LocalDate.now());
        }
        try {
            exercice.setEndDate(LocalDate.parse(req.getParameter(exercice.KEY_END_DATE)));

        }catch (DateTimeParseException e){
            e.printStackTrace();
            exercice.setEndDate(LocalDate.now());
        }
        exercice.setYear(Integer.parseInt(req.getParameter(Exercice.KEY_YEAR)));
        exercice.setActivity(ServicesFactory.getActivityService().findById(Integer.parseInt(req.getParameter(Exercice.KEY_ACTIVITY_ID))));

        List<String> validationErrors=new ArrayList<>();
        if(InputValidator.isExerciceValid(exercice,validationErrors) ){
            //if the data is valid we add the new  exercice and redirect the user to the activities list page
            System.out.println("exercice updated");
            String message="";
            try {
                ServicesFactory.getExercicesService().update(exercice);
                message="exercice edited successfully!";
            }catch (Exception e){
                e.printStackTrace();
                message="Error updating exercice";
            }
            finally {
                resp.sendRedirect("/exercices?message="+message);
            }
        }else
        {
            //if the data is not valid we redirect the user to exercice creation form with validation errors
            System.out.println("failed to update exercice");
            req.setAttribute(Constants.KEY_VALIDATION_ERRORS,validationErrors);
            Exercice exerciceToEdit=null;
            try {
                exerciceToEdit=ServicesFactory.getExercicesService().findById(Integer.parseInt(req.getParameter(Exercice.KEY_ID)));
                System.out.println("found exercice has a name of "+exerciceToEdit.getTitle());
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("error while looking for exercice !");
            }
            req.setAttribute(Constants.KEY_EXERCICE_TO_EDIT,exerciceToEdit);

            req.getRequestDispatcher("/WEB-INF/exercices/edit.jsp").forward(req,resp);
        };
    }

    private void processAddExercice(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Exercice exercice=new Exercice();
        exercice.setTitle(req.getParameter(exercice.KEY_TITLE));
        exercice.setStatus(Exercice.ExerciceStatusEnum.IN_PROGRESS);

        try {
            exercice.setStartDate(LocalDate.parse(req.getParameter(exercice.KEY_START_DATE)));

        }catch (DateTimeParseException e){
            e.printStackTrace();
            exercice.setStartDate(LocalDate.now());
        }
        try {
            exercice.setEndDate(LocalDate.parse(req.getParameter(exercice.KEY_END_DATE)));

        }catch (DateTimeParseException e){
            e.printStackTrace();
            exercice.setEndDate(LocalDate.now());
        }
        exercice.setYear(Integer.parseInt(req.getParameter(Exercice.KEY_YEAR)));

        exercice.setActivity(ServicesFactory.getActivityService().findById(Integer.parseInt(req.getParameter(Exercice.KEY_ACTIVITY_ID))));
        List<String> validationErrors=new ArrayList<>();
        if(InputValidator.isExerciceValid(exercice,validationErrors) ){
            //if the data is valid we add the new  exercice and redirect the user to the activities list page
            String message="";
            try {
                ServicesFactory.getExercicesService().insert(exercice);
                message="exercice inserted successfully!";
            }catch (Exception e){
                e.printStackTrace();
                message="Error adding exercice";
            }
            finally {
                resp.sendRedirect("/exercices?message="+message);
            }


        }else
        {
            //if the data is not valid we redirect the user to exercice creation form with validation errors
            System.out.println("failed to add exercice");
            req.setAttribute(Constants.KEY_VALIDATION_ERRORS,validationErrors);
                  req.getRequestDispatcher("/WEB-INF/exercices/add.jsp").forward(req,resp);
        };
    }


}
