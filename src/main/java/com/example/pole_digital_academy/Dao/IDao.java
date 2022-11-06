package com.example.pole_digital_academy.Dao;

import com.example.pole_digital_academy.utils.EntityManagerFactory;
import jakarta.persistence.EntityManager;

import java.util.List;

public interface IDao<T> {
    int delete(int id) throws Exception;
    List<T> getAll()   throws Exception;
    T findById(int id)  throws Exception;
    default void insert(T entity) throws Exception{
        EntityManager em= EntityManagerFactory.getEntityManager();
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        em.close();
    }
    int update(T entity) throws Exception;

}
