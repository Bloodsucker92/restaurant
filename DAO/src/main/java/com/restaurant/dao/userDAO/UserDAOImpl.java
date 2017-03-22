package com.restaurant.dao.userDAO;

import com.restaurant.dao.beans.User;
import com.restaurant.dao.connectionpool.ConnectionPool;
import com.restaurant.dao.connectionpool.MysqlConnectionPool;
import java.io.IOException;
import java.sql.*;

public class UserDAOImpl implements UserDAO {

    private static UserDAOImpl instance;
    private ConnectionPool connectionPool;

    private final String GET_USER_QUERY = "SELECT username," +
            " password, access" +
            " FROM user" +
            " WHERE username=? AND password=?;";

    private final String ADD_USER_QUERY = "INSERT INTO user (username, password, access) VALUES (?, ?, ?);";

    public UserDAOImpl(ConnectionPool connectionPool) throws SQLException, IOException {
        setConnectionPool(connectionPool);
    }

    public static UserDAOImpl getInstance() {
        if (instance == null) {
            try {
                instance = new UserDAOImpl(MysqlConnectionPool.getInstance());
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

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

    public void setConnectionPool(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }
}
