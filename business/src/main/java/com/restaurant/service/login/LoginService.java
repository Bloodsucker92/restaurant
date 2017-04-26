package com.restaurant.service.login;

import com.restaurant.dao.beans.User;
import com.restaurant.dao.exceptions.DaoException;
import com.restaurant.dao.impl.UserDaoImpl;
import com.restaurant.dao.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


/* Provides service methods which take action when a user fills in the login form of the app*/

public class LoginService {

    private static Logger log = Logger.getLogger(LoginService.class);
    private UserDaoImpl userDao = UserDaoImpl.getInstance();
    private static LoginService instance;
    private HibernateUtil hibernateUtil = HibernateUtil.getHibernateUtil();
    private Session session;
    private Transaction transaction;


    private LoginService() {

    }

    public static LoginService getInstance() {
        if (instance == null) {
            instance = new LoginService();
        }
        return instance;
    }

    public User getUserById(Integer id) throws DaoException {
        User user = new User();
        try {
            session = hibernateUtil.getSession();
            transaction = session.beginTransaction();
            user = userDao.get(id);
            session.flush();
            transaction.commit();
        } catch (HibernateException e) {
            log.error("Error getting user by id" + e);
            transaction.rollback();
            throw new DaoException(e);

        }

        return user;
    }


  /* Checks if a certain user was registered and is present in the database */

    public User findUserByLoginAndPassword(String enterLogin, String enterPassword) throws DaoException {
        User user = new User();
        try {
            session = hibernateUtil.getSession();
            transaction = session.beginTransaction();
            user = userDao.getUserByLoginAndPassword(enterLogin, enterPassword);
            session.flush();
            transaction.commit();
        } catch (HibernateException e) {
            log.error("Error getting User by login and password" + e);
            transaction.rollback();
            throw new DaoException(e);
        }
        return user;
    }

}