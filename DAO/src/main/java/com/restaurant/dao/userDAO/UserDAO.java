package com.restaurant.dao.userDAO;

import com.restaurant.dao.beans.User;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;

/* Defines common methods for CRUD-operations with User POJO */

public interface UserDAO {

    User getUser(String login, String password) throws SQLException, NamingException;

    User addUser(String username, String userpassword) throws SQLException, NamingException;

    ArrayList<User> showUsers() throws  SQLException, NamingException;

    boolean deleteUser(String username, String userpassword) throws SQLException, NamingException;


}
