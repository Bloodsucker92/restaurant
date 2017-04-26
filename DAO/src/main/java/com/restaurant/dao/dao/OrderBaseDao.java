package com.restaurant.dao.dao;


import com.restaurant.dao.beans.Order;
import com.restaurant.dao.exceptions.DaoException;
import org.hibernate.Session;

import java.io.Serializable;

public interface OrderBaseDao<Order> extends BaseDao<Order> {

    @Override
    default void saveOrUpdate(Order o) throws DaoException {

    }

    @Override
    default Order get(Serializable id) throws DaoException {
        return null;
    }

    @Override
    default Order load(Serializable id) throws DaoException {
        return null;
    }

    @Override
    default void delete(Order o) throws DaoException {

    }

}
