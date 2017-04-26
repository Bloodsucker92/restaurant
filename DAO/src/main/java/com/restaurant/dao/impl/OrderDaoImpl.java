package com.restaurant.dao.impl;


import com.restaurant.dao.beans.Order;
import com.restaurant.dao.dao.OrderBaseDao;
import com.restaurant.dao.exceptions.DaoException;
import com.restaurant.dao.util.HibernateUtil;
import com.thoughtworks.qdox.model.expression.Or;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderBaseDao {

    private static Logger log = Logger.getLogger(OrderDaoImpl.class);
    private HibernateUtil util = HibernateUtil.getHibernateUtil();
    private static OrderDaoImpl instance;

    public static OrderDaoImpl getInstance(){
        if (instance == null) {
            instance = new OrderDaoImpl();
        }
        return instance;
    }

    public void saveOrUpdate(Order order) throws DaoException {
        util.getSession().save(order);

    }

    public ArrayList<Order> getOrders () throws DaoException {
        Criteria criteria = getSession().createCriteria(Order.class);
        List<Order> list = criteria.list();
        return (ArrayList<Order>) list;
    }

    public Long getTotalOrdersCount(Integer id) throws DaoException {
        Criteria criteria = getSession().createCriteria(Order.class);
        criteria.add(Restrictions.eq("user.id", id));
        criteria.setProjection(Projections.rowCount());
        Long count = (Long) criteria.uniqueResult();
        return count;
    }

    public ArrayList<Order> getOrdersOfOneUser (Integer userId) throws DaoException{
        String hql = "SELECT O FROM Order O WHERE O.user.id=:id";
        Query query = getSession().createQuery(hql);
        query.setParameter("id", userId);
//        Criteria criteria = getSession().createCriteria(Order.class);
//        criteria.add(Restrictions.eq("user.id", userId));
        List ordersList = query.list();
        return (ArrayList<Order>) ordersList;
    }

    @Override
    public Object get(Serializable id) throws DaoException {
        Order order = (Order) getSession().get(Order.class, id);
        return order;
    }

    @Override
    public Object load(Serializable id) throws DaoException {
        return null;
    }

    public void delete(Order order) throws DaoException {
        util.getSession().delete(order);

    }

    @Override
    public Session getSession() {
        return util.getSession();
    }


}
