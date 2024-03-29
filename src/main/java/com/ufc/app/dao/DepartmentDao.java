package com.ufc.app.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.ufc.app.model.Department;

public class DepartmentDao implements Dao<Department> {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public DepartmentDao(EntityManagerFactory entityManagerFactory, EntityManager entityManager) {
        this.entityManagerFactory = entityManagerFactory;
        this.entityManager = entityManager;
    }

    @Override
    public Department get(long id) {
        return this.entityManager.find(Department.class, id);
    }

    @Override
    public List<Department> getAll() {

        List<Department> departments = null;

        try {
            CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
            CriteriaQuery<Department> cq = cb.createQuery(Department.class);
            Root<Department> rootEntry = cq.from(Department.class);
            CriteriaQuery<Department> all = cq.select(rootEntry);
            TypedQuery<Department> allQuery = this.entityManager.createQuery(all);

            departments = allQuery.getResultList();
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
        } finally {
            this.entityManager.close();
            this.entityManagerFactory.close();
        }
        return departments;
    }

    @Override
    public void save(Department department) {
        try {
            this.entityManager.getTransaction().begin();
            this.entityManager.persist(department);
            this.entityManager.getTransaction().commit();
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
        } finally {
            this.entityManager.close();
            this.entityManagerFactory.close();
        }
    }

    @Override
    public void update(Department department, long id) {
        try {
            this.entityManager.getTransaction().begin();

            Department newDepartment = this.entityManager.find(Department.class, id);
            newDepartment.setName(department.getName());
            newDepartment.setNumber(department.getNumber());

            this.entityManager.merge(newDepartment);
            this.entityManager.getTransaction().commit();

        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
        } finally {
            this.entityManager.close();
            this.entityManagerFactory.close();
        }
    }

    @Override
    public void delete(Department department) {
        try {
            this.entityManager.getTransaction().begin();
            this.entityManager.remove(department);
            this.entityManager.getTransaction().commit();
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
        } finally {
            this.entityManager.close();
            this.entityManagerFactory.close();
        }
    }
}
