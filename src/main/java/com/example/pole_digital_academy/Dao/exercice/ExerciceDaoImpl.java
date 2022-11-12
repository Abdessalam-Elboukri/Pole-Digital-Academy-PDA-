package com.example.pole_digital_academy.Dao.exercice;

import com.example.pole_digital_academy.Entities.Exercice;
import com.example.pole_digital_academy.utils.EntityManagerFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

public class ExerciceDaoImpl implements IExercicieDao{
    @Override
    public int delete(int id) throws Exception {
        EntityManager em=EntityManagerFactory.getEntityManager();
        em.getTransaction().begin();
        Query query=em.createQuery("DELETE FROM Exercice e WHERE e.id=:id");
        query.setParameter("id",id);
        int affectedRows= query.executeUpdate();
        em.getTransaction().commit();
        em.close();
        return affectedRows;
    }

    @Override
    public List<Exercice> getAll() throws Exception {
        EntityManager em=EntityManagerFactory.getEntityManager();
        return em.createQuery("SELECT e FROM Exercice e",Exercice.class).getResultList();
    }

    @Override
    public Exercice findById(int id) throws Exception {
        EntityManager em=EntityManagerFactory.getEntityManager();
        return em.find(Exercice.class,id);
    }

    @Override
    public int update(Exercice entity) throws Exception {
        EntityManager em=EntityManagerFactory.getEntityManager();
        em.getTransaction().begin();

        try {
            em.persist(entity);
            em.getTransaction().commit();
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            em.getTransaction().getRollbackOnly();
            return 0;
        }

    }

    @Override
    public List<Exercice> getAllForActivity(int activityId) {
          EntityManager em=EntityManagerFactory.getEntityManager();
          Query q=em.createQuery("SELECT e FROM Exercice e WHERE e.activity.id=:activityId");
          q.setParameter("activityId",activityId);
          return (List<Exercice>) q.getResultList();

    }
}
