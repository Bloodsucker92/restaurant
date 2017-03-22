package com.restaurant.dao.connectionpool;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Defines common logic for retreiving connection from different data sources
 *
 */
public interface ConnectionPool {
    Connection getConnection() throws SQLException;
}