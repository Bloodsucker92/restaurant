package controller;

import com.restaurant.dao.pojos.Course;
import com.restaurant.service.exceptions.ServiceException;
import com.restaurant.service.interfaces.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import utils.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private ICourseService courseService;
    private List<Course> list = new ArrayList<>();
    private String page = null;


    @GetMapping(value = "/orders/page/{pageNumber}")
    public String showOrdersToAdmin(@PathVariable Integer pageNumber, HttpServletRequest request,
                                    HttpSession session, Model model) {
        String pageToResponse;
        if (request.getParameter("itemsPerPage") == null) {
            int itemsPerPage = (int) session.getAttribute("itemsPerPage");
            pageToResponse = pagination(request, itemsPerPage, pageNumber, model);
        }
            /* If we're loading the 'show all courses' page from admin, meaning that pagination will take action,
             * we take 'itemsPerPage' attribute from request and store it into a session for future use.
             * We need to know 'itemsPerPage' parameter each time to perform pagination correctly and load results.
             * Then, when we switch between the pages of pagination results, we take that attribute from session.
             * If we change the 'itemsPerPage', it replaces the current value in session with a new one.
             */
        else {
            Integer itemsNo = Integer.parseInt(request.getParameter("itemsPerPage"));
            session.setAttribute("itemsPerPage", itemsNo);
            pageToResponse = pagination(request, itemsNo, pageNumber, model);
        }
        return pageToResponse;
    }

    public String pagination(HttpServletRequest request, int itemsPerPage, Integer pageNumber, Model model) {
        try {
            Long totalItemsCount = courseService.getTotalCourseNumber();
            Integer pageNumberCount = (int) Math.ceil((double) totalItemsCount / (double) itemsPerPage);
            request.setAttribute("page", pageNumber);
            list = courseService.getAllCourses(itemsPerPage * (pageNumber - 1), itemsPerPage);
            request.setAttribute("noOfPages", pageNumberCount);
            request.setAttribute("courseList", list);
            page = "showcourses";
        } catch (ServiceException e) {
            model.addAttribute("message", e.getMessage());
            page = "redirect:/error";
        }
        return page;
    }
}
