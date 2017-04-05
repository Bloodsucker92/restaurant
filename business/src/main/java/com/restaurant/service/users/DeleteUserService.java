package com.restaurant.service.users;


import com.restaurant.dao.userDAO.UserDAOImpl;
import com.restaurant.service.login.LoginService;

import javax.naming.NamingException;
import java.sql.SQLException;

/* Provides service methods for deleting a certain user from the databse */

public class DeleteUserService {

    private UserDAOImpl userDAO = UserDAOImpl.getInstance();
    private static DeleteUserService instance;
    private LoginService loginService = LoginService.getInstance();

    private DeleteUserService() throws SQLException, NamingException {
    }

    public static DeleteUserService getInstance() throws SQLException, NamingException {
        if (instance == null) {
            instance = new DeleteUserService();
        }
        return instance;
    }

    /* Checks if a certain user exists and if so, accesses dao to perform a delete operation.
    *  Returns true if the user has been successfully deleted and false otherwise.
    */

    public boolean deleteUser (String username, String userpassword) throws SQLException, NamingException {
            if (loginService.ifUserExists(username, userpassword)){
                userDAO.deleteUser(username, userpassword);
                return true;
            }
            else return false;

    }


}
