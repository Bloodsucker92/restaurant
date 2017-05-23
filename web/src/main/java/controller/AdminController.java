package controller;

import com.restaurant.dao.pojos.Course;
import com.restaurant.dao.pojos.CourseCategory;
import com.restaurant.dao.pojos.CourseDTO;
import com.restaurant.dao.pojos.User;
import com.restaurant.service.exceptions.ServiceException;
import com.restaurant.service.interfaces.ICourseService;
import com.restaurant.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private ICourseService courseService;

    @Autowired
    private IUserService userService;

    private List<Course> list = new ArrayList<>();
    private String page = null;

    @GetMapping
    public String showAdminPage () {
        return "admin";
    }

    @GetMapping (value = "/courses/add")
    public ModelAndView showCourseAddForm () {
        return new ModelAndView("addcourse", "course", new CourseDTO());
    }

    @PostMapping (value = "/courses")
    public String addCourse (@RequestParam String courseName, @RequestParam String coursePrice,
                                   @RequestParam String courseCategory, ModelAndView model,
                                   @Valid @ModelAttribute ("course") CourseDTO courseDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "addcourse";
        }
        Course course = new Course();
        course.setCourseName(courseName);
        course.setCoursePrice(Integer.valueOf(coursePrice));
        try {
            CourseCategory courseType = courseService.getCourseCategory(Integer.valueOf(courseCategory));
            course.setCourseCategory(courseType);
            courseService.add(course);
        }
        catch (ServiceException e) {
            model.addObject("message", e.getMessage());
            model.setViewName("error");
        }
        return "redirect:/admin";
    }

    @GetMapping(value = "/courses/page/{pageNumber}")
    public String showCoursesToAdmin(@PathVariable Integer pageNumber,
                                    @RequestParam(value = "sort", required = false) String sortBy,
                                    @RequestParam(value = "order", required = false) String sortingOrder,
                                    HttpServletRequest request,
                                    HttpSession session,
                                    Model model) {
        String pageToResponse;
        if (request.getParameter("itemsPerPage") == null) {
            int itemsPerPage = (int) session.getAttribute("itemsPerPage");
            if (sortingOrder!=null) {
                session.setAttribute("sortingOrder", sortingOrder);
                pageNumber = 1;
            }
            else {
                sortingOrder = (String) session.getAttribute("sortingOrder");
            }
            pageToResponse = pagination(request, itemsPerPage, pageNumber, model, sortBy, sortingOrder, session);
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
            pageToResponse = pagination(request, itemsNo, pageNumber, model, sortBy, sortingOrder, session);
        }
        return pageToResponse;
    }

    public String pagination(HttpServletRequest request, int itemsPerPage, Integer pageNumber, Model model, String sortBy,
                             String sortingOrder, HttpSession session) {
        try {
            Long totalItemsCount = courseService.getTotalCourseNumber();
            Integer pageNumberCount = (int) Math.ceil((double) totalItemsCount / (double) itemsPerPage);
            request.setAttribute("page", pageNumber);
            list = courseService.getAllCourses(itemsPerPage * (pageNumber - 1), itemsPerPage, sortBy, sortingOrder);
            request.setAttribute("noOfPages", pageNumberCount);
            request.setAttribute("courseList", list);
            page = "showcourses";
        } catch (ServiceException e) {
            model.addAttribute("message", e.getMessage());
            page = "redirect:/error";
        }
        return page;
    }

    @GetMapping (value = "/users")
    public ModelAndView showUsersToAdmin (ModelAndView model) {
        try {
            ArrayList<User> users = (ArrayList<User>) userService.getAllUsers();
            model.setViewName("showusers");
            model.addObject("usersList", users);
        }
        catch (ServiceException e) {
            model.addObject("message", e.getMessage());
            model.setViewName("error");
        }
        return model;
    }

}
