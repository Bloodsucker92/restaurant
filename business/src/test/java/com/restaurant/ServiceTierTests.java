package com.restaurant;

import com.restaurant.dao.beans.Course;
import com.restaurant.dao.beans.Order;
import com.restaurant.dao.beans.User;
import com.restaurant.dao.exceptions.DaoException;
import com.restaurant.dao.impl.OrderDaoImpl;
import com.restaurant.dao.util.HibernateUtil;
import com.restaurant.service.courses.CourseService;
import com.restaurant.service.login.LoginService;
import com.restaurant.service.orders.OrderService;
import com.restaurant.service.register.RegisterUserService;
import com.restaurant.service.users.DeleteUserService;
import com.restaurant.service.users.ShowUsersService;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Assert;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.*;

/**
 * Unit test for simple App.
 */
public class ServiceTierTests {

    // Course services
    @org.junit.Test
    public void testGetOneCourse () throws DaoException {
        Course course = CourseService.getInstance().getOneCourse(1);
        Assert.assertNotNull(course);
        Assert.assertEquals(course.getCourseName(), "prosciutto");
        Assert.assertEquals(course.getCourseCategory().getId(), new Integer(1));
        Assert.assertEquals(course.getCoursePrice(), new Integer(7));
    }

    @org.junit.Test
    public void testShowCoursesOfCertainType () throws DaoException {
        ArrayList<Course> courses = CourseService.getInstance().showCoursesOfCertainType(2);
        Assert.assertNotNull(courses);
        Assert.assertEquals(courses.size(), 5);
        Assert.assertEquals(courses.get(0).getCourseCategory().getCourseCategory(), "drink");
        Assert.assertEquals(courses.get(1).getCourseName(), "fanta");
    }

    @org.junit.Test
    public void testGetTotalRecordsCount () throws DaoException {
        Long recordCount = CourseService.getInstance().getTotalCourseNumber();
        Assert.assertEquals(recordCount, new Long(21));
    }

    @org.junit.Test
    public void testShowAllCourses () throws DaoException {
        ArrayList<Course> courses = CourseService.getInstance().showAllCourses(0, 5);
        Assert.assertEquals(courses.size(), 5);
        Assert.assertEquals(courses.get(0).getCourseCategory().getCourseCategory(), "pizza");
        Assert.assertEquals(courses.get(1).getCourseName(), "margaritta");
    }

    @org.junit.Test
    public void testAddCourse () throws DaoException {
        CourseService.getInstance().addCourse("borsh", 3, 3);
        ArrayList<Course> courses = CourseService.getInstance().showCoursesOfCertainType(3);
        Assert.assertEquals(courses.get(courses.size()-1).getCourseCategory().getId(), new Integer(3));
        Assert.assertEquals(courses.get(courses.size()-1).getCourseName(), "borsh" );
        Assert.assertEquals(courses.get(courses.size()-1).getCoursePrice(), new Integer(3) );

    }

    @org.junit.Test
    public void testDeleteCourse () throws DaoException {
        ArrayList<Course> courses = CourseService.getInstance().showCoursesOfCertainType(3);
        int courseId = courses.get(courses.size()-1).getId();
        CourseService.getInstance().deleteCourse(courseId);
        Assert.assertNull(CourseService.getInstance().getOneCourse(courseId));
    }

    // Login service

    @org.junit.Test
    public void testGetUserById () throws DaoException {
        User user = LoginService.getInstance().getUserById(4);
        Assert.assertNotNull(user);
        Assert.assertEquals(user.getUsername(), "blackmore");
        Assert.assertEquals(user.getUserpassword(), "blackmore");
    }

    @org.junit.Test
    public void testfindUserByLoginAndPassword () throws DaoException {
        User user = LoginService.getInstance().findUserByLoginAndPassword("denis", "denis");
        Assert.assertNotNull(user);
        Assert.assertEquals(user.getUsername(), "denis");
        Assert.assertEquals(user.getUserpassword(), "denis");

    }

    // Order services

    @org.junit.Test
    public void testGetAllOrders () throws DaoException {
        ArrayList<Order> orders = (ArrayList<Order>) OrderService.getInstance().getAllOrders();
        Assert.assertNotNull(orders);
    }

    @org.junit.Test
    public void testMakeNewOrder () throws DaoException {
        Order order = OrderService.getInstance().makeNewOrder(4, 5);
        Assert.assertNotNull(order);
        Assert.assertEquals(order.getUser().getUsername(), "blackmore");
        Assert.assertEquals(order.getCourseSet().size(), 1);
        OrderService.getInstance().addItemToCurrentOrder(6);
        Assert.assertEquals(order.getUser().getUsername(), "blackmore");
        Assert.assertEquals(order.getCourseSet().size(), 2);
    }

    @org.junit.Test
    public void testDeleteOrder () throws DaoException {
        ArrayList<Order> orders = (ArrayList<Order>) OrderService.getInstance().getAllOrders();
        Assert.assertNotNull(orders);
        int itemId = orders.get(orders.size()-1).getId();
        OrderService.getInstance().deleteOrder(5, itemId);
        OrderService.getInstance().deleteOrder(6, itemId);
        ArrayList<Order> renewedOrders = (ArrayList<Order>) OrderService.getInstance().getAllOrders();
        Assert.assertNotEquals(orders.size(), renewedOrders.size());
        Assert.assertEquals(orders.size()-renewedOrders.size(), 1);

    }

    //User services

    @org.junit.Test
    public void testShowUsers () throws DaoException {
        ArrayList<User> users = ShowUsersService.getInstance().showUsers();
        Assert.assertNotNull(users);
        Assert.assertEquals(users.get(0).getUsername(), "user");
        Assert.assertEquals(users.get(0).getUserpassword(), "123");
    }

    @org.junit.Test
    public void testAddUser () throws DaoException {

        RegisterUserService.getInstance().registerUser("tom", "jerry");
        ArrayList<User> users = ShowUsersService.getInstance().showUsers();
        Assert.assertNotNull(users);
        Assert.assertEquals(users.get(users.size()-1).getUsername(), "tom");
        Assert.assertEquals(users.get(users.size()-1).getUserpassword(), "jerry");

    }

    @org.junit.Test
    public void testDeleteUser () throws DaoException {
        ArrayList<User> users = ShowUsersService.getInstance().showUsers();
        int userId = users.get(users.size()-1).getId();
        DeleteUserService.getInstance().deleteUser(userId);
        Assert.assertNull(LoginService.getInstance().getUserById(userId));
    }



}
