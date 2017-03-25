package com.restaurant.logics.login;

import com.restaurant.dao.userDAO.*;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.SQLException;

/* Provides service methods which take action when a user fills in the login form of the app*/

public class LoginLogic {

  private static String ENTER_LOGIN = null;
  private static String ENTER_PASSWORD = null;
  private static String ENTER_ACCESS = null;
  private UserDAOImpl userDAO = UserDAOImpl.getInstance();
  private static LoginLogic instance;

  private LoginLogic() throws NamingException, SQLException {
  }

  public static LoginLogic getInstance() throws NamingException, SQLException {
      if (instance == null) {
        instance = new LoginLogic();
      }
    return instance;
  }

  /* Checks if a certain user was registered and is present in the database */

  public boolean ifUserExists (String enterLogin, String enterPassword) throws SQLException, NamingException {
    boolean userExists;
    if (userDAO.getUser(enterLogin, enterPassword) == null){
      userExists = false;
    } else {
      userExists = true;
    }
    return userExists;
  }

  /* A subsidiary method */

  public void getUserDaoInstance (String enterLogin, String enterPass) throws SQLException, NamingException {

  ENTER_LOGIN = userDAO.getUser(enterLogin, enterPass).getUsername();
  ENTER_PASSWORD = userDAO.getUser(enterLogin, enterPass).getUserpassword();
  ENTER_ACCESS = userDAO.getUser(enterLogin, enterPass).getAccess();
  }


  /* Checks if a certain user has admin access*/

  public boolean checkAdminLogin(String enterLogin, String enterPass) throws SQLException, NamingException {
    getUserDaoInstance(enterLogin, enterPass);

    return ENTER_LOGIN.equals(enterLogin) && ENTER_PASSWORD.equals(enterPass) && ENTER_ACCESS.equals("a");
  }

  /* Checks if a certain user has user access*/

  public boolean checkUserLogin(String enterLogin, String enterPass) throws SQLException, NamingException {
    getUserDaoInstance(enterLogin, enterPass);

    return ENTER_LOGIN.equals(enterLogin) && ENTER_PASSWORD.equals(enterPass) && ENTER_ACCESS.equals("u");
  }
}