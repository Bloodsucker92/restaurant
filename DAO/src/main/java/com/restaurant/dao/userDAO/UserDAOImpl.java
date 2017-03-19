package com.restaurant.dao.userDAO;


import com.restaurant.dao.beans.Course;
import com.restaurant.dao.beans.User;
import com.restaurant.dao.connectionpool.DatabaseConnectionPool;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDAOImpl implements UserDAO {

    Connection connection = null;
    User user = new User();
    private static UserDAOImpl instance;

    private UserDAOImpl() throws SQLException, IOException, ClassNotFoundException{
    }

    public static UserDAOImpl getInstance() {
        try {
            if (instance == null) {
                instance = new UserDAOImpl();
            }
        }
        catch (ClassNotFoundException | SQLException | IOException e){
            e.printStackTrace();
        }
        return instance;
    }


    @Override
    public User getUser(String username, String userpassword) throws SQLException, NamingException {

        connection = DatabaseConnectionPool.getConnection();
        PreparedStatement myStatement = connection.prepareStatement("SELECT username," +
                " password, access" +
                " FROM restaurant.user" +
                " WHERE username=? AND password=?;");
        myStatement.setString(1, username);
        myStatement.setString(2, userpassword);
        ResultSet rst = myStatement.executeQuery();
        while (rst.next()) {
            user.setUsername(rst.getString("username"));
            user.setUserpassword(rst.getString("password"));
            user.setAccess(rst.getString("access"));
        }
        rst.close();
        myStatement.close();
        connection.close();
        return user;
    }

    public User addUser(String username, String userpassword) throws SQLException, NamingException {

        connection = DatabaseConnectionPool.getConnection();
        PreparedStatement myStatement = connection.prepareStatement("INSERT IGNORE INTO user (username, password, access) VALUES (?, ?, ?);");
        myStatement.setString(1, username);
        myStatement.setString(2, userpassword);
        myStatement.setString(3, "u");
        myStatement.executeUpdate();
        getUser(username, userpassword);
        user.setUsername(username);
        user.setUserpassword(userpassword);
        user.setAccess("u");
        myStatement.close();
        connection.close();
        return user;

    }

}
