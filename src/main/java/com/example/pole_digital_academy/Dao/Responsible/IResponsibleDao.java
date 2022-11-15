package com.example.pole_digital_academy.Dao.Responsible;

import com.example.pole_digital_academy.Dao.IDao;
import com.example.pole_digital_academy.Entities.Responsible;

import java.util.List;

public interface IResponsibleDao extends IDao<Responsible> {
    Responsible update1(Responsible responsible) throws Exception;
    List<Responsible> getNonOccupedResponsibles() throws Exception;
}
