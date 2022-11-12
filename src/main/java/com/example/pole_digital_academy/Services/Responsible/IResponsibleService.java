package com.example.pole_digital_academy.Services.Responsible;

import com.example.pole_digital_academy.Entities.Responsible;
import com.example.pole_digital_academy.Services.IService;

import java.util.List;

public interface IResponsibleService extends IService<Responsible> {
    List<Responsible> getNonOccupedResponsibles() throws Exception;
}
