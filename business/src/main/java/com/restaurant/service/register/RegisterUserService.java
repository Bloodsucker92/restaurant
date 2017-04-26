package com.restaurant.service.register;

import com.restaurant.dao.beans.User;
import com.restaurant.dao.exceptions.DaoException;
import com.restaurant.dao.impl.UserDaoImpl;
import com.restaurant.dao.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


/* Provides service methods for a new user registration operation */

public class RegisterUserService {

    private static Logger log = Logger.getLogger(RegisterUserService.class);
    private static RegisterUserService instance;
    private UserDaoImpl userDao = UserDaoImpl.getInstance();
    private HibernateUtil hibernateUtil = HibernateUtil.getHibernateUtil();
    private Session session = hibernateUtil.getSession();
    private Transaction transaction;

    private RegisterUserService() {
    }

    public static RegisterUserService getInstance() {
        if (instance == null) {
            instance = new RegisterUserService();
        }
        return instance;
    }

    /* Accesses user dao to register a new user into a database */

    public void registerUser(String username, String userpassword) throws DaoException {

        User user = new User();
        user.setUsername(username);
        user.setUserpassword(userpassword);
        user.setAccess("u");
        try {
            session = hibernateUtil.getSession();
            transaction = session.beginTransaction();
            userDao.saveOrUpdate(user);
            session.flush();
            transaction.commit();
        } catch (HibernateException e) {
            log.error("Error registering new user " + e);
            transaction.rollback();
            throw new DaoException(e);
        }
    }
}
