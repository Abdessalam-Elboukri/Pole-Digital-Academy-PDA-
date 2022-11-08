package com.example.pole_digital_academy.Services.ResponsibleTypeService;

import com.example.pole_digital_academy.Dao.DaoFactory;
import com.example.pole_digital_academy.Entities.ResponsibleType;

import java.util.List;

public class ResponsibleTypeImp implements IResponsibleTypeService{
    @Override
    public int delete(int id) throws Exception {
        return 0;
    }

    @Override
    public List<ResponsibleType> getAll() throws Exception {
        return DaoFactory.getResponsibleTypeDao().getAll();
    }

    @Override
    public ResponsibleType findById(int id) throws Exception {
        return null;
    }

    @Override
    public int insert(ResponsibleType entity) throws Exception {
        return 0;
    }

    @Override
    public int update(ResponsibleType entity) throws Exception {
        return 0;
    }
}
