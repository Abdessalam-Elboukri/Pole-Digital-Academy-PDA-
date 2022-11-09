package com.example.pole_digital_academy.Services.exercice;

import com.example.pole_digital_academy.Dao.DaoFactory;
import com.example.pole_digital_academy.Entities.Exercice;

import java.util.List;

public class ExerciceServiceImp implements IExerciceService {
    @Override
    public int delete(int id) throws Exception {

        return  DaoFactory.getExerciceDao().delete(id);
    }

    @Override
    public List<Exercice> getAll() throws Exception {
        return DaoFactory.getExerciceDao().getAll();

    }

    @Override
    public Exercice findById(int id) throws Exception {

        return DaoFactory.getExerciceDao().findById(id);
    }

    @Override
    public int insert(Exercice entity) throws Exception {
        DaoFactory.getExerciceDao().insert(entity);
        return 1;
    }

    @Override
    public int update(Exercice entity) throws Exception {
        return DaoFactory.getExerciceDao().update(entity);
    }
}
