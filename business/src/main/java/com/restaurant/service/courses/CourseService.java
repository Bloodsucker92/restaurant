package com.restaurant.service.courses;


import com.restaurant.dao.beans.Course;
import com.restaurant.dao.courseDAO.CourseDAOImpl;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;


/*Class provides methods for accessing dao layer and performing service operations on the data obtained
* Deals with the course logic exclusively
*/

public class CourseService {
    private static CourseService instance;
    private CourseDAOImpl courseDAO = CourseDAOImpl.getInstance();

    private CourseService() throws SQLException, NamingException {
    }

    public static CourseService getInstance() throws SQLException, NamingException {
        if (instance == null) {
            instance = new CourseService();
        }
        return instance;
    }

    //Returns the list of all the courses available in the restaurant's menu

    public ArrayList<Course> showCourses() throws SQLException, NamingException {
        ArrayList<Course> courseList = courseDAO.showAllCourses();
        return courseList;
    }


    //Allows to add a course with fields filled by the user of the app. Returns a course object

    public Course addCourse(String courseName, int coursePrice, String courseType) throws SQLException, NamingException {
        Course course = courseDAO.addCourse(courseName, coursePrice, courseType);
        return course;
    }

}
