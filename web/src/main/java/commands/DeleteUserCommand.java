package commands;


import com.restaurant.dao.beans.User;
import com.restaurant.dao.exceptions.DaoException;
import com.restaurant.service.users.DeleteUserService;
import com.restaurant.service.users.ShowUsersService;
import utils.ConfigurationManager;
import utils.ExceptionManager;

import javax.naming.NamingException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;

/*Defines logic for DeleteUser command*/

public class DeleteUserCommand extends ActionCommand {

    private DeleteUserService deleteUserService = DeleteUserService.getInstance();
    private ShowUsersService showUsersService = ShowUsersService.getInstance();

    public DeleteUserCommand() {
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DaoException {
        String page = null;
        Integer id = Integer.valueOf(request.getParameter("id"));
        ArrayList<User> list = null;
        boolean userDeleted = deleteUserService.deleteUser(id);
        list = showUsersService.showUsers();
        request.setAttribute("usersList", list);
        request.setAttribute("userDeleted", userDeleted);
        page = ConfigurationManager.getProperty("path.page.showusers");

        return page;
    }
}
