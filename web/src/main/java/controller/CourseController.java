package controller;


import com.restaurant.service.exceptions.ServiceException;
import com.restaurant.service.interfaces.IOrderService;
import com.restaurant.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = {"/pizza", "/drinks", "/soup", "/dessert", "/other"})
public class CourseController {

    @Autowired
    IOrderService orderService;

    @Autowired
    IUserService userService;

    @PostMapping("/{courseId}")
    public String addItemToOrder(@PathVariable String courseId, ModelAndView model, HttpServletRequest request) {
        String page = "";
        try {
            String parameter = request.getParameter("courseCategory");
            String userLoggedIn = getPrincipal();
            com.restaurant.dao.pojos.User user = userService.getUserByLogin(userLoggedIn);
            if (user.getOrderList().isEmpty()) {
                orderService.makeNewOrder(user, Integer.parseInt(courseId));
            } else {
                int orderId = user.getOrderList().get(0).getId();
                orderService.addItemToCurrentOrder(orderId, Integer.parseInt(courseId));
            }
            if (request.getParameter("courseCategory").equals("pizza")) {
                page = "redirect:/pizza";
            } else if (request.getParameter("courseCategory").equals("drink")) {
                page = "redirect:/drinks";
            } else if (request.getParameter("courseCategory").equals("soup")) {
                page = "redirect:/soup";
            } else if (request.getParameter("courseCategory").equals("dessert")) {
                page = "redirect:/dessert";
            } else if (request.getParameter("courseCategory").equals("other")) {
                page = "redirect:/other";
            }
        }
        catch (ServiceException e) {
            model.addObject("message", e.getMessage());
            model.setViewName("error");
        }
        return page;
    }

    private String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
}
