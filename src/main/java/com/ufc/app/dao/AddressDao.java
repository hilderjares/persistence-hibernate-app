package com.ufc.app.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.ufc.app.model.Address;

public class AddressDao implements Dao<Address> {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public AddressDao(EntityManagerFactory entityManagerFactory, EntityManager entityManager) {
        this.entityManagerFactory = entityManagerFactory;
        this.entityManager = entityManager;
    }

    @Override
    public Address get(long id) {
        return this.entityManager.find(Address.class, id);
    }

    @Override
    public List<Address> getAll() {

        List<Address> addresses = null;

        try {
            CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
            CriteriaQuery<Address> cq = cb.createQuery(Address.class);
            Root<Address> rootEntry = cq.from(Address.class);
            CriteriaQuery<Address> all = cq.select(rootEntry);
            TypedQuery<Address> allQuery = this.entityManager.createQuery(all);

            addresses = allQuery.getResultList();
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
        } finally {
            this.entityManager.close();
            this.entityManagerFactory.close();
        }
        return addresses;
    }

    @Override
    public void save(Address address) {
        try {
            this.entityManager.getTransaction().begin();
            this.entityManager.persist(address);
            this.entityManager.getTransaction().commit();
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
        } finally {
            this.entityManager.close();
            this.entityManagerFactory.close();
        }
    }

    @Override
    public void update(Address address, long id) {
        try {
            this.entityManager.getTransaction().begin();

            Address newAddress = this.entityManager.find(Address.class, id);
            newAddress.setCity(address.getCity());
            newAddress.setState(address.getState());
            newAddress.setStreet(address.getStreet());
            newAddress.setZipcode(address.getZipcode());

            this.entityManager.merge(newAddress);
            this.entityManager.getTransaction().commit();

        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    @Override
    public void delete(Address address) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(address);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}