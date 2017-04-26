package commands;


import com.restaurant.dao.beans.User;
import com.restaurant.dao.exceptions.DaoException;
import com.restaurant.service.login.LoginService;
import filter.ClientType;
import utils.ConfigurationManager;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

/*Defines logic for Login command*/

public class LoginCommand extends ActionCommand {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    private LoginService loginService = LoginService.getInstance();


    public LoginCommand() {
    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DaoException {
        String page = null;
        // retrieving the login and password parameters from the request
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);
        User user = new User();
        user = loginService.findUserByLoginAndPassword(login, pass);
        // login and password check
        // check if user exists in a database
        if (user != null) {
            if (user.getAccess().equals("a")) {
                request.setAttribute("user", login);
                HttpSession session = request.getSession(true);
                session.setAttribute("userType", ClientType.ADMINISTRATOR);
                // defining the path to the main.jsp (in case the user is admin)
                page = ConfigurationManager.getProperty("path.page.admin");
            } else {
                request.setAttribute("user", login);
                HttpSession session = request.getSession(true);
                session.setAttribute("userType", ClientType.USER);
                session.setAttribute("userId", user.getId());
                // defining the path to the user.jsp (in case the user is user)
                page = ConfigurationManager.getProperty("path.page.index");
            }
        } else {
            // if the user is not registered provides a path to the register.jsp
            request.setAttribute("guest", true);
            request.getSession().setAttribute("userType", ClientType.GUEST);
            page = ConfigurationManager.getProperty("path.page.retrylogin");
        }
        return page;
    }
}
