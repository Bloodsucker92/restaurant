package com.restaurant.service.orders;

import com.restaurant.dao.beans.Course;
import com.restaurant.dao.beans.Order;
import com.restaurant.dao.beans.User;
import com.restaurant.dao.exceptions.DaoException;
import com.restaurant.dao.impl.OrderDaoImpl;
import com.restaurant.dao.util.HibernateUtil;
import com.restaurant.service.courses.CourseService;
import com.restaurant.service.login.LoginService;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class OrderService {

    private static Logger log = Logger.getLogger(LoginService.class);
    private OrderDaoImpl orderDao = OrderDaoImpl.getInstance();
    private static OrderService instance;
    private HibernateUtil hibernateUtil = HibernateUtil.getHibernateUtil();
    private Session session;
    private Transaction transaction;
    private Order currentOrder = new Order();

    public static OrderService getInstance() {
        if (instance == null) {
            instance = new OrderService();
        }
        return instance;
    }

    public Order makeNewOrder(int userId, int courseId) throws DaoException {
        try {
            User user = LoginService.getInstance().getUserById(userId);
            Course course = CourseService.getInstance().getOneCourse(courseId);
            session = hibernateUtil.getSession();
            transaction = session.beginTransaction();
            Order order = new Order();
            order.setUser(user);
            order.getCourseSet().add(course);
            orderDao.saveOrUpdate(order);
            transaction.commit();
            currentOrder = order;
        } catch (HibernateException e) {
            log.error("Error making new order " + e);
            transaction.rollback();
            throw new DaoException(e);
        }
        return currentOrder;
    }

    public void addItemToCurrentOrder(int courseId) throws DaoException {
        try {
            Course course = CourseService.getInstance().getOneCourse(courseId);
            session = hibernateUtil.getSession();
            transaction = session.beginTransaction();
            currentOrder = (Order) orderDao.get(currentOrder.getId());
            currentOrder.getCourseSet().add(course);
            orderDao.saveOrUpdate(currentOrder);
            transaction.commit();
        } catch (HibernateException e) {
            log.error("Error adding item to a current order " + e);
            transaction.rollback();
            throw new DaoException(e);
        }
    }

    public boolean deleteOrder(Integer courseIdToDelete, Integer orderId) throws DaoException {
        boolean orderDeleted = false;
        Order order = new Order();
        try {

            Course course = CourseService.getInstance().getOneCourse(courseIdToDelete);
            session = hibernateUtil.getSession();
            transaction = session.beginTransaction();
            order = (Order) orderDao.get(orderId);
            order.getCourseSet().remove(course);
            orderDao.saveOrUpdate(order);
            if (order.getCourseSet().isEmpty()) {
                orderDao.delete(order);
            }
            transaction.commit();
            orderDeleted = true;
        } catch (HibernateException e) {
            log.error("Error deleting order " + e);
            transaction.rollback();
            throw new DaoException(e);
        }
        return orderDeleted;
    }


    public List<Order> getAllOrders() throws DaoException {
        List<Order> ordersList = new ArrayList<Order>();
        try {
            session = hibernateUtil.getSession();
            transaction = session.beginTransaction();
            ordersList = orderDao.getOrders();
            session.flush();
            transaction.commit();
        } catch (HibernateException e) {
            log.error("Error getting all users " + e);
            transaction.rollback();
            throw new DaoException(e);
        }
        return (ArrayList<Order>) ordersList;

    }
}
