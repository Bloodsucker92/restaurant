package commands;

import com.restaurant.dao.exceptions.DaoException;
import com.restaurant.service.orders.OrderService;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class MakeOrderCommand extends ActionCommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DaoException {
        HttpSession session = request.getSession();
        String page = null;
        Integer courseId = Integer.valueOf(request.getParameter("courseId"));
        Integer userId = (int) session.getAttribute("userId");
        if (session.getAttribute("orderCount") != null) {
            OrderService.getInstance().addItemToCurrentOrder(courseId);
        } else {
            OrderService.getInstance().makeNewOrder(userId, courseId);
        }
        ShowCoursesCommand showCoursesCommand = new ShowCoursesCommand();
        page = showCoursesCommand.execute(request, response);

        return page;
    }
}
