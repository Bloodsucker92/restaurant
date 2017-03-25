package com.restaurant.logics.register;

import com.restaurant.dao.beans.User;
import com.restaurant.dao.userDAO.UserDAOImpl;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.SQLException;

/* Provides service methods for a new user registration operation */

public class RegisterUserLogic {

    private static RegisterUserLogic instance;
    private UserDAOImpl userDAO = UserDAOImpl.getInstance();

    private RegisterUserLogic() throws NamingException, SQLException {
    }

    public static RegisterUserLogic getInstance() throws NamingException, SQLException {
        if (instance == null) {
            instance = new RegisterUserLogic();
        }
        return instance;
    }

    /* Accesses user dao to register a new user into a database */

    public User registerUser(String username, String userpassword) throws SQLException, NamingException {
        User user = userDAO.addUser(username, userpassword);
        return user;
    }
}
