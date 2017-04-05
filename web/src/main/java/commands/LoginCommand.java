package commands;


import com.restaurant.service.login.LoginService;
import filter.ClientType;
import utils.ConfigurationManager;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/*Defines logic for Login command*/

public class LoginCommand extends ActionCommand {
  private static final String PARAM_NAME_LOGIN = "login";
  private static final String PARAM_NAME_PASSWORD = "password";
  private LoginService loginService = LoginService.getInstance();

  public LoginCommand() throws SQLException, NamingException {
  }


  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, NamingException {
    String page = null;
    // retrieving the login and password parameters from the request
    String login = request.getParameter(PARAM_NAME_LOGIN);
    String pass = request.getParameter(PARAM_NAME_PASSWORD);
    // login and password check
      // check if user exists in a database
      if (loginService.ifUserExists(login, pass)){
        if (loginService.checkAdminLogin(login, pass)) {
          request.setAttribute("user", login);
          HttpSession session = request.getSession(true);
          session.setAttribute("userType", ClientType.ADMINISTRATOR);
          // defining the path to the main.jsp (in case the user is admin)
          page = ConfigurationManager.getProperty("path.page.main");
        } else if (loginService.checkUserLogin(login, pass)) {
          request.setAttribute("user", login);
          HttpSession session = request.getSession(true);
          session.setAttribute("userType", ClientType.USER);
          // defining the path to the user.jsp (in case the user is user)
          page = ConfigurationManager.getProperty("path.page.user");
      }
      }
      else {
        // if the user is not registered provides a path to the register.jsp
        request.setAttribute("guest", true);
        request.getSession().setAttribute("userType", ClientType.GUEST);
        page = ConfigurationManager.getProperty("path.page.register");
      }
      return page;
    }
  }
