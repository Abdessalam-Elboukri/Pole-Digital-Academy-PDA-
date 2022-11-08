package com.example.pole_digital_academy.Dao.ResponsibleType;

import com.example.pole_digital_academy.Entities.ResponsibleType;
import com.example.pole_digital_academy.utils.EntityManagerFactory;

import java.util.List;

public class ResponsibleTypeDaoImp implements IResponsibleTypeDao{
    @Override
    public int delete(int id) throws Exception {
        return 0;
    }

    @Override
    public List<ResponsibleType> getAll() throws Exception {
        return EntityManagerFactory.getEntityManager().createQuery("SELECT resType FROM ResponsibleType resType", ResponsibleType.class).getResultList();

    }

    @Override
    public ResponsibleType findById(int id) throws Exception {
        return null;
    }

    @Override
    public int update(ResponsibleType entity) throws Exception {
        return 0;
    }
}
