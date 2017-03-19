package com.restaurant.logics.register;

import com.restaurant.dao.beans.User;
import com.restaurant.dao.userDAO.UserDAOImpl;

import javax.naming.NamingException;
import java.sql.SQLException;

public class RegisterUserLogic {

    private static RegisterUserLogic instance;
    private static UserDAOImpl userDAO = UserDAOImpl.getInstance();

    private RegisterUserLogic(){
    }

    public static RegisterUserLogic getInstance() {
        if (instance == null) {
            instance = new RegisterUserLogic();
        }
        return instance;
    }

    public User registerUser(String username, String userpassword) throws SQLException, NamingException {
        User user = userDAO.addUser(username, userpassword);
        return user;
    }
}
