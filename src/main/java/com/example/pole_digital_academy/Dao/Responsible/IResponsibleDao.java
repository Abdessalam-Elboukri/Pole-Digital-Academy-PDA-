package com.example.pole_digital_academy.Dao.Responsible;

import com.example.pole_digital_academy.Dao.IDao;
import com.example.pole_digital_academy.Entities.Participant;
import com.example.pole_digital_academy.Entities.Responsible;

import java.util.List;

public interface IResponsibleDao extends IDao<Responsible> {

    List<Responsible> getNonOccupedResponsibles() throws Exception;
    Responsible update1(Responsible participant) throws Exception;
}
