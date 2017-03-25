package com.restaurant.dao.connectionpool;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/*
*  Sets the connection pool for MySQL database
 */

public class MysqlConnectionPool implements ConnectionPool {
    private static MysqlConnectionPool instance;
    private DataSource dataSource;


    public MysqlConnectionPool() throws NamingException {

            // The configs for MySQL connection pool are stored in web module /webapp/META-INF/context.xml file.

            dataSource = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/RestaurantDB");
    }

    public static MysqlConnectionPool getInstance() throws NamingException {
        if(instance == null) {
            instance = new MysqlConnectionPool();
        }

        return instance;
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
