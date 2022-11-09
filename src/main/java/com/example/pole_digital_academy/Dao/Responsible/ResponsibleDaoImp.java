package com.example.pole_digital_academy.Dao.Responsible;

import com.example.pole_digital_academy.Entities.Responsible;
import com.example.pole_digital_academy.utils.EntityManagerFactory;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ResponsibleDaoImp implements IResponsibleDao{
    @Override
    public int delete(int id) throws Exception {
        return 0;
    }

    @Override
    public List<Responsible> getAll() throws Exception {
         return EntityManagerFactory.getEntityManager().createQuery("SELECT res FROM Responsible res", Responsible.class).getResultList();
    }

    @Override
    public Responsible findById(int id) throws Exception {
        EntityManager em = EntityManagerFactory.getEntityManager();
        Responsible user = em.find(Responsible.class, id);
        em.persist(user);

        em.getTransaction().commit();
        return null;
    }

    @Override
    public int update(Responsible entity) throws Exception {
        return 0;
    }
}
