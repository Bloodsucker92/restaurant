package commands;


import com.restaurant.dao.beans.Course;
import com.restaurant.logics.courses.CourseLogic;
import utils.ConfigurationManager;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;

public class ShowCoursesCommand extends ActionCommand {

    private static CourseLogic courseLogic = CourseLogic.getInstance();

    @Override

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = null;
        String userForm = request.getParameter("userForm");

        try {
            ArrayList<Course> list = courseLogic.showCourses();
            request.setAttribute("courseList", list);
            page = ConfigurationManager.getProperty("path.page.showcourses");
        } catch (SQLException | NamingException e) {
            errorManager.writeErrorToLog(ActionCommand.class.getName(), e, true);
        }

        return page;
    }
}
