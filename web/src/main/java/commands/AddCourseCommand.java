package commands;


import com.restaurant.dao.exceptions.DaoException;
import com.restaurant.service.courses.CourseService;

import utils.ExceptionManager;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.SQLException;


/* Defines logic for the AddCourse command*/

public class AddCourseCommand extends ActionCommand {

    private ExceptionManager exceptionManager = ExceptionManager.getInstance();
    private CourseService courseService = CourseService.getInstance();

    public AddCourseCommand() {
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DaoException {
        boolean courseAdded = false;
        String page = null;
        String courseName = request.getParameter("courseName");
        String coursePrice = request.getParameter("coursePrice");
        Integer courseCategory = Integer.parseInt(request.getParameter("categoryOptions"));
        courseService.addCourse(courseName, Integer.parseInt(coursePrice), courseCategory);
        courseAdded = true;
        ShowCoursesCommand showCoursesCommand = new ShowCoursesCommand();
        page = showCoursesCommand.execute(request, response);

        return page;
    }
}
