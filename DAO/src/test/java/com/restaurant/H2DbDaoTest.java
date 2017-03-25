package com.restaurant;

import com.restaurant.dao.beans.Course;
import com.restaurant.dao.beans.User;
import com.restaurant.dao.courseDAO.CourseDAO;
import com.restaurant.dao.courseDAO.CourseDAOImpl;
import com.restaurant.dao.userDAO.UserDAO;
import com.restaurant.dao.userDAO.UserDAOImpl;
import com.util.H2ConnectionPool;
import junit.framework.TestCase;
import org.junit.Test;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.SQLException;


/* Provides methods to test CRUD operations with POJOs using H2 in-memory
*  database filled with actual data which is used in the app
*/

public class H2DbDaoTest extends TestCase {

    /*User dao tests*/

    @Test
    public void testAddAndGetUser () throws SQLException, NamingException {
        UserDAO dao = new UserDAOImpl(H2ConnectionPool.getInstance());
        User testUser = dao.addUser("test", "test");
        assertNotNull(testUser);
        assertEquals("test", testUser.getUsername());
        assertEquals("test", testUser.getUserpassword());
        assertEquals("u", testUser.getAccess());
    }

    @Test
    public void testShowAllUsers () throws SQLException, NamingException {
        UserDAO dao = new UserDAOImpl(H2ConnectionPool.getInstance());
        assertNotNull(dao.showUsers());
    }

    @Test
    public void testDeleteUser () throws SQLException, NamingException {
        UserDAO dao = new UserDAOImpl(H2ConnectionPool.getInstance());
        User testUser = dao.addUser("testUser", "testPassword");
        assertEquals("testUser", testUser.getUsername());
        assertEquals("testPassword", testUser.getUserpassword());
        assertTrue(dao.deleteUser("testUser", "testPassword"));
        assertNull(dao.getUser("testUser", "testPassword"));
    }

    /*Course dao tests*/

    @Test
    public void testAddAndGetCourse () throws SQLException, NamingException {
        CourseDAO dao = new CourseDAOImpl((H2ConnectionPool.getInstance()));
        Course testCourse = dao.addCourse("test", 3, "drink");
        assertNotNull(testCourse);
        assertEquals("test", testCourse.getCourseName());
        assertEquals(3, testCourse.getCoursePrice());
    }

    @Test
    public void testGetCourseCategoryId () throws SQLException, NamingException {
        CourseDAOImpl courseDAO = new CourseDAOImpl(H2ConnectionPool.getInstance());
        int courseCategoryId = courseDAO.getCourseCategoryId("drink");
        assertNotNull(courseCategoryId);
        assertEquals(2, courseCategoryId);
    }

    @Test
    public void testShowAllCourses () throws SQLException, NamingException {
        CourseDAO courseDAO = new CourseDAOImpl(H2ConnectionPool.getInstance());
        assertNotNull(courseDAO.showAllCourses());
    }



}
