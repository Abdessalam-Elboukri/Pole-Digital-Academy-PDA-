package com.example.pole_digital_academy.Services;

import com.example.pole_digital_academy.Dao.IDao;
import com.example.pole_digital_academy.Entities.Exercice;
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


    public static IExerciceService getExercicesService() {
        if(exerciceService==null)
            exerciceService=new ExerciceServiceImp();
        return exerciceService;
    }
}
