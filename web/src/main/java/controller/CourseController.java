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
@RequestMapping(value = {"/pizza", "/drinks"})
public class CourseController {

    @Autowired
    IOrderService orderService;

    @Autowired
    IUserService userService;

    @PostMapping("/{courseId}")
    public String addItemToOrder(@PathVariable String courseId, ModelAndView model, HttpServletRequest request) {
        String page = "";
        try {
            String userLoggedIn = getPrincipal();
            com.restaurant.dao.pojos.User user = userService.getUserByLogin(userLoggedIn);
            if (user.getOrderList().isEmpty()) {
                orderService.makeNewOrder(user, Integer.parseInt(courseId));
            } else {
                int orderId = user.getOrderList().get(0).getId();
                orderService.addItemToCurrentOrder(orderId, Integer.parseInt(courseId));
            }
            String req = request.getParameter("courseCategory");
            if (request.getParameter("courseCategory").equals("pizza")) {
                page = "redirect:/pizza";
            } else {
                page = "redirect:/drinks";
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
