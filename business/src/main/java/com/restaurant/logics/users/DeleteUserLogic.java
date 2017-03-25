package com.restaurant.logics.users;


import com.restaurant.dao.userDAO.UserDAOImpl;
import com.restaurant.logics.login.LoginLogic;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.SQLException;

/* Provides service methods for deleting a certain user from the databse */

public class DeleteUserLogic {

    private UserDAOImpl userDAO = UserDAOImpl.getInstance();
    private static DeleteUserLogic instance;
    private LoginLogic loginLogic = LoginLogic.getInstance();

    private DeleteUserLogic() throws SQLException, NamingException {
    }

    public static DeleteUserLogic getInstance() throws SQLException, NamingException {
        if (instance == null) {
            instance = new DeleteUserLogic();
        }
        return instance;
    }

    /* Checks if a certain user exists and if so, accesses dao to perform a delete operation.
    *  Returns true if the user has been successfully deleted and false otherwise.
    */

    public boolean deleteUser (String username, String userpassword) throws SQLException, NamingException {
            if (loginLogic.ifUserExists(username, userpassword)){
                userDAO.deleteUser(username, userpassword);
                return true;
            }
            else return false;

    }


}
