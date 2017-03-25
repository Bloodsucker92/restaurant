package com.restaurant.dao.userDAO;

import com.restaurant.dao.beans.User;
import com.restaurant.dao.connectionpool.ConnectionPool;
import com.restaurant.dao.connectionpool.MysqlConnectionPool;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/* Implements methods for CRUD-operations with User POJO */

public class UserDAOImpl implements UserDAO {

    private static UserDAOImpl instance;
    private ConnectionPool connectionPool;

    private final String GET_USER_QUERY = "SELECT username," +
            " password, access" +
            " FROM user" +
            " WHERE username=? AND password=?;";

    private final String ADD_USER_QUERY = "INSERT INTO user (username, password, access) VALUES (?, ?, ?);";

    private final String SHOW_USERS_QUERY = "SELECT username, password, access FROM user;";

    private final String DELETE_USER_SQL = "DELETE FROM user WHERE username = ? and password = ?;";

    public UserDAOImpl(ConnectionPool connectionPool) throws SQLException {
        setConnectionPool(connectionPool);
    }

    public static UserDAOImpl getInstance() throws NamingException, SQLException {
        if (instance == null) {
                instance = new UserDAOImpl(MysqlConnectionPool.getInstance());
        }
        return instance;}

    @Override
    public User getUser(String username, String userpassword) throws SQLException {
        Connection connection = connectionPool.getConnection();
        PreparedStatement statement = connection.prepareStatement(GET_USER_QUERY);
        statement.setString(1, username);
        statement.setString(2, userpassword);
        ResultSet rst = statement.executeQuery();
        User user = null;
        if(rst.first()) {
            user =  new User(
                    rst.getString("username"),
                    rst.getString("password"),
                    rst.getString("access")
            );
        }
        rst.close();
        statement.close();
        connection.close();
        return user;
    }

    public User addUser(String username, String userpassword) throws SQLException {
        Connection connection = connectionPool.getConnection();
        PreparedStatement statement = connection.prepareStatement(ADD_USER_QUERY);
        statement.setString(1, username);
        statement.setString(2, userpassword);
        statement.setString(3, "u");
        statement.executeUpdate();
        User user = getUser(username, userpassword);
        statement.close();
        connection.close();
        return user;
    }

    @Override
    public ArrayList<User> showUsers() throws SQLException, NamingException {
        Connection connection = connectionPool.getConnection();
        List<User> usersList = new ArrayList();
        PreparedStatement statement = connection.prepareStatement(SHOW_USERS_QUERY);
        ResultSet rst = statement.executeQuery();
        while (rst.next()) {
            User user = new User();
            user.setUsername(rst.getString("username"));
            user.setUserpassword(rst.getString("password"));
            user.setAccess(rst.getString("access"));
            usersList.add(user);
        }
        rst.close();
        statement.close();
        connection.close();

        return (ArrayList<User>) usersList;
    }

    @Override
    public boolean deleteUser(String username, String userpassword) throws SQLException, NamingException {
        Connection connection = connectionPool.getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE_USER_SQL);
        statement.setString(1, username);
        statement.setString(2, userpassword);
        statement.executeUpdate();
        statement.close();
        connection.close();
        return true;
    }


    public void setConnectionPool(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }
}
