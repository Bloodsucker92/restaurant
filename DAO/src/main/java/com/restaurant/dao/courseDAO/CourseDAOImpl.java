package com.restaurant.dao.courseDAO;

import com.restaurant.dao.beans.Course;
import com.restaurant.dao.connectionpool.DatabaseConnectionPool;
import com.restaurant.dao.userDAO.UserDAOImpl;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDAOImpl implements CourseDAO{

    Connection connection = null;
    private static CourseDAOImpl instance;

    private CourseDAOImpl() throws ClassNotFoundException, SQLException, IOException{

    }

    public static CourseDAOImpl getInstance() {
        try {
            if (instance == null) {
                instance = new CourseDAOImpl();
            }
        }
        catch (ClassNotFoundException | SQLException | IOException e){
            e.printStackTrace();
        }
        return instance;
    }

    public Course addCourse(String courseName, int coursePrice, String courseType) throws SQLException, NamingException {
        Course course = new Course();

        int courseCategoryId = getCourseCategoryId(courseType);
        connection = DatabaseConnectionPool.getConnection();

        PreparedStatement myStatement = connection.prepareStatement("INSERT IGNORE INTO restaurant.course (course_name, course_price, course_category_idCourse_category) VALUES (?, ?, ?);");
        myStatement.setString(1, courseName);
        myStatement.setInt(2, coursePrice);
        myStatement.setInt(3, courseCategoryId);
        myStatement.executeUpdate();

        course.setCourseName(courseName);
        course.setCoursePrice(coursePrice);
        myStatement.close();
        connection.close();

        return course;
    }

    public int getCourseCategoryId(String courseType) throws NamingException, SQLException {
        int courseTypeId = 0;
        connection = DatabaseConnectionPool.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT idCourse_category FROM course_category WHERE course_type=?;");
        statement.setString(1, courseType);
        ResultSet rst = statement.executeQuery();

        while (rst.next()) {
            courseTypeId = rst.getInt("idCourse_category");
        }
        statement.close();
        rst.close();
        connection.close();

        return courseTypeId;
    }

    public ArrayList<Course> showAllCourses() throws SQLException, NamingException {

        List<Course> courseList = new ArrayList();
        connection = DatabaseConnectionPool.getConnection();
        PreparedStatement myStatement = connection.prepareStatement("SELECT course_name, course_price, course_type FROM Restaurant.Course INNER JOIN \n" +
                "Restaurant.Course_category ON Restaurant.Course.Course_category_idCourse_category = Restaurant.Course_category.idCourse_category;");
        ResultSet rst = myStatement.executeQuery();
        while (rst.next()) {
            Course course = new Course();
            course.setCourseName(rst.getString("course_name"));
            course.setCoursePrice(Integer.parseInt(rst.getString("course_price")));
            course.setCourseType(rst.getString("course_type"));
            courseList.add(course);

        }
        rst.close();
        myStatement.close();
        connection.close();

        return (ArrayList<Course>) courseList;
    }
}
