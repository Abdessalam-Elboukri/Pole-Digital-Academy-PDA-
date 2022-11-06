package com.example.pole_digital_academy.Dao.activity;

import com.example.pole_digital_academy.Dao.IDao;
import com.example.pole_digital_academy.Entities.Activity;
import com.example.pole_digital_academy.utils.EntityManagerFactory;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ActivityDaoImpl implements IDao<Activity> {
    @Override
    public int delete(int id) throws Exception {
        return 0;
    }

    @Override
    public List<Activity> getAll() throws Exception {
        return null;
    }

    @Override
    public Activity findById(int id) throws Exception {
        return null;
    }


    @Override
    public int update(Activity entity) throws Exception {
        return 0;
    }
}
