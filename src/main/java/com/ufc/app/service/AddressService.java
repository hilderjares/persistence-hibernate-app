package com.ufc.app.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.ufc.app.dao.AddressDao;
import com.ufc.app.model.Address;

public class AddressService {

    private AddressDao addressDao;

    public AddressService(EntityManagerFactory entityManagerFactory, EntityManager entityManager) { 
        this.addressDao = new AddressDao(entityManagerFactory, entityManager);
    }

    public void createAddress() {

        Address address = new Address();

        address.setCity("quixada");
        address.setState("ce");
        address.setStreet("rua benjamim constant");
        address.setZipcode("290238484");

        this.addressDao.save(address);
    }

    public void getAddress(int id) {

        Address address = addressDao.get(id);

        System.out.println("================================");
        System.out.println(address.toString());
        System.out.println("================================");
    }

    public void listAll() {
        
        List<Address> addresses = addressDao.getAll();
        
        for (Address address : addresses) {
            System.out.println(address.toString());
        }
    }
}