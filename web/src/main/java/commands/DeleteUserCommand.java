package commands;


import com.restaurant.dao.beans.User;
import com.restaurant.service.users.DeleteUserService;
import com.restaurant.service.users.ShowUsersService;
import utils.ConfigurationManager;
import utils.ExceptionManager;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;

/*Defines logic for DeleteUser command*/

public class DeleteUserCommand extends ActionCommand {

    private DeleteUserService deleteUserService = DeleteUserService.getInstance();
    private ShowUsersService showUsersService = ShowUsersService.getInstance();
    private ExceptionManager exceptionManager = ExceptionManager.getInstance();

    public DeleteUserCommand() throws SQLException, NamingException {
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, NamingException {
        String page = null;
        String username = request.getParameter("username");
        String userpassword = request.getParameter("userpassword");
        ArrayList<User> list = null;
        boolean userDeleted = deleteUserService.deleteUser(username, userpassword);
        list = showUsersService.showUsers();
        request.setAttribute("usersList", list);
        request.setAttribute("userDeleted", userDeleted);
        page = ConfigurationManager.getProperty("path.page.showusers");

        return page;
    }
}
