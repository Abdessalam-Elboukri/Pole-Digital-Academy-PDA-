package com.example.pole_digital_academy.Services;

import com.example.pole_digital_academy.Services.User.IUserService;
import com.example.pole_digital_academy.Services.User.UserServiceImp;
import com.example.pole_digital_academy.Services.activity.ActivityServiceImp;
import com.example.pole_digital_academy.Services.activity.IActivityService;

public class ServicesFactory {
    static IActivityService activityServiceInstance;
    static IUserService userServiceInstance;
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


}
