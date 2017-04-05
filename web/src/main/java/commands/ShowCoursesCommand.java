package commands;


import com.restaurant.dao.beans.Course;
import com.restaurant.service.courses.CourseService;
import utils.ConfigurationManager;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/*Defines logic for ShowCourses command*/

public class ShowCoursesCommand extends ActionCommand {

    private CourseService courseService = CourseService.getInstance();

    public ShowCoursesCommand() throws SQLException, NamingException {
    }

    @Override

    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, NamingException {
        String page = null;
        String userForm = request.getParameter("userForm");

        ArrayList<Course> list = courseService.showCourses();
        request.setAttribute("courseList", list);
        page = ConfigurationManager.getProperty("path.page.showcourses");
        return page;
    }
}
