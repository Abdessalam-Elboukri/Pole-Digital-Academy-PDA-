package com.example.pole_digital_academy.Servlets;

import com.example.pole_digital_academy.Entities.Activity;

import java.util.List;

public class InputValidator {
    public static boolean isActivityValid(Activity activity, List<String> errors){
        boolean isValid=true;
        if(activity.getActivityType()==null)
        {
            isValid=false;
            errors.add("invalide activity type");
        }
        if(activity.getTitle()==null || activity.getTitle().isEmpty() ||activity.getTitle().length()<3)
        {
            isValid=false;
            errors.add("invalide activity name must contain at least 3 characters");
        }
        if(activity.getDescription()==null || activity.getDescription().isEmpty() ||activity.getDescription().length()<3)
        {
            isValid=false;
            errors.add("invalide activity description must contain at least 3 characters");
        }
        //TODO validate dates here

        return isValid;
    }

}
