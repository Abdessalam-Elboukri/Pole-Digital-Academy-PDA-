package com.example.pole_digital_academy.Services;

import com.example.pole_digital_academy.Services.Participant.IParticipantService;
import com.example.pole_digital_academy.Services.Participant.ParticipantServiceImp;
import com.example.pole_digital_academy.Services.Responsible.IResponsibleService;
import com.example.pole_digital_academy.Services.Responsible.ResponsibleSrviceImp;
import com.example.pole_digital_academy.Services.ResponsibleTypeService.IResponsibleTypeService;
import com.example.pole_digital_academy.Services.ResponsibleTypeService.ResponsibleTypeImp;
import com.example.pole_digital_academy.Services.User.IUserService;
import com.example.pole_digital_academy.Services.User.UserServiceImp;
import com.example.pole_digital_academy.Services.activity.ActivityServiceImp;
import com.example.pole_digital_academy.Services.activity.IActivityService;
import com.example.pole_digital_academy.Services.exercice.ExerciceServiceImp;
import com.example.pole_digital_academy.Services.exercice.IExerciceService;

public class ServicesFactory {
    static IActivityService activityServiceInstance;
    static IUserService userServiceInstance;
    static IExerciceService exerciceService;
    static IParticipantService participantService;
    static IResponsibleService responsibleService;
    static IResponsibleTypeService responsibleTypeService;



    public static IActivityService getActivityService(){
        if(activityServiceInstance==null)
            activityServiceInstance=new ActivityServiceImp();
        return activityServiceInstance;
    }

    public static IUserService getUserService(){
        if(userServiceInstance ==null)
            userServiceInstance = new UserServiceImp();
        return userServiceInstance;
    }

    public static IParticipantService getParticipantService(){
        if(participantService ==null)
            participantService = new ParticipantServiceImp();
        return participantService;
    }

    public static IResponsibleService getResponsibleService(){
        if(responsibleService==null){
            return responsibleService=new ResponsibleSrviceImp();
        }
        return responsibleService;
    }

    public static IResponsibleTypeService getResponsibleTypeService(){
        if(responsibleTypeService==null){
            return responsibleTypeService=new ResponsibleTypeImp();
        }
        return responsibleTypeService;
    }

    public static IExerciceService getExercicesService() {
        if(exerciceService==null)
            exerciceService=new ExerciceServiceImp();
        return exerciceService;
    }
}
