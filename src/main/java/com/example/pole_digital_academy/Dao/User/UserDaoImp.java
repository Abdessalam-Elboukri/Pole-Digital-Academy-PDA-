package com.example.pole_digital_academy.Dao.User;

import com.example.pole_digital_academy.Entities.User;
import com.example.pole_digital_academy.utils.EntityManagerFactory;
import jakarta.persistence.EntityManager;

import java.util.List;

public class UserDaoImp implements UserDao{

    private static EntityManager em = EntityManagerFactory.getEntityManager();

    @Override
    public int delete(int id) throws Exception {
        return 0;
    }

    @Override
    public List<User> getAll() throws Exception {
        return null;
    }

    @Override
    public User findById(int id) throws Exception {
        return null;
    }

    @Override
    public void insert(User entity) throws Exception {

    }

    @Override
    public int update(User entity) throws Exception {
        return 0;
    }


    @Override
    public User findByEmail(String Email) throws Exception {
        return null;
    }
}
