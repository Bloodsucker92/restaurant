package commands;

import com.restaurant.service.register.RegisterUserService;
import filter.ClientType;
import utils.ConfigurationManager;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

/*Defines logic for Register command*/

public class RegisterCommand extends ActionCommand{

    private RegisterUserService registerUserService = RegisterUserService.getInstance();

    public RegisterCommand() throws SQLException, NamingException {
    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, NamingException {

        String page = null;
        String loginReg = request.getParameter("loginReg");
        String passwordReg = request.getParameter("passwordReg");
        registerUserService.registerUser(loginReg, passwordReg);
        request.setAttribute("newUser", loginReg);
        HttpSession session = request.getSession(true);
        session.setAttribute("userType", ClientType.USER);
        page = ConfigurationManager.getProperty("path.page.login");

        return page;
    }
}
