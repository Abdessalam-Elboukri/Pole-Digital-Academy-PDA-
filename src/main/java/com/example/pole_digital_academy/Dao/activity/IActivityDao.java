package com.example.pole_digital_academy.Dao.activity;

import com.example.pole_digital_academy.Dao.IDao;
import com.example.pole_digital_academy.Entities.Activity;

import java.time.LocalDate;
import java.util.List;

public interface IActivityDao extends IDao<Activity> {
   List<Activity> search(LocalDate startIntervalDate, LocalDate endIntervalDate, Activity.ActivityTypeEnum type);
}
