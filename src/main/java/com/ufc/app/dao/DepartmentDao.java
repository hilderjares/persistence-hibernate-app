package com.ufc.app.dao;

import com.ufc.app.model.Department;

import java.util.List;
import java.util.Optional;

public class DepartmentDao implements Dao<Department> {

    @Override
    public Optional<Department> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Department> getAll() {
        return null;
    }

    @Override
    public void save(Department department) {

    }

    @Override
    public void update(Department department, String[] params) {

    }

    @Override
    public void delete(Department department) {

    }
}
