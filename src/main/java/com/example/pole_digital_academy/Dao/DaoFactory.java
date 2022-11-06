package com.example.pole_digital_academy.Dao;

import com.example.pole_digital_academy.Dao.Admin.AdminDaoImp;
import com.example.pole_digital_academy.Dao.Admin.IAdminDao;
import com.example.pole_digital_academy.Dao.User.UserDao;
import com.example.pole_digital_academy.Dao.User.UserDaoImp;
import com.example.pole_digital_academy.Dao.activity.ActivityDaoImpl;
import com.example.pole_digital_academy.Dao.activity.IActivityDao;
import com.example.pole_digital_academy.Entities.User;
import com.example.pole_digital_academy.Services.activity.IActivityService;

public class DaoFactory {
    static UserDao userDaoInstance;
    static IAdminDao adminDaoInstance;
    static IActivityDao activityDao;
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



}
