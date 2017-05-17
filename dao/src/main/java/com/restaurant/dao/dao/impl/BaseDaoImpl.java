package com.restaurant.dao.dao.impl;

import com.restaurant.dao.dao.BaseDao;
import com.restaurant.dao.dao.exceptions.DaoException;
import org.apache.logging.log4j.LogManager;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

@Repository(value = "baseDaoImpl")
public class BaseDaoImpl<T> implements BaseDao<T> {

    private static org.apache.logging.log4j.Logger log = LogManager.getLogger(BaseDaoImpl.class);
    private SessionFactory sessionFactory;

    @Autowired
    public BaseDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public int add(T t) throws DaoException {
        int id;
        try {
            id = (int) getSession().save(t);
            log.info("Save:" + t);
        } catch (HibernateException e) {
            throw new DaoException(getPersistentClass(), "Error adding an entity to database", e);
        }
        return id;
    }

    @Override
    public void update(T t) throws DaoException {
        try {
            getSession().update(t);
            log.info("Update:" + t);
        } catch (HibernateException e) {
            throw new DaoException(getPersistentClass(), "Error updating entity", e);
        }
    }

    @Override
    public T get(Serializable id) throws DaoException {
        try {
            log.info("Get:" + id);
            return (T) getSession().load(getPersistentClass(), id);
        } catch (HibernateException e) {
            throw new DaoException(getPersistentClass(), "Error getting entity", e);
        }
    }


    @Override
    public void delete(T t) throws DaoException {
        try {
        log.info("Delete:" + t);
        getSession().delete(t);}
        catch (HibernateException e) {
            throw new DaoException(getPersistentClass(), "Error deleting entity", e);
        }
    }

    protected Criteria createEntityCriteria() {
        return getSession().createCriteria(getPersistentClass());
    }

    private Class getPersistentClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }


}
