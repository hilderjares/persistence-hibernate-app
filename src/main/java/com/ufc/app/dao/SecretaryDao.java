package com.ufc.app.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.ufc.app.model.Secretary;

public class SecretaryDao implements Dao<Secretary> {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public SecretaryDao(EntityManagerFactory entityManagerFactory, EntityManager entityManager) {
        this.entityManagerFactory = entityManagerFactory;
        this.entityManager = entityManager;
    }

    @Override
    public Secretary get(long id) {
        return this.entityManager.find(Secretary.class, id);
    }

    @Override
    public List<Secretary> getAll() {

        List<Secretary> secretaries = null;

        try {
            CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
            CriteriaQuery<Secretary> cq = cb.createQuery(Secretary.class);
            Root<Secretary> rootEntry = cq.from(Secretary.class);
            CriteriaQuery<Secretary> all = cq.select(rootEntry);
            TypedQuery<Secretary> allQuery = this.entityManager.createQuery(all);

            secretaries = allQuery.getResultList();
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
        } finally {
            this.entityManager.close();
            this.entityManagerFactory.close();
        }
        return secretaries;
    }

    @Override
    public void save(Secretary secretary) {
        try {
            this.entityManager.getTransaction().begin();
            this.entityManager.persist(secretary);
            this.entityManager.getTransaction().commit();
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
        } finally {
            this.entityManager.close();
            this.entityManagerFactory.close();
        }
    }

    @Override
    public void update(Secretary secretary, long id) {
        try {
            this.entityManager.getTransaction().begin();

            Secretary newSecretary = this.entityManager.find(Secretary.class, id);
            newSecretary.setName(secretary.getName());
            newSecretary.setSalary(secretary.getSalary());
            newSecretary.setBirthday(secretary.getBirthday());
            newSecretary.setEducationLevel(secretary.getEducationLevel());
            newSecretary.setWorkload(secretary.getWorkload());

            this.entityManager.merge(newSecretary);
            this.entityManager.getTransaction().commit();

        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
        } finally {
            this.entityManager.close();
            this.entityManagerFactory.close();
        }
    }

    @Override
    public void delete(Secretary secretary) {
        try {
            this.entityManager.getTransaction().begin();
            this.entityManager.remove(secretary);
            this.entityManager.getTransaction().commit();
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
        } finally {
            this.entityManager.close();
            this.entityManagerFactory.close();
        }
    }
}