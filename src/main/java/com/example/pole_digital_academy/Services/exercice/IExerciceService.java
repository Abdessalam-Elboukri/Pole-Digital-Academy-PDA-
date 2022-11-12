package com.example.pole_digital_academy.Services.exercice;

import com.example.pole_digital_academy.Entities.Exercice;
import com.example.pole_digital_academy.Services.IService;

import java.util.List;

public interface IExerciceService extends IService<Exercice> {
    List<Exercice> getAllForActivity(int activityId);
}
