package com.util;


import java.util.ResourceBundle;

public class H2DbConfigurationManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("H2Dbconnection");

    public H2DbConfigurationManager() {
    }

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
