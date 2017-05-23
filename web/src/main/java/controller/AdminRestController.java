package controller;

import com.restaurant.dao.pojos.Course;
import com.restaurant.dao.pojos.User;
import com.restaurant.service.exceptions.ServiceException;
import com.restaurant.service.interfaces.ICourseService;
import com.restaurant.service.interfaces.IUserService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AdminRestController {

    @Autowired
    private IUserService userService;

    @Autowired
    private ICourseService courseService;

    @DeleteMapping(value = "admin/users/{username}", produces = "application/json")
    public String deleteUser(@PathVariable String username, ModelAndView modelAndView) {
        try {
            User user = userService.getUserByLogin(username);
            userService.delete(user);
            Map result = new HashMap<String, String>();
            result.put("message", "success");
            return new JSONObject(result).toString();
        } catch (ServiceException e) {
            modelAndView.setViewName("error");
            modelAndView.addObject("message", e.getMessage());
            Map result = new HashMap<String, String>();
            result.put("message", "failed");
            return new JSONObject(result).toString();
        }
    }

    @DeleteMapping (value = "admin/courses/{courseId}", produces = "application/json")
    public String deleteCourse (@PathVariable String courseId, ModelAndView model) {
        try {
            Course course = courseService.get(Integer.valueOf(courseId));
            courseService.delete(course);
            Map result = new HashMap<String, String>();
            result.put("message", "success");
            result.put("courseName", "courseName");
            return new JSONObject(result).toString();
        } catch (ServiceException e) {
            model.setViewName("error");
            model.addObject("message", e.getMessage());
            Map result = new HashMap<String, String>();
            result.put("message", "failed");
            return new JSONObject(result).toString();
        }
    }

}
