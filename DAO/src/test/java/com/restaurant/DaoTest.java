package com.restaurant;

import com.restaurant.dao.beans.Course;
import com.restaurant.dao.beans.User;
import com.restaurant.dao.connectionpool.ConnectionPool;
import com.restaurant.dao.courseDAO.CourseDAO;
import com.restaurant.dao.courseDAO.CourseDAOImpl;
import com.restaurant.dao.userDAO.UserDAO;
import com.restaurant.dao.userDAO.UserDAOImpl;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import javax.naming.NamingException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

/* Uses Mockito-framework to test if the database connection is set up
   and CRUD-methods of dao layer perform correctly
 * */


@RunWith(MockitoJUnitRunner.class)
public class DaoTest extends TestCase {
    @Mock
    Connection mockConn;
    @Mock
    PreparedStatement mockStatement;
    @Mock
    ResultSet mockResultSet;

    ConnectionPool testConnectionPool;

    @Before
    public void setUp() throws SQLException, NamingException {
        when(mockConn.prepareStatement(anyString())).thenReturn(mockStatement);
        doNothing().when(mockStatement).setString(anyInt(), anyString());
        when(mockStatement.executeQuery()).thenReturn(mockResultSet);

        when(mockResultSet.first()).thenReturn(Boolean.TRUE);
        when(mockResultSet.getString(anyString())).thenReturn(anyString());

        testConnectionPool = new ConnectionPool() {
            @Override
            public Connection getConnection() throws SQLException {
                return mockConn;
            }
        };
    }



    @Test
    public void testGetUser() throws SQLException, NamingException {
        UserDAO dao = new UserDAOImpl(testConnectionPool);
        assertNotNull(dao.getUser("test", "test"));
    }

    @Test
    public void testAddUser() throws SQLException, NamingException {
        UserDAO dao = new UserDAOImpl(testConnectionPool);
        User testUser = dao.addUser("test", "test");
        assertNotNull(testUser);
        assertNotNull(testUser.getUsername());
        assertNotNull(testUser.getUserpassword());
        assertNotNull(testUser.getAccess());
    }

    @Test
    public void testShowUsers () throws SQLException, NamingException {
        UserDAO dao = new UserDAOImpl(testConnectionPool);
        assertNotNull(dao.showUsers());
    }

    @Test
    public void testDeleteUser () throws SQLException, NamingException {
        UserDAO dao = new UserDAOImpl(testConnectionPool);
        assertTrue(dao.deleteUser("test", "test22"));
    }


    @Test
    public void testAddCourse() throws SQLException, NamingException {
        CourseDAO courseDAO = new CourseDAOImpl(testConnectionPool);
        Course testCourse = courseDAO.addCourse("test", 3, "test");
        assertNotNull(testCourse);
        assertNotNull(testCourse.getCourseName());
        assertNotNull(testCourse.getCoursePrice());

    }

    @Test
    public void testGetCourseCategoryId () throws SQLException, NamingException {
        CourseDAOImpl courseDAO = new CourseDAOImpl(testConnectionPool);
        int courseCategoryId = courseDAO.getCourseCategoryId("drink");
        assertNotNull(courseCategoryId);
    }

    @Test
    public void testShowAllCourses () throws SQLException, NamingException {
        CourseDAO courseDAO = new CourseDAOImpl(testConnectionPool);
        assertNotNull(courseDAO.showAllCourses());
    }




}
