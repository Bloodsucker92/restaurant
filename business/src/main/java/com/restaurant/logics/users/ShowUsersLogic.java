package com.restaurant.logics.users;


import com.restaurant.dao.beans.User;
import com.restaurant.dao.userDAO.UserDAOImpl;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/* Provides service methods that show the existing users*/

public class ShowUsersLogic {
    private UserDAOImpl userDAO = UserDAOImpl.getInstance();
    private static ShowUsersLogic instance;

    private ShowUsersLogic() throws NamingException, SQLException {
    }

    public static ShowUsersLogic getInstance() throws NamingException, SQLException {
        if (instance == null) {
            instance = new ShowUsersLogic();
        }
        return instance;
    }

    /* Accesses dao to return a list of all the users registered.
    *  Only users with 'u' access which means 'user' are added to the list, i.e. those
    *  who have 'a' access type which stands for 'admin' are ignored. */

    public ArrayList<User> showUsers() throws SQLException, NamingException {
        ArrayList<User> userList = userDAO.showUsers();
        List<User> onlyUserList = new ArrayList<User>();
        for (User user : userList) {
            if (user.getAccess().equals("u")){
                onlyUserList.add(user);
            }
        }

        return (ArrayList<User>) onlyUserList;
    }
}
