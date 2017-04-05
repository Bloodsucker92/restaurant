package com.restaurant.service.register;

import com.restaurant.dao.beans.User;
import com.restaurant.dao.userDAO.UserDAOImpl;

import javax.naming.NamingException;
import java.sql.SQLException;

/* Provides service methods for a new user registration operation */

public class RegisterUserService {

    private static RegisterUserService instance;
    private UserDAOImpl userDAO = UserDAOImpl.getInstance();

    private RegisterUserService() throws NamingException, SQLException {
    }

    public static RegisterUserService getInstance() throws NamingException, SQLException {
        if (instance == null) {
            instance = new RegisterUserService();
        }
        return instance;
    }

    /* Accesses user dao to register a new user into a database */

    public User registerUser(String username, String userpassword) throws SQLException, NamingException {
        User user = userDAO.addUser(username, userpassword);
        return user;
    }
}
