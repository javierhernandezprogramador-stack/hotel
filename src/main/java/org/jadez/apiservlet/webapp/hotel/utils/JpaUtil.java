package org.jadez.apiservlet.webapp.hotel.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {
    private static final EntityManagerFactory entityManagerFactory = buildEntityManagerFactory();

    //Maneja la fabrica de manager
    private static EntityManagerFactory buildEntityManagerFactory() {
        return Persistence.createEntityManagerFactory("persistenceJPA");
    }

    //Retorna el manager que usaremos
    public static EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}
