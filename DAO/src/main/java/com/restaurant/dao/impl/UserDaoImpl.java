package com.restaurant.dao.impl;

import com.restaurant.dao.beans.User;
import com.restaurant.dao.dao.UserBaseDao;
import com.restaurant.dao.exceptions.DaoException;
import com.restaurant.dao.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.*;

import javax.naming.NamingException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserBaseDao<User> {

    private static Logger logger = Logger.getLogger(UserDaoImpl.class);
    HibernateUtil util = HibernateUtil.getHibernateUtil();
    private static UserDaoImpl instance;

    public UserDaoImpl() {

    }

    public static UserDaoImpl getInstance() {
        if (instance == null) {
            instance = new UserDaoImpl();
        }
        return instance;
    }

    public User getUserByLoginAndPassword(String login, String password) throws DaoException {

        User user = new User();

        String hql = "SELECT U FROM User U WHERE U.username=:login AND U.userpassword=:password";
        Query query = getSession().createQuery(hql);
        query.setParameter("login", login);
        query.setParameter("password", password);
        user = (User) query.uniqueResult();

        return user;
    }

    public User getUserByLogin(String login) throws DaoException {
        User user = new User();
        String hql = "SELECT U FROM User U WHERE U.username=:login";
        Query query = getSession().createQuery(hql);
        query.setParameter("login", login);
        user = (User) query.uniqueResult();

        return user;
    }

    public ArrayList<User> getAllUsers () throws DaoException {
        Criteria criteria = getSession().createCriteria(User.class);
        List results = criteria.list();
        return (ArrayList<User>) results;
    }

    @Override
    public void saveOrUpdate(User user) throws DaoException {
        util.getSession().save(user);
    }


    @Override
    public User get(Serializable id) throws DaoException {
        User user = (User) getSession().get(User.class, id);
        return user;
    }

    @Override
    public User load(Serializable id) throws DaoException {
        return null;
    }

    @Override
    public void delete(User user) throws DaoException {
        getSession().delete(user);
    }


    @Override
    public Session getSession() {
        return util.getSession();

    }
}
