package com.example.pole_digital_academy.Dao.Admin;

import com.example.pole_digital_academy.Dao.IDao;
import com.example.pole_digital_academy.Entities.Admin;

public interface IAdminDao extends IDao<Admin> {
    Admin findByEmail(String Email) throws Exception ;
}
