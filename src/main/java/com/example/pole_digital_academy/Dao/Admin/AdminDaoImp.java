package com.example.pole_digital_academy.Dao.Admin;

import com.example.pole_digital_academy.Entities.Admin;
import com.example.pole_digital_academy.utils.EntityManagerFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

public class AdminDaoImp implements IAdminDao{

    //private static final EntityManager em = EntityManager_Factory.getEntityManager();

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
    public Admin findByEmail(String Email) throws Exception {
        EntityManager em = EntityManagerFactory.getEntityManager();
        em.getTransaction().begin();
        System.out.println("here");
        Query query = em.createQuery(  "SELECT admin FROM Admin admin  where admin.email = :Email ", Admin.class);
        query.setParameter("Email", Email);
        Admin admin = (Admin) query.getSingleResult();
        System.out.println(admin.getRole());
        em.getTransaction().commit();

        return admin;
    }

}
