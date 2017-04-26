package commands;

import com.restaurant.dao.exceptions.DaoException;
import com.restaurant.service.login.LoginService;
import com.restaurant.service.register.RegisterUserService;
import filter.ClientType;
import utils.ConfigurationManager;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

/*Defines logic for Register command*/

public class RegisterCommand extends ActionCommand {

    private RegisterUserService registerUserService = RegisterUserService.getInstance();

    public RegisterCommand() {
    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DaoException {

        String page = null;
        String loginReg = request.getParameter("login");
        String passwordReg = request.getParameter("password");
        registerUserService.registerUser(loginReg, passwordReg);
        int userId = LoginService.getInstance().findUserByLoginAndPassword(loginReg, passwordReg).getId();
        request.getSession().setAttribute("userId", userId);
        request.setAttribute("newUser", loginReg);
        HttpSession session = request.getSession(true);
        session.setAttribute("userType", ClientType.USER);
        page = ConfigurationManager.getProperty("path.page.index");
        return page;
    }
}
