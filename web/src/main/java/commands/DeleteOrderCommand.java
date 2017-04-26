package commands;

import com.restaurant.dao.exceptions.DaoException;
import com.restaurant.service.courses.CourseService;
import com.restaurant.service.orders.OrderService;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class DeleteOrderCommand extends ActionCommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DaoException {
        String page = null;
        Integer courseIdToDeleteFromOrder = Integer.valueOf(request.getParameter("courseId"));
        Integer orderId = Integer.parseInt(request.getParameter("orderId"));
        OrderService.getInstance().deleteOrder(courseIdToDeleteFromOrder, orderId);
        request.getSession().setAttribute("orderCount", null);
        ShowOrdersCommand showOrdersCommand = new ShowOrdersCommand();
        page = showOrdersCommand.execute(request, response);
        return page;
    }
}
