package com.ufc.app.dao;

import java.util.List;

public interface Dao<T> {

    T get(long id);

    List<T> getAll();

    void save(T t);

    void update(T t, long id);

    void delete(T t);
}