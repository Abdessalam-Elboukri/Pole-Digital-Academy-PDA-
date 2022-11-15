package com.example.pole_digital_academy.Dao.participation;

import com.example.pole_digital_academy.Entities.Participant;
import com.example.pole_digital_academy.Entities.Participation;
import com.example.pole_digital_academy.utils.EntityManagerFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

public class ParticipationDaoImp implements IParticipationDao{
    @Override
    public int delete(int id) throws Exception {
        return 0;
    }

    @Override
    public List<Participation> getAll() throws Exception {
        return null;
    }

    @Override
    public Participation findById(int id) throws Exception {
        EntityManager em = EntityManagerFactory.getEntityManager();
        Participation participation = em.find(Participation.class,id);
        return participation;
    }

    @Override
    public int update(Participation entity) throws Exception {
        return 0;
    }


    @Override
    public Participation update1(Participation participation) throws Exception {
        EntityManager em = EntityManagerFactory.getEntityManager();
        em.getTransaction().begin();
        em.find(Participation.class,participation.getId());
        em.merge(participation);
        em.getTransaction().commit();
        return participation;
    }
}
