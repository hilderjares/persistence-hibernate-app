package com.ufc.app;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.ufc.app.service.AddressService;

/**
 * Hello world!
 */
public class App {

    private static final Logger LOGGER = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {

        EntityManager entityManager = null;
        EntityManagerFactory entityManagerFactory = null;

        try {

            entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
            entityManager = entityManagerFactory.createEntityManager();

            AddressService addressService = new AddressService(entityManagerFactory, entityManager);

            // addressService.createAddress();
            // addressService.createAddress();

            addressService.listAll();
            // addressService.getAddress(1);

        } catch (Throwable ex) {
            LOGGER.log(Level.WARNING, ex.getMessage());
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
