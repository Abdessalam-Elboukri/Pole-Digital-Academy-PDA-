package com.example.pole_digital_academy.Services.Responsible;

import com.example.pole_digital_academy.Dao.DaoFactory;
import com.example.pole_digital_academy.Entities.Responsible;

import java.util.List;

public class ResponsibleSrviceImp implements IResponsibleService{
    @Override
    public int delete(int id) throws Exception {
        return 0;
    }

    @Override
    public List<Responsible> getAll() throws Exception {
        return DaoFactory.getResponsibleDao().getAll();
    }

    @Override
    public Responsible findById(int id) throws Exception {
        return DaoFactory.getResponsibleDao().findById(id);
    }

    @Override
    public int insert(Responsible entity) throws Exception {
        return 0;
    }

    @Override
    public int update(Responsible entity) throws Exception {
        return 0;
    }
}
