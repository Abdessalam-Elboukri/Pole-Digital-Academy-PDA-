package com.example.pole_digital_academy.Services.activity;

import com.example.pole_digital_academy.Entities.Activity;
import com.example.pole_digital_academy.Services.IService;

import java.time.LocalDate;
import java.util.List;

public interface IActivityService extends IService<Activity> {
    List<Activity> search(LocalDate startIntervalDate, LocalDate endIntervalDate,Activity.ActivityTypeEnum type);
}
