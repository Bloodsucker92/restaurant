package utils;

import java.util.ResourceBundle;

/* Util class which provides the message manager to write error messages*/

public class MessageManager {
  private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("messages");

  private MessageManager() {
  }

  public static String getProperty(String key) {
    return resourceBundle.getString(key);
  }
}