package com.restaurant.logics.login;

import com.restaurant.dao.userDAO.*;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.SQLException;

public class LoginLogic {

  private static String ENTER_LOGIN = null;
  private static String ENTER_PASSWORD = null;
  private static String ENTER_ACCESS = null;
  private static UserDAOImpl userDAO = UserDAOImpl.getInstance();
  private static LoginLogic instance;

  private LoginLogic(){
  }

  public static LoginLogic getInstance() {
      if (instance == null) {
        instance = new LoginLogic();
      }
    return instance;
  }

  public boolean ifUserExists (String enterLogin, String enterPassword) throws SQLException, IOException, ClassNotFoundException, NamingException {
    boolean userExists;
    if (userDAO.getUser(enterLogin, enterPassword) == null){
      userExists = false;
    } else {
      userExists = true;
    }
    return userExists;
  }

  public void getUserDaoInstance (String enterLogin, String enterPass) throws SQLException, IOException, ClassNotFoundException, NamingException {

  ENTER_LOGIN = userDAO.getUser(enterLogin, enterPass).getUsername();
  ENTER_PASSWORD = userDAO.getUser(enterLogin, enterPass).getUserpassword();
  ENTER_ACCESS = userDAO.getUser(enterLogin, enterPass).getAccess();
  }


  public boolean checkAdminLogin(String enterLogin, String enterPass) throws SQLException, IOException, ClassNotFoundException, NamingException {
    getUserDaoInstance(enterLogin, enterPass);

    return ENTER_LOGIN.equals(enterLogin) && ENTER_PASSWORD.equals(enterPass) && ENTER_ACCESS.equals("a");
  }

  public boolean checkUserLogin(String enterLogin, String enterPass) throws SQLException, IOException, ClassNotFoundException, NamingException {
    getUserDaoInstance(enterLogin, enterPass);

    return ENTER_LOGIN.equals(enterLogin) && ENTER_PASSWORD.equals(enterPass) && ENTER_ACCESS.equals("u");
  }
}