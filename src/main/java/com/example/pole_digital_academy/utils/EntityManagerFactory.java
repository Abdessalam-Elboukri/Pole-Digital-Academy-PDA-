package com.example.pole_digital_academy.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class EntityManagerFactory {
    private static EntityManager entityManager;
    public static EntityManager getEntityManager(){
        if(entityManager==null) {
             jakarta.persistence.EntityManagerFactory emf= Persistence.createEntityManagerFactory("pda_academy");
            entityManager=emf.createEntityManager();
        }
        return entityManager;
    }

}
