package commands;

import com.restaurant.dao.beans.User;
import com.restaurant.dao.exceptions.DaoException;
import com.restaurant.service.users.ShowUsersService;
import utils.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/*Defines logic for ShowUsers command*/

public class ShowUsersCommand extends ActionCommand {

    private ShowUsersService showUsersService = ShowUsersService.getInstance();

    public ShowUsersCommand() {
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DaoException {
        String page = null;
        ArrayList<User> list = showUsersService.showUsers();
        request.setAttribute("usersList", list);
        page = ConfigurationManager.getProperty("path.page.showusers");
        return page;
    }
}
