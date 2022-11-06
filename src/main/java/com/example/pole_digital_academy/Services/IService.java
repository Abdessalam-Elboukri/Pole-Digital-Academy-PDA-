package com.example.pole_digital_academy.Services;

import java.util.List;

public interface IService<T> {
    int delete(int id) ;
    List<T> getAll()   ;
    T findById(int id) ;
    int insert(T entity) ;
    int update(T entity) ;

}
