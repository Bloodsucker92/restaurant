package commands;

import com.restaurant.logics.courses.CourseLogic;
import utils.ConfigurationManager;
import utils.ExceptionManager;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/* Defines logic for the AddCourse command*/

public class AddCourseCommand extends ActionCommand {

    private ExceptionManager exceptionManager = ExceptionManager.getInstance();
    private CourseLogic courseLogic = CourseLogic.getInstance();

    public AddCourseCommand() throws SQLException, NamingException {
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = null;
        String courseName = request.getParameter("courseName");
        String coursePrice = request.getParameter("coursePrice");
        String courseType = request.getParameter("dropDownList");
        try {
            courseLogic.addCourse(courseName, Integer.parseInt(coursePrice), courseType);
        } catch (SQLException | NamingException e) {
            exceptionManager.writeErrorToLog(AddCourseCommand.class.getName(), e, true);
        }
        request.setAttribute("courseName", courseName);
            request.setAttribute("coursePrice", coursePrice);
            page = ConfigurationManager.getProperty("path.page.courseadded");


        return page;
    }
}
