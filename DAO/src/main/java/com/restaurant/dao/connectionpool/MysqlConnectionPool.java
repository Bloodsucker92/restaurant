package com.restaurant.dao.connectionpool;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class MysqlConnectionPool implements ConnectionPool {
    private static MysqlConnectionPool instance;
    private DataSource dataSource;

    public MysqlConnectionPool() {
        try {
            dataSource = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/RestaurantDB");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public static MysqlConnectionPool getInstance() {
        if(instance == null) {
            instance = new MysqlConnectionPool();
        }

        return instance;
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
