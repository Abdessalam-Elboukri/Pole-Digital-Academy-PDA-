package com.example.pole_digital_academy.Services;

import com.example.pole_digital_academy.Entities.Activity;
import com.example.pole_digital_academy.Services.activity.ActivityServiceImp;
import com.example.pole_digital_academy.Services.activity.IActivityService;

public class ServicesFactory {
    static IActivityService activityServiceInstance;
    public static IActivityService getActivityService(){
        if(activityServiceInstance==null)
            activityServiceInstance=new ActivityServiceImp();
        return activityServiceInstance;
    }


}
