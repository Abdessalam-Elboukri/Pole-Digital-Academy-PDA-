package com.example.pole_digital_academy.Dao.User;

import com.example.pole_digital_academy.Dao.IDao;
import com.example.pole_digital_academy.Entities.User;

public interface  UserDao extends IDao<User> {
    User findByEmail(String Email) throws Exception;


}
