package com.example.pole_digital_academy.Services.Participant;

import com.example.pole_digital_academy.Entities.Participant;
import com.example.pole_digital_academy.Services.IService;

import java.util.List;

public interface IParticipantService extends IService<Participant> {
    List<Participant> getAllPNotInSelActivity(int id) throws Exception;
    List<Participant> getAllPInSelActivity(int id) throws Exception;
}
