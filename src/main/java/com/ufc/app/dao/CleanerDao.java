package com.ufc.app.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.ufc.app.model.Cleaner;

public class CleanerDao implements Dao<Cleaner> {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public CleanerDao(EntityManagerFactory entityManagerFactory, EntityManager entityManager) {
        this.entityManagerFactory = entityManagerFactory;
        this.entityManager = entityManager;
    }

    @Override
    public Cleaner get(long id) {
        return this.entityManager.find(Cleaner.class, id);
    }

    @Override
    public List<Cleaner> getAll() {

        List<Cleaner> cleaners = null;

        try {
            CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
            CriteriaQuery<Cleaner> cq = cb.createQuery(Cleaner.class);
            Root<Cleaner> rootEntry = cq.from(Cleaner.class);
            CriteriaQuery<Cleaner> all = cq.select(rootEntry);
            TypedQuery<Cleaner> allQuery = this.entityManager.createQuery(all);

            cleaners = allQuery.getResultList();
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
        } finally {
            this.entityManager.close();
            this.entityManagerFactory.close();
        }
        return cleaners;
    }

    @Override
    public void save(Cleaner cleaner) {
        try {
            this.entityManager.getTransaction().begin();
            this.entityManager.persist(cleaner);
            this.entityManager.getTransaction().commit();
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
        } finally {
            this.entityManager.close();
            this.entityManagerFactory.close();
        }
    }

    @Override
    public void update(Cleaner cleaner, long id) {
        try {
            this.entityManager.getTransaction().begin();

            Cleaner newCleaner = this.entityManager.find(Cleaner.class, id);
            newCleaner.setName(cleaner.getName());
            newCleaner.setSalary(cleaner.getSalary());
            newCleaner.setBirthday(cleaner.getBirthday());
            newCleaner.setCompanyPosition(cleaner.getCompanyPosition());

            this.entityManager.merge(newCleaner);
            this.entityManager.getTransaction().commit();

        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
        } finally {
            this.entityManager.close();
            this.entityManagerFactory.close();
        }
    }

    @Override
    public void delete(Cleaner cleaner) {
        try {
            this.entityManager.getTransaction().begin();
            this.entityManager.remove(cleaner);
            this.entityManager.getTransaction().commit();
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
        } finally {
            this.entityManager.close();
            this.entityManagerFactory.close();
        }
    }
}