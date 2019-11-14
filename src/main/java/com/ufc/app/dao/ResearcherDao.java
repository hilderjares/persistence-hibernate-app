package com.ufc.app.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.ufc.app.model.Researcher;

public class ResearcherDao implements Dao<Researcher> {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public ResearcherDao(EntityManagerFactory entityManagerFactory, EntityManager entityManager) {
        this.entityManagerFactory = entityManagerFactory;
        this.entityManager = entityManager;
    }

    @Override
    public Researcher get(long id) {
        return this.entityManager.find(Researcher.class, id);
    }

    @Override
    public List<Researcher> getAll() {

        List<Researcher> researchers = null;

        try {
            CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
            CriteriaQuery<Researcher> cq = cb.createQuery(Researcher.class);
            Root<Researcher> rootEntry = cq.from(Researcher.class);
            CriteriaQuery<Researcher> all = cq.select(rootEntry);
            TypedQuery<Researcher> allQuery = this.entityManager.createQuery(all);

            researchers = allQuery.getResultList();
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
        } finally {
            this.entityManager.close();
            this.entityManagerFactory.close();
        }
        return researchers;
    }

    @Override
    public void save(Researcher researcher) {
        try {
            this.entityManager.getTransaction().begin();
            this.entityManager.persist(researcher);
            this.entityManager.getTransaction().commit();
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
        } finally {
            this.entityManager.close();
            this.entityManagerFactory.close();
        }
    }

    @Override
    public void update(Researcher researcher, long id) {
        try {
            this.entityManager.getTransaction().begin();

            Researcher newResearcher = this.entityManager.find(Researcher.class, id);
            newResearcher.setName(researcher.getName());
            newResearcher.setSalary(researcher.getSalary());
            newResearcher.setBirthday(researcher.getBirthday());

            this.entityManager.merge(newResearcher);
            this.entityManager.getTransaction().commit();

        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
        } finally {
            this.entityManager.close();
            this.entityManagerFactory.close();
        }
    }

    @Override
    public void delete(Researcher researcher) {
        try {
            this.entityManager.getTransaction().begin();
            this.entityManager.remove(researcher);
            this.entityManager.getTransaction().commit();
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
        } finally {
            this.entityManager.close();
            this.entityManagerFactory.close();
        }
    }
}