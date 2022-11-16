package com.example.pole_digital_academy.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class EntityManagerFactory {
    private static EntityManager entityManager;
    public static final String TEST_PERSISTENCE_UNIT_NAME="test_db";
    private static boolean isTestPersistenceUnit=false;
    public static EntityManager getEntityManager(){
        if(entityManager==null ||! entityManager.isOpen()) {
             jakarta.persistence.EntityManagerFactory emf= Persistence.createEntityManagerFactory(isTestPersistenceUnit?TEST_PERSISTENCE_UNIT_NAME:"pda_academy");
            entityManager=emf.createEntityManager();
        }
        return entityManager;
    }
    public static void setUseTestPersistenceUnit(boolean isTestPersistenceUnit){
        EntityManagerFactory.isTestPersistenceUnit=isTestPersistenceUnit;
    }

}
