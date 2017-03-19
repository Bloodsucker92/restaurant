package com.restaurant.logics.courses;


import com.restaurant.dao.beans.Course;
import com.restaurant.dao.courseDAO.CourseDAOImpl;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;

public class CourseLogic {
    private static CourseLogic instance;
    private static CourseDAOImpl courseDAO = CourseDAOImpl.getInstance();

    private CourseLogic(){
    }

    public static CourseLogic getInstance() {
        if (instance == null) {
            instance = new CourseLogic();
        }
        return instance;
    }

    public ArrayList<Course> showCourses() throws SQLException, NamingException {
        ArrayList<Course> courseList = courseDAO.showAllCourses();
        return courseList;
    }

    public Course addCourse(String courseName, int coursePrice, String courseType) throws SQLException, NamingException {
        Course course = courseDAO.addCourse(courseName, coursePrice, courseType);
        return course;
    }

}
