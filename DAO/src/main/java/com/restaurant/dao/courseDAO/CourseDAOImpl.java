package com.restaurant.dao.courseDAO;

import com.restaurant.dao.beans.Course;
import com.restaurant.dao.connectionpool.ConnectionPool;
import com.restaurant.dao.connectionpool.MysqlConnectionPool;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/* Implements CRUD-methods for Course POJO */

public class CourseDAOImpl implements CourseDAO{

    private static CourseDAOImpl instance;


    private ConnectionPool connectionPool;
    private final String ADD_COURSE_QUERY = "INSERT INTO course " +
            "(course_name, course_price, course_category_idCourse_category) VALUES (?, ?, ?);";
    private final String GET_COURSE_CATEGORY_ID_QUERY = "SELECT idCourse_category " +
            "FROM course_category WHERE course_type=?;";
    private final String SHOW_ALL_COURSES_QUERY = "SELECT course_name, course_price, " +
            "course_type FROM Course INNER JOIN \n" +
            "Course_category ON Course.Course_category_idCourse_category" +
            " = Course_category.idCourse_category;";

    public CourseDAOImpl(ConnectionPool connectionPool) {
        setConnectionPool(connectionPool);
    }

    public static CourseDAOImpl getInstance() throws NamingException {
            if (instance == null) {
                    instance = new CourseDAOImpl(MysqlConnectionPool.getInstance());
            }
        return instance;
    }

    public void setConnectionPool(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public Course addCourse(String courseName, int coursePrice, String courseType) throws SQLException, NamingException {
        Connection connection = connectionPool.getConnection();
        Course course = new Course();
        int courseCategoryId = getCourseCategoryId(courseType);
        PreparedStatement statement = connection.prepareStatement(ADD_COURSE_QUERY);
        statement.setString(1, courseName);
        statement.setInt(2, coursePrice);
        statement.setInt(3, courseCategoryId);
        statement.executeUpdate();
        course.setCourseName(courseName);
        course.setCoursePrice(coursePrice);
        statement.close();
        connection.close();

        return course;
    }

    public int getCourseCategoryId(String courseType) throws NamingException, SQLException {
        Connection connection = connectionPool.getConnection();
        int courseTypeId = 0;
        PreparedStatement statement = connection.prepareStatement(GET_COURSE_CATEGORY_ID_QUERY);
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
        Connection connection = connectionPool.getConnection();
        List<Course> courseList = new ArrayList();
        PreparedStatement statement = connection.prepareStatement(SHOW_ALL_COURSES_QUERY);
        ResultSet rst = statement.executeQuery();
        Course course = null;
        while (rst.next()) {
            course = new Course(
                    rst.getString("course_name"),
                    Integer.parseInt(rst.getString("course_price")),
                    rst.getString("course_type")
            );
            courseList.add(course);

        }
        rst.close();
        statement.close();
        connection.close();

        return (ArrayList<Course>) courseList;
    }
}
