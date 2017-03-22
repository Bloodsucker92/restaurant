package commands;


import com.restaurant.logics.login.LoginLogic;
import filter.ClientType;
import utils.ConfigurationManager;
import utils.ErrorManager;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class LoginCommand extends ActionCommand {
  private static final String PARAM_NAME_LOGIN = "login";
  private static final String PARAM_NAME_PASSWORD = "password";
  private LoginLogic loginLogic = LoginLogic.getInstance();


  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) {
    String page = null;
    // извлечение из запроса логина и пароля
    String login = request.getParameter(PARAM_NAME_LOGIN);
    String pass = request.getParameter(PARAM_NAME_PASSWORD);
    // проверка логина и пароля
    try {
      // check if user exists in a database
      if (loginLogic.ifUserExists(login, pass)){
        if (loginLogic.checkAdminLogin(login, pass)) {
          request.setAttribute("user", login);
          HttpSession session = request.getSession(true);
          session.setAttribute("userType", ClientType.ADMINISTRATOR);
          // определение пути к main.jsp
          page = ConfigurationManager.getProperty("path.page.main");
        } else if (loginLogic.checkUserLogin(login, pass)) {
          request.setAttribute("user", login);
          HttpSession session = request.getSession(true);
          session.setAttribute("userType", ClientType.USER);
          // определение пути к main.jsp
          page = ConfigurationManager.getProperty("path.page.user");
      }
      }
      else {
        request.setAttribute("guest", true);
        request.getSession().setAttribute("userType", ClientType.GUEST);
        page = ConfigurationManager.getProperty("path.page.register");
      }
    } catch (SQLException | NamingException | IOException | ClassNotFoundException e) {
      errorManager.writeErrorToLog(LoginCommand.class.getName(), e, true);
    }
      return page;
    }
  }
