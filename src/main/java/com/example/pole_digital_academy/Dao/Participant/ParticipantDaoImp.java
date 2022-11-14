package com.example.pole_digital_academy.Dao.Participant;

import com.example.pole_digital_academy.Entities.Activity;
import com.example.pole_digital_academy.Entities.Participant;
import com.example.pole_digital_academy.utils.EntityManagerFactory;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ParticipantDaoImp implements IParticipantDao{
    @Override
    public int delete(int id) throws Exception {
        return 0;
    }

    @Override
    public List<Participant> getAll() throws Exception {
        return EntityManagerFactory.getEntityManager().createQuery("SELECT p FROM Participant p", Participant.class).getResultList();

    }

    @Override
    public Participant findById(int id) throws Exception {
        EntityManager em = EntityManagerFactory.getEntityManager();
        Participant participant = em.find(Participant.class,id);
        return participant;
    }

    @Override
    public void insert(Participant entity) throws Exception {
        IParticipantDao.super.insert(entity);
    }

    @Override
    public int update(Participant entity) throws Exception {
        return 0;
    }

    @Override
    public Participant update1(Participant participant) throws Exception {
        EntityManager em = EntityManagerFactory.getEntityManager();
        em.getTransaction().begin();
        em.find(Participant.class,participant.getId());
        em.merge(participant);
        em.getTransaction().commit();
        return participant;
    }


}
