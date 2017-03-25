package com.util;

import java.util.ResourceBundle;

/* Util class which allows to get SQL-queries form a resource bundle file */

public class H2DbSQLManager {

    // Accesses the DAO/main/resources/SQLQueries.properties file

    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("SQLQueries");

    public H2DbSQLManager() {
    }

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}

