package com.restaurant;

import com.restaurant.dao.dao.CourseBaseDao;
import com.restaurant.dao.dao.OrderBaseDao;
import com.restaurant.dao.dao.UserBaseDao;
import com.restaurant.dao.dao.exceptions.DaoException;
import com.restaurant.dao.pojos.Course;
import com.restaurant.dao.pojos.Order;
import com.restaurant.dao.pojos.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@ContextConfiguration(locations = "classpath*:/beans-dao-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional(transactionManager = "transactionManager")
public class OrderDaoImplTest {

    @Qualifier("orderDao")
    @Autowired
    OrderBaseDao orderDao;

    @Qualifier("courseDao")
    @Autowired
    CourseBaseDao courseBaseDao;

    @Qualifier("userDao")
    @Autowired
    UserBaseDao userBaseDao;

    @Test
    public void testAddUpdateAndDeleteOrder () throws DaoException {
        Course courseOne = courseBaseDao.get(1);
        Course courseTwo = courseBaseDao.get(2);
        Set<Course> courses = new HashSet<>();
        courses.add(courseOne);
        courses.add(courseTwo);
        User user = (User) userBaseDao.getUserByLogin("user");
        Order order = new Order();
        order.setUser(user);
        order.setCourseSet(courses);
        int id = orderDao.add(order);
        Assert.assertNotNull(orderDao.getOrders());
        Assert.assertEquals(new Integer(id), orderDao.getOrderById(id).getId());
        Assert.assertEquals("user", orderDao.getOrders().get(orderDao.getOrders().size()-1).getUser().getUsername());
        Assert.assertEquals(courses, orderDao.getOrders().get(orderDao.getOrders().size()-1).getCourseSet());
        User userTwo = (User) userBaseDao.getUserByLogin("denis");
        order.setUser(userTwo);
        orderDao.update(order);
        Assert.assertEquals("denis", orderDao.getOrders().get(orderDao.getOrders().size()-1).getUser().getUsername());
        orderDao.delete(order);
        Assert.assertTrue(orderDao.getOrders().isEmpty());

    }
}
