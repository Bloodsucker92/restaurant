package utils;

import java.util.ResourceBundle;

/* Util class that contains method to retrieve the page paths from the config file*/

public class ConfigurationManager {
  // Reads the web/main/resources/config.properties file
  private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("config");

  private ConfigurationManager() {
  }

  public static String getProperty(String key) {
    return resourceBundle.getString(key);
  }
}