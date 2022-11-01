package com.example.pole_digital_academy.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerHolder {
    private static EntityManager entityManager;
    public static EntityManager getEntityManager(){
        if(entityManager==null) {
             EntityManagerFactory emf= Persistence.createEntityManagerFactory("default");
            entityManager=emf.createEntityManager();
        }
        return entityManager;
    }

}
