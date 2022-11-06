package com.example.pole_digital_academy.Dao.activity;

import com.example.pole_digital_academy.Dao.IDao;
import com.example.pole_digital_academy.Entities.Activity;
import com.example.pole_digital_academy.utils.EntityManagerFactory;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ActivityDaoImpl implements IActivityDao {
    @Override
    public int delete(int id) throws Exception {
        EntityManagerFactory.getEntityManager().remove(EntityManagerFactory.getEntityManager().find(Activity.class,id));
        return 1;
    }

    @Override
    public List<Activity> getAll() throws Exception {
        return EntityManagerFactory.getEntityManager().createQuery("SELECT a FROM Activity a",Activity.class).getResultList();
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
