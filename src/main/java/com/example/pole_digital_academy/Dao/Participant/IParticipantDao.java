package com.example.pole_digital_academy.Dao.Participant;

import com.example.pole_digital_academy.Dao.IDao;
import com.example.pole_digital_academy.Entities.Participant;

import java.util.List;

public interface IParticipantDao extends IDao<Participant> {
    Participant update1(Participant participant) throws Exception;
    List<Participant> getAllPNotInSelActivity(int id) throws Exception;
    List<Participant> getAllPInSelActivity(int id) throws Exception;
}
