package com.example.pole_digital_academy.Services.participation;

import com.example.pole_digital_academy.Dao.DaoFactory;
import com.example.pole_digital_academy.Entities.Participation;

import java.util.List;

public class ParticipationServiceImp implements IParticipationService{
    @Override
    public int delete(int id) throws Exception {
        return 0;
    }

    @Override
    public List<Participation> getAll() throws Exception {
        return null;
    }

    @Override
    public Participation findById(int id) throws Exception {
        return null;
    }

    @Override
    public int insert(Participation entity) throws Exception {
        DaoFactory.getParticipationDao().insert(entity);
        return 0;
    }

    @Override
    public int update(Participation entity) throws Exception {
        return 0;
    }
}
