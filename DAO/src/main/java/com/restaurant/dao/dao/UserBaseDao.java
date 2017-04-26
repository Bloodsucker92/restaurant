package com.restaurant.dao.dao;


import com.restaurant.dao.exceptions.DaoException;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

public interface UserBaseDao<User> extends BaseDao<User> {

    @Override
    void saveOrUpdate(User user) throws DaoException;

    @Override
    User get(Serializable id) throws DaoException;

    @Override
    User load(Serializable id) throws DaoException;

    @Override
    void delete(User user) throws DaoException;

    @Override
    Session getSession();
}
