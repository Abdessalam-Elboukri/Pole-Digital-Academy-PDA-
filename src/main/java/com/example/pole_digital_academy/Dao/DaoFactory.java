package com.example.pole_digital_academy.Dao;

import com.example.pole_digital_academy.Dao.Admin.AdminDaoImp;
import com.example.pole_digital_academy.Dao.Admin.IAdminDao;
import com.example.pole_digital_academy.Dao.User.UserDao;
import com.example.pole_digital_academy.Dao.User.UserDaoImp;
import com.example.pole_digital_academy.Entities.User;

public class DaoFactory {
    static UserDao userDaoInstance;

    public static UserDao getUserDao() {
        if (userDaoInstance == null) {
            return userDaoInstance = new UserDaoImp();
        }
        return userDaoInstance;
    }

    static IAdminDao adminDaoInstance;
    public static IAdminDao getAdminDao(){
        if(adminDaoInstance==null){
            return adminDaoInstance=new AdminDaoImp();
        }
        return adminDaoInstance;
    }



}
