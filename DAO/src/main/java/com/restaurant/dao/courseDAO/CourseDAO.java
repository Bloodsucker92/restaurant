package com.restaurant.dao.courseDAO;


import com.restaurant.dao.beans.Course;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CourseDAO {

    Course addCourse(String courseName, int coursePrice, String courseType) throws SQLException, NamingException;

    ArrayList<Course> showAllCourses() throws SQLException, NamingException;
}