package com.restaurant.service.users;


import com.restaurant.dao.beans.User;
import com.restaurant.dao.exceptions.DaoException;
import com.restaurant.dao.impl.UserDaoImpl;
import com.restaurant.dao.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/* Provides service methods that show the existing users*/

public class ShowUsersService {

    private static Logger log = Logger.getLogger(ShowUsersService.class);
    private UserDaoImpl userDao = UserDaoImpl.getInstance();
    private static ShowUsersService instance;
    private HibernateUtil hibernateUtil = HibernateUtil.getHibernateUtil();
    private Session session = hibernateUtil.getSession();
    private Transaction transaction;

    private ShowUsersService() {
    }

    public static ShowUsersService getInstance() {
        if (instance == null) {
            instance = new ShowUsersService();
        }
        return instance;
    }

    /* Accesses dao to return a list of all the users registered.
    *  Only users with 'u' access which means 'user' are added to the list, i.e. those
    *  who have 'a' access type which stands for 'admin' are ignored. */

    public ArrayList<User> showUsers() throws DaoException {
        List<User> onlyUserList = new ArrayList<User>();
        try {
            session = hibernateUtil.getSession();
            transaction = session.beginTransaction();
            ArrayList<User> userList = userDao.getAllUsers();
            for (User user : userList) {
                if (user.getAccess().equals("u")) {
                    onlyUserList.add(user);
                }
            }
            session.flush();
            transaction.commit();
        } catch (HibernateException e) {
            log.error("Error getting all users " + e);
            transaction.rollback();
            throw new DaoException(e);
        }
        return (ArrayList<User>) onlyUserList;
    }
}
