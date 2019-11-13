package com.ufc.app;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Hello world!
 */
public class App {

    private static final Logger LOGGER = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {

        EntityManagerFactory emf;
        EntityManager em;

        try {

            emf = Persistence.createEntityManagerFactory("persistence");
            em = emf.createEntityManager();

            LOGGER.log(Level.INFO, "connection was:" + em.isOpen());

        } catch (Throwable ex) {
            LOGGER.log(Level.WARNING, ex.getMessage());
        }
    }
}
