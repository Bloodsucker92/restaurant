package commands;

import com.restaurant.dao.beans.User;
import com.restaurant.logics.users.ShowUsersLogic;
import utils.ConfigurationManager;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/*Defines logic for ShowUsers command*/

public class ShowUsersCommand extends ActionCommand {

    private ShowUsersLogic showUsersLogic = ShowUsersLogic.getInstance();

    public ShowUsersCommand() throws SQLException, NamingException {
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, NamingException {
        String page = null;
        ArrayList<User> list = showUsersLogic.showUsers();
        request.setAttribute("usersList", list);
        page = ConfigurationManager.getProperty("path.page.showusers");
        return page;
    }
}
