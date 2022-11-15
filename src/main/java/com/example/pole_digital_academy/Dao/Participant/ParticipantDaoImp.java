package com.example.pole_digital_academy.Dao.Participant;

import com.example.pole_digital_academy.Entities.Activity;
import com.example.pole_digital_academy.Entities.Participant;
import com.example.pole_digital_academy.utils.EntityManagerFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

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

    //get all the participants are not participate in the selected activity
    public List<Participant> getAllPNotInSelActivity(int id){
        EntityManager em= EntityManagerFactory.getEntityManager();
        String queryString = "SELECT p FROM Participant p  WHERE NOT EXISTS ( SELECT pt FROM Participation pt where p.id = pt.participant.id AND pt.activity.id= :id ) ";
        Query query = em.createQuery(queryString);
        query.setParameter("id", id);
        return (List<Participant>) query.getResultList();
    }


    // get all the participations that are participate in the selected activity
    public List<Participant> getAllPInSelActivity(int id){
        EntityManager em= EntityManagerFactory.getEntityManager();
        String queryString = "SELECT p FROM Participant p  WHERE EXISTS ( SELECT pt FROM Participation pt where p.id = pt.participant.id AND pt.activity.id= :id ) ";
        Query query = em.createQuery(queryString);
        query.setParameter("id", id);
        return (List<Participant>) query.getResultList();
    }


}
