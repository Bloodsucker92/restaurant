package commands;

import com.restaurant.logics.register.RegisterUserLogic;
import filter.ClientType;
import utils.ConfigurationManager;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class RegisterCommand extends ActionCommand{

    private static RegisterUserLogic registerUserLogic = RegisterUserLogic.getInstance();


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String page = null;
        String loginReg = request.getParameter("loginReg");
        String passwordReg = request.getParameter("passwordReg");
        try {
          registerUserLogic.registerUser(loginReg, passwordReg);
        } catch (SQLException | NamingException e) {
            errorManager.writeErrorToLog(RegisterCommand.class.getName(), e, true);
        }
        request.setAttribute("newUser", loginReg);
        HttpSession session = request.getSession(true);
        session.setAttribute("userType", ClientType.USER);
        page = ConfigurationManager.getProperty("path.page.login");

        return page;
    }
}
