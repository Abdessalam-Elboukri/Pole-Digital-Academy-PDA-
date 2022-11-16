package com.example.pole_digital_academy.Services.activity;

import com.example.pole_digital_academy.Dao.DaoFactory;
import com.example.pole_digital_academy.Entities.Activity;

import java.time.LocalDate;
import java.util.List;

public class ActivityServiceImp implements IActivityService{
    @Override
    public int delete(int id) throws Exception {
        DaoFactory.getActivityDao().delete(id);
        return  1;
    }

    @Override
    public List<Activity> getAll() throws Exception {
        return DaoFactory.getActivityDao().getAll();

    }

    @Override
    public Activity findById(int id) throws Exception {

        return DaoFactory.getActivityDao().findById(id);
    }

    @Override
    public int insert(Activity entity) throws Exception {
        DaoFactory.getActivityDao().insert(entity);
        return 0;
    }

    @Override
    public int update(Activity entity) throws Exception {

        return DaoFactory.getActivityDao().update(entity);
    }

    @Override
    public List<Activity> search(LocalDate startIntervalDate, LocalDate endIntervalDate, Activity.ActivityTypeEnum type) {
        return DaoFactory.getActivityDao().search(startIntervalDate,endIntervalDate,type);
    }
}
