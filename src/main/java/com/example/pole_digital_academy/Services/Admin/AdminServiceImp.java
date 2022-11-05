package com.example.pole_digital_academy.Services.Admin;

import com.example.pole_digital_academy.Dao.Admin.AdminDaoImp;
import com.example.pole_digital_academy.Dao.Admin.IAdminDao;
import com.example.pole_digital_academy.Dao.DaoFactory;
import com.example.pole_digital_academy.Entities.Admin;
import com.example.pole_digital_academy.utils.PasswordHasher;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

import java.util.List;

public class AdminServiceImp implements IAdminService{

    IAdminDao AdminDao = new AdminDaoImp();

    @Override
    public int delete(int id) throws Exception {
        return 0;
    }

    @Override
    public List<Admin> getAll() throws Exception {
        return null;
    }

    @Override
    public Admin findById(int id) throws Exception {
        return null;
    }

    @Override
    public int insert(Admin entity) throws Exception {
        return 0;
    }

    @Override
    public int update(Admin entity) throws Exception {
        return 0;
    }

    @Override
    public boolean login(String Email, String Password) throws Exception {
        Admin admin = DaoFactory.getAdminDao().findByEmail(Email);
        if(admin.getEmail()==null){return false ;}
        return PasswordHasher.verify(admin.getPasswordHash(), Password);
    }

    public Admin findByEmail(String Email) throws Exception {
        return AdminDao.findByEmail(Email);
    }
}
