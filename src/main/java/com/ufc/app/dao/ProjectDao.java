package com.ufc.app.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.ufc.app.model.Project;

public class ProjectDao implements Dao<Project> {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public ProjectDao(EntityManagerFactory entityManagerFactory, EntityManager entityManager) {
        this.entityManagerFactory = entityManagerFactory;
        this.entityManager = entityManager;
    }

    @Override
    public Project get(long id) {
        return this.entityManager.find(Project.class, id);
    }

    @Override
    public List<Project> getAll() {

        List<Project> projects = null;

        try {
            CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
            CriteriaQuery<Project> cq = cb.createQuery(Project.class);
            Root<Project> rootEntry = cq.from(Project.class);
            CriteriaQuery<Project> all = cq.select(rootEntry);
            TypedQuery<Project> allQuery = this.entityManager.createQuery(all);

            projects = allQuery.getResultList();
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
        } finally {
            this.entityManager.close();
            this.entityManagerFactory.close();
        }
        return projects;
    }

    @Override
    public void save(Project project) {
        try {
            this.entityManager.getTransaction().begin();
            this.entityManager.persist(project);
            this.entityManager.getTransaction().commit();
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
        } finally {
            this.entityManager.close();
            this.entityManagerFactory.close();
        }
    }

    @Override
    public void update(Project project, long id) {
        try {
            this.entityManager.getTransaction().begin();

            Project newProject = this.entityManager.find(Project.class, id);
            
            newProject.setName(project.getName());
            newProject.setPeriod(project.getPeriod());
            newProject.setDepartment(project.getDepartment());

            this.entityManager.merge(newProject);
            this.entityManager.getTransaction().commit();

        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
        } finally {
            this.entityManager.close();
            this.entityManagerFactory.close();
        }
    }

    @Override
    public void delete(Project project) {
        try {
            this.entityManager.getTransaction().begin();
            this.entityManager.remove(project);
            this.entityManager.getTransaction().commit();
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
        } finally {
            this.entityManager.close();
            this.entityManagerFactory.close();
        }
    }
}