package com.example.pole_digital_academy.Dao;

import com.example.pole_digital_academy.Dao.Admin.AdminDaoImp;
import com.example.pole_digital_academy.Dao.Admin.IAdminDao;
import com.example.pole_digital_academy.Dao.Participant.IParticipantDao;
import com.example.pole_digital_academy.Dao.Participant.ParticipantDaoImp;
import com.example.pole_digital_academy.Dao.Responsible.IResponsibleDao;
import com.example.pole_digital_academy.Dao.Responsible.ResponsibleDaoImp;
import com.example.pole_digital_academy.Dao.ResponsibleType.IResponsibleTypeDao;
import com.example.pole_digital_academy.Dao.ResponsibleType.ResponsibleTypeDaoImp;
import com.example.pole_digital_academy.Dao.User.UserDao;
import com.example.pole_digital_academy.Dao.User.UserDaoImp;
import com.example.pole_digital_academy.Dao.activity.ActivityDaoImpl;
import com.example.pole_digital_academy.Dao.activity.IActivityDao;
import com.example.pole_digital_academy.Dao.exercice.ExerciceDaoImpl;
import com.example.pole_digital_academy.Dao.exercice.IExercicieDao;
import com.example.pole_digital_academy.Entities.User;
import com.example.pole_digital_academy.Services.activity.IActivityService;

public class DaoFactory {
    static UserDao userDaoInstance;
    static IAdminDao adminDaoInstance;
    static IActivityDao activityDao;
    static IParticipantDao participantDao;
    static IResponsibleTypeDao responsibleTypeDao;
    static IResponsibleDao responsibleDao;
    static IExercicieDao exerciceDao;


    public static UserDao getUserDao() {
        if (userDaoInstance == null) {
            return userDaoInstance = new UserDaoImp();
        }
        return userDaoInstance;
    }

    public static IAdminDao getAdminDao(){
        if(adminDaoInstance==null){
            return adminDaoInstance=new AdminDaoImp();
        }
        return adminDaoInstance;
    }
    public static IActivityDao getActivityDao(){
        if(activityDao==null){
            return activityDao=new ActivityDaoImpl();
        }
        return activityDao;
    }
    public static IExercicieDao getExerciceDao(){
        if(exerciceDao==null){
            exerciceDao=new ExerciceDaoImpl();
        }
        return exerciceDao;
    }


    public static IParticipantDao getParticipantDao(){
        if(participantDao==null){
            return participantDao = new ParticipantDaoImp();
        }
        return participantDao;
    }

    public static IResponsibleTypeDao getResponsibleTypeDao(){
        if(responsibleTypeDao==null){
            return responsibleTypeDao = new ResponsibleTypeDaoImp();
        }
        return responsibleTypeDao;
    }

    public static IResponsibleDao getResponsibleDao(){
        if(responsibleDao==null){
            return responsibleDao = new ResponsibleDaoImp();
        }
        return responsibleDao;
    }



}
