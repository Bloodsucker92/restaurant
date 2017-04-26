package com.restaurant.service.users;


import com.restaurant.dao.beans.User;
import com.restaurant.dao.exceptions.DaoException;
import com.restaurant.dao.impl.UserDaoImpl;
import com.restaurant.dao.util.HibernateUtil;
import com.restaurant.service.login.LoginService;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


/* Provides service methods for deleting a certain user from the databse */

public class DeleteUserService {

    private static Logger log = Logger.getLogger(LoginService.class);
    private UserDaoImpl userDao = UserDaoImpl.getInstance();
    private static DeleteUserService instance;
    private HibernateUtil hibernateUtil = HibernateUtil.getHibernateUtil();
    private Session session = hibernateUtil.getSession();
    private Transaction transaction;

    private DeleteUserService() {
    }

    public static DeleteUserService getInstance() {
        if (instance == null) {
            instance = new DeleteUserService();
        }
        return instance;
    }

    /* Checks if a certain user exists and if so, accesses dao to perform a delete operation.
    *  Returns true if the user has been successfully deleted and false otherwise.
    */

    public boolean deleteUser(Integer id) throws DaoException {
        boolean userDeleted = false;
        try {
            session = hibernateUtil.getSession();
            transaction = session.beginTransaction();
            User user = userDao.get(id);
            userDao.delete(user);
            session.flush();
            transaction.commit();
            userDeleted = true;
        } catch (HibernateException e) {
            log.error("Error deleting user " + e);
            transaction.rollback();
            throw new DaoException(e);
        }

        return userDeleted;


    }


}
