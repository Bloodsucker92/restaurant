package com.restaurant.dao.dao.impl;

import com.restaurant.dao.pojos.User;
import com.restaurant.dao.dao.UserBaseDao;
import com.restaurant.dao.dao.exceptions.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository(value = "userDaoImpl")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserBaseDao<User> {

    private static Logger logger = LogManager.getLogger(UserDaoImpl.class);

    @Autowired
    private UserDaoImpl (SessionFactory sessionFactory) {
        super(sessionFactory);
    }


    public User getUserByLoginAndPassword(String login, String password) throws DaoException {

        User user;
        try {
            String hql = "SELECT U FROM User U WHERE U.username=:login AND U.userpassword=:password";
            Query query = getSession().createQuery(hql);
            query.setParameter("login", login);
            query.setParameter("password", password);
            user = (User) query.uniqueResult();
        }
        catch (HibernateException e) {
            throw new DaoException(UserDaoImpl.class, "Error getting user by login and password", e);
        }

        return user;
    }

    public User getUserByLogin(String login) throws DaoException {
        User user;
        try {
            String hql = "SELECT U FROM User U WHERE U.username=:login";
            Query query = getSession().createQuery(hql);
            query.setParameter("login", login);
            user = (User) query.uniqueResult();
        }
        catch (HibernateException e) {
            throw new DaoException(UserDaoImpl.class, "Error getting user by login and password", e);
        }

        return user;
    }

    public ArrayList<User> getAllUsers () throws DaoException {
        List<User> results;
        try {
            Criteria criteria = getSession().createCriteria(User.class);
            results = criteria.list();
        }
        catch (HibernateException e) {
            throw new DaoException(UserDaoImpl.class, "Error getting all users", e);
        }
        return (ArrayList<User>) results;
    }

}
