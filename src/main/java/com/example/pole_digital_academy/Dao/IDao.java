package com.example.pole_digital_academy.Dao;

import java.util.List;

public interface IDao<T> {
    int delete(int id) throws Exception;
    List<T> getAll()   throws Exception;
    T findById(int id)  throws Exception;
    int insert(T entity) throws Exception;
    int update(T entity) throws Exception;

}
