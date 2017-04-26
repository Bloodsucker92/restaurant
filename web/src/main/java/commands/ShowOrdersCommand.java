package commands;

import com.restaurant.dao.beans.Course;
import com.restaurant.dao.beans.Order;
import com.restaurant.dao.exceptions.DaoException;
import com.restaurant.service.login.LoginService;
import utils.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ShowOrdersCommand extends ActionCommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DaoException {
        String page = null;
        Integer userId = (int) request.getSession().getAttribute("userId");
        List<Order> orders = LoginService.getInstance().getUserById(userId).getOrderList();
        Order order = new Order();
        Set<Course> courseSet = new HashSet<>();
        if (orders.isEmpty()==false){
        order = orders.get(0);
        courseSet = orders.get(0).getCourseSet();
        }
        request.setAttribute("courseSet", courseSet);
        request.setAttribute("order", order);


        page = ConfigurationManager.getProperty("path.page.showOrdersOfOneUser");

        return page;
    }
}
