package com.restaurant.dao.dao.impl;


import com.restaurant.dao.pojos.Order;
import com.restaurant.dao.dao.OrderBaseDao;
import com.restaurant.dao.dao.exceptions.DaoException;
import org.apache.logging.log4j.LogManager;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository(value = "orderDaoImpl")
public class OrderDaoImpl extends BaseDaoImpl<Order> implements OrderBaseDao {

    private static org.apache.logging.log4j.Logger log = LogManager.getLogger(OrderDaoImpl.class);

    @Autowired
    public OrderDaoImpl (SessionFactory sessionFactory) {
        super(sessionFactory);
    }


    public ArrayList<Order> getOrders () throws DaoException {
        List<Order> list;
        try {
            Criteria criteria = getSession().createCriteria(Order.class);
            list = criteria.list();
        }
        catch (HibernateException e) {
            throw new DaoException(OrderDaoImpl.class, "Error getting all orders", e);
        }
        return (ArrayList<Order>) list;
    }

    public Order getOrderById (int orderId) throws DaoException {
        Order order;
        try {
            order =(Order) getSession().get(Order.class, orderId);
        }
        catch (HibernateException e) {
            throw new DaoException(OrderDaoImpl.class, "Error getting order by id", e);
        }
        return order;

    }

}
