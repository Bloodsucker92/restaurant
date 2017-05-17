package com.restaurant.dao.dao;


import com.restaurant.dao.dao.exceptions.DaoException;
import org.hibernate.Session;
import java.io.Serializable;


public interface BaseDao<T> {

    void update(T t) throws DaoException;

    int add (T t) throws DaoException;

    T get(Serializable id) throws DaoException;

    void delete(T t) throws DaoException;

    Session getSession();

}
