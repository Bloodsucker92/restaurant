package controller;

import com.restaurant.dao.pojos.Course;
import com.restaurant.dao.pojos.User;
import com.restaurant.service.exceptions.ServiceException;
import com.restaurant.service.interfaces.ICourseService;
import com.restaurant.service.interfaces.IUserService;
import constants.CourseCategoryConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class RootController {

    @Autowired
    private IUserService userService;

    @Autowired
    private ICourseService courseService;

    @Autowired
    AuthenticationTrustResolver authenticationTrustResolver;

//    @GetMapping (value = "error")
//    public String showErrorPage () {
//        return "error";
//    }


    @RequestMapping (value = {"/", "home"}, produces = "plain/text", method = {RequestMethod.GET, RequestMethod.POST})
    public String showHomePage(ModelMap model, HttpSession session) {
        session.setAttribute("loggedinuser", getPrincipal());
//        model.addAttribute("loggedinuser", getPrincipal());
        return "home";
    }


    @GetMapping(value = "access_denied")
    public String accessDeniedPage() {
        return "access_denied";
    }

    @GetMapping(value = "/pizza")
    public String showPizzaPage(ModelMap model) {
        List<Course> courseList;
        try {
            courseList = courseService.showCoursesOfCertainType(CourseCategoryConstants.PIZZA.getNumVal());
            model.put("courseList", courseList);
            model.put("courseCategory", courseList.get(0).getCourseCategory().getCourseCategory());
            return "courses";
        }
        catch (ServiceException e) {
            model.addAttribute("message", e.getMessage());
            return "redirect:/error";
        }
    }

    @GetMapping(value = "/drinks")
    public String showDrinksPage(ModelMap model) {
        List<Course> courseList;
        try {
            courseList = courseService.showCoursesOfCertainType(CourseCategoryConstants.DRINKS.getNumVal());
            model.put("courseList", courseList);
            model.put("courseCategory", courseList.get(0).getCourseCategory().getCourseCategory());
            return "courses";
        }
        catch (ServiceException e) {
            model.addAttribute("message", e.getMessage());
            return "redirect:/error";
        }
    }

    @RequestMapping (value = "/dessert", method = RequestMethod.GET)
    public String showDessertPage(ModelMap model) {
        List<Course> courseList;
        try {
            courseList = courseService.showCoursesOfCertainType(CourseCategoryConstants.DESSERT.getNumVal());
            model.put("courseList", courseList);
            model.put("courseCategory", courseList.get(0).getCourseCategory().getCourseCategory());
        }
        catch (ServiceException e) {
            model.addAttribute("message", e.getMessage());
            return "redirect:/error";
        }
        return "courses";
    }

    @GetMapping(value = "/soup")
    public String showSoupPage(ModelMap model) {
        List<Course> courseList;
        try {
            courseList = courseService.showCoursesOfCertainType(CourseCategoryConstants.SOUP.getNumVal());
            model.put("courseList", courseList);
            model.put("courseCategory", courseList.get(0).getCourseCategory().getCourseCategory());
        }
        catch (ServiceException e) {
            model.addAttribute("message", e.getMessage());
            return "redirect:/error";
        }
        return "courses";
    }

    @GetMapping(value = "/other")
    public String showOtherPage(ModelMap model) {
        List<Course> courseList;
        try {
            courseList = courseService.showCoursesOfCertainType(CourseCategoryConstants.OTHER.getNumVal());
            model.put("courseList", courseList);
            model.put("courseCategory", courseList.get(0).getCourseCategory().getCourseCategory());
        }
        catch (ServiceException e) {
            model.addAttribute("message", e.getMessage());
            return "redirect:/error";
        }
        return "courses";
    }

    @GetMapping(value = "/register")
    public ModelAndView showRegisterPage() {
        return new ModelAndView("register", "user", new User());
    }

    @PostMapping(value = "/register")
    public String registerNewUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {
            return "register";
        } else {
            user.setAccess("USER");
            try {
                userService.add(user);
            } catch (ServiceException e) {
                modelAndView.addObject("message", e.getMessage());
                modelAndView.setViewName("error");
            }
            return "redirect:/login";
        }
    }

    /**
     * This method handles login GET requests.
     * If users is already logged-in and tries to goto login page again, will be redirected to home page.
     */
    //Spring Security see this :
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public ModelAndView login(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout) {

        ModelAndView model = new ModelAndView();


        if (error != null) {
            model.addObject("error", "Invalid username and password!");
        }

        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }
        if (!isCurrentAuthenticationAnonymous()) {
            model.setViewName("redirect:/home");
        } else {
            model.setViewName("login");
        }

        return model;
    }


    @GetMapping(value = "logout")
    public String doLogout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/home?logout";
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

    /**
     * This method returns true if users is already authenticated [logged-in], else false.
     */
    private boolean isCurrentAuthenticationAnonymous() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authenticationTrustResolver.isAnonymous(authentication);
    }


}
