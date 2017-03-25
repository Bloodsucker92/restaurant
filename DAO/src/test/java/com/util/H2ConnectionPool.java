package com.util;

import com.restaurant.dao.connectionpool.ConnectionPool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/* Sets uo the connection pool for H2 database and creates the actual database with data*/

public class H2ConnectionPool implements ConnectionPool {
    private static final String DB_DRIVER = H2DbConfigurationManager.getProperty("db.driver");
    private static final String DB_CONNECTION = H2DbConfigurationManager.getProperty("db.connection");
    private static final String DB_USER = H2DbConfigurationManager.getProperty("db.user");
    private static final String DB_PASSWORD = H2DbConfigurationManager.getProperty("db.password");

    private static H2ConnectionPool instance;

    private H2ConnectionPool() throws SQLException {
        try (Connection c = getConnection()) {
            c.createStatement().execute(H2DbSQLManager.getProperty("CREATE_USER_TABLE_SQL"));
            c.createStatement().execute(H2DbSQLManager.getProperty("CREATE_COURSE_CATEGORY_TABLE_SQL"));
            c.createStatement().execute(H2DbSQLManager.getProperty("CREATE_COURSE_TABLE_SQL"));
            c.createStatement().executeUpdate(H2DbSQLManager.getProperty("INSERT_INTO_COURSE_CATEGORY_TABLE_SQL"));
            c.createStatement().executeUpdate(H2DbSQLManager.getProperty("INSERT_INTO_COURSE_TABLE_SQL"));
            c.createStatement().execute(H2DbSQLManager.getProperty("INSERT_INTO_USER_TABLE_SQL"));
        }
    }

    public static H2ConnectionPool getInstance() throws SQLException {
        if(instance == null) {
            instance = new H2ConnectionPool();
        }

        return instance;
    }

    @Override
    public Connection getConnection() throws SQLException {
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER,
                    DB_PASSWORD);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }
}
