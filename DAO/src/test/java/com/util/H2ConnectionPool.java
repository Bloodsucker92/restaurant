package com.util;

import com.restaurant.dao.connectionpool.ConnectionPool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class H2ConnectionPool implements ConnectionPool {
    private static final String DB_DRIVER = H2DbConfigurationManager.getProperty("db.driver");
    private static final String DB_CONNECTION = H2DbConfigurationManager.getProperty("db.connection");
    private static final String DB_USER = H2DbConfigurationManager.getProperty("db.user");
    private static final String DB_PASSWORD = H2DbConfigurationManager.getProperty("db.password");
    public static final String USER_TABLE_SQL = "CREATE TABLE `User` (\n" +
            "  `idClient` INT NOT NULL AUTO_INCREMENT,\n" +
            "  `username` VARCHAR(45) NULL,\n" +
            "  `password` VARCHAR(45) NULL,\n" +
            "  `access` VARCHAR(45) NULL,\n" +
            "  PRIMARY KEY (`idClient`))";
    public static final String COURSE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS `Course` (\n" +
            "  `idProduct` INT UNSIGNED NOT NULL AUTO_INCREMENT,\n" +
            "  `course_name` VARCHAR(45) NULL,\n" +
            "  `course_price` VARCHAR(45) NULL,\n" +
            "  `Course_category_idCourse_category` INT NOT NULL,\n" +
            "  PRIMARY KEY (`idProduct`, `Course_category_idCourse_category`))";
    public static final String COURSE_CATEGORY_TABLE_SQL = "CREATE TABLE IF NOT EXISTS `Course_category` (\n" +
            "  `idCourse_category` INT NOT NULL AUTO_INCREMENT,\n" +
            "  `course_type` VARCHAR(45) NULL,\n" +
            "  PRIMARY KEY (`idCourse_category`))";

    private static H2ConnectionPool instance;

    private H2ConnectionPool() throws SQLException {
        try (Connection c = getConnection()) {
            c.createStatement().execute(USER_TABLE_SQL);
            c.createStatement().execute(COURSE_TABLE_SQL);
            c.createStatement().execute(COURSE_CATEGORY_TABLE_SQL);
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
