package com.example.pole_digital_academy.Services.Admin;

import com.example.pole_digital_academy.Entities.Admin;
import com.example.pole_digital_academy.Services.IService;

public interface IAdminService extends IService<Admin> {
    boolean login(String Email, String Password) throws Exception;
}
