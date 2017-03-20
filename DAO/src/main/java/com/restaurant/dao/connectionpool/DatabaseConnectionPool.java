package com.restaurant.dao.connectionpool;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


public final class DatabaseConnectionPool {

    public DatabaseConnectionPool() {
    }


    public static Connection getConnection() throws SQLException, NamingException {
        Context initCtx = new InitialContext();
        DataSource ds = (DataSource) initCtx.lookup("java:comp/env/jdbc/RestaurantDB");
        return ds.getConnection();
    }
}
