package controller;

import com.restaurant.dao.pojos.Course;
import com.restaurant.dao.pojos.Order;
import com.restaurant.dao.pojos.User;
import com.restaurant.service.exceptions.ServiceException;
import com.restaurant.service.interfaces.IOrderService;
import com.restaurant.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class OrderRestController {

    @Autowired
    IUserService userService;

    @Autowired
    IOrderService orderService;

    @DeleteMapping (value = "/orders/{orderId}/courses/{courseId}")
    public String deleteItemFromOrder (@PathVariable String orderId, @PathVariable String courseId, ModelAndView model) {
        try {
            Order order = orderService.getOrderById(Integer.parseInt(orderId));
            orderService.deleteItemFromOrder(Integer.parseInt(courseId), order.getId());
        }
        catch (ServiceException e) {
            model.setViewName("error");
            model.addObject("message", e.getMessage());
        }
        return "redirect:/orders";
    }

    @GetMapping (value = "/orders")
    public ModelAndView getOrderList (HttpSession session, ModelAndView model, Authentication auth) {
        try {
            User user = userService.getUserByLogin((String) session.getAttribute("loggedinuser"));
            List<Order> orders = user.getOrderList();
            List<Course> courseList = new ArrayList<>();
            Order order = new Order();
            if (!orders.isEmpty()) {
                order = orders.get(0);
                Set<Course> courseSet = new HashSet<>();
                courseList.addAll(order.getCourseSet());
            }
            model.addObject("order", order);
            model.addObject("courseList", courseList);
            model.setViewName("showordersREST");
        }
        catch (ServiceException e) {
            model.setViewName("error");
            model.addObject("message", e.getMessage());
        }
        return model;
    }

}
