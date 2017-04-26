package commands;

import filter.ClientType;
import utils.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/* Defines logic for Logout command */

public class LogoutCommand extends ActionCommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = ConfigurationManager.getProperty("path.page.index");
        // session invalidation
        request.getSession().invalidate();
        request.getSession(true).setAttribute("userType", ClientType.GUEST);
        return page;
    }
}