package com.restaurant.dao.dao;


import com.restaurant.dao.exceptions.DaoException;
import org.hibernate.Session;

import java.io.Serializable;

public interface BaseDao<T> {

    void saveOrUpdate(T t) throws DaoException;

    T get(Serializable id) throws DaoException;

    T load(Serializable id) throws DaoException;

    void delete(T t) throws DaoException;

    Session getSession();

}
