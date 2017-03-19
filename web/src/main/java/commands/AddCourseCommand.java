package commands;

import com.restaurant.dao.beans.Course;
import com.restaurant.logics.courses.CourseLogic;
import utils.ConfigurationManager;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddCourseCommand extends ActionCommand {

    private static CourseLogic courseLogic = CourseLogic.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String courseName = request.getParameter("courseName");
        String coursePrice = request.getParameter("coursePrice");
        String courseType = request.getParameter("dropDownList");

        try {

            courseLogic.addCourse(courseName, Integer.parseInt(coursePrice), courseType);
            request.setAttribute("courseName", courseName);
            request.setAttribute("coursePrice", coursePrice);
            page = ConfigurationManager.getProperty("path.page.courseadded");
        } catch (SQLException | NamingException e) {
            errorManager.writeErrorToLog(ActionCommand.class.getName(), e, true);
        }

        return page;
    }
}
