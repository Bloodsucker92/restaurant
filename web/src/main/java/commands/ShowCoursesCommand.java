package commands;


import com.restaurant.dao.beans.Course;
import com.restaurant.dao.exceptions.DaoException;
import com.restaurant.service.courses.CourseService;
import utils.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/*Defines logic for ShowCourses command*/

public class ShowCoursesCommand extends ActionCommand {

    private CourseService courseService = CourseService.getInstance();
    private List<Course> list = new ArrayList<>();
    private String page = null;

    public ShowCoursesCommand() {
    }

    @Override

    public String execute(HttpServletRequest request, HttpServletResponse response) throws DaoException {

        HttpSession session = request.getSession();
        String pageToResponse = null;

        /* The ShowCourses command is used in different context.
         * Its use depends whether the user or admin performs action.
         * Request's 'define' parameter helps us understand that.
         * First, we check if the parameter is null. If it is, it means that the command is called by
         * the user, not admin, and we need to perform logic showing the courses of a certain type depending on the
         * value of 'define' parameter.
         */

        if (request.getParameter("define") != null) {
            list = courseService.defineWhatTypeOfCoursesToShow(Integer.parseInt(request.getParameter("define")));
            request.setAttribute("courseList", list);
            session.setAttribute("define", request.getParameter("define"));

            //check if the user pushed "add to cart" button

            if (request.getParameter("orderCount") != null) {
                session.setAttribute("orderCount", "secondRequest");
            }
            pageToResponse = ConfigurationManager.getProperty("path.page.showOneTypeOfCourse");
        }
        /*If this block operates, it means we're dealing with ShowCoursesCommand being an administrator.
         * In this case we're dealing with all courses and need to perform pagination logic  */
        else {

            // Check if we're just switching between pages 1,2,3... without changing the 'items per page' parameter
            if (request.getParameter("itemsPerPage") == null) {
                int itemsPerPage = (int) session.getAttribute("itemsPerPage");
                pageToResponse = pagination(request, itemsPerPage);
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
                pageToResponse = pagination(request, itemsNo);
            }
        }
        return pageToResponse;
    }

    private String pagination(HttpServletRequest request, int itemsPerPage) throws DaoException {
        Long totalItemsCount = courseService.getTotalCourseNumber();
        Integer pageNumberCount = (int) Math.ceil((double) totalItemsCount / (double) itemsPerPage);
        Integer pageNumber = null;
        if (request.getParameter("page") == null) {
            pageNumber = 1;
        } else {
            pageNumber = Integer.parseInt(request.getParameter("page"));
        }
        request.setAttribute("page", pageNumber);
        list = courseService.showAllCourses(itemsPerPage * (pageNumber - 1), itemsPerPage);
        request.setAttribute("noOfPages", pageNumberCount);
        request.setAttribute("courseList", list);
        page = ConfigurationManager.getProperty("path.page.showcourses");
        return page;
    }
}
