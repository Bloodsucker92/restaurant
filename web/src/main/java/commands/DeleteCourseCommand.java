package commands;

import com.restaurant.dao.beans.Course;
import com.restaurant.dao.exceptions.DaoException;
import com.restaurant.service.courses.CourseService;
import utils.ConfigurationManager;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;

public class DeleteCourseCommand extends ActionCommand {

    private CourseService courseService = CourseService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DaoException {
        String page = null;
        Integer id = Integer.valueOf(request.getParameter("id"));
        boolean courseDeleted = courseService.deleteCourse(id);
//        ArrayList<Course> list = courseService.showAllCourses(0, 25);
//        request.setAttribute("courseList", list);
        request.setAttribute("courseDeleted", courseDeleted);
        ShowCoursesCommand showCoursesCommand = new ShowCoursesCommand();
        page = showCoursesCommand.execute(request, response);

        return page;
    }
}
