package com.example.pole_digital_academy.Dao.exercice;

import com.example.pole_digital_academy.Dao.IDao;
import com.example.pole_digital_academy.Entities.Exercice;

import java.util.List;

public interface IExercicieDao extends IDao<Exercice> {
    List<Exercice> getAllForActivity(int activityId);
}
