package com.restaurant.service.impl;

import com.restaurant.dao.dao.CourseBaseDao;
import com.restaurant.dao.dao.exceptions.DaoException;
import com.restaurant.dao.pojos.Course;
import com.restaurant.dao.pojos.Order;
import com.restaurant.dao.dao.OrderBaseDao;
import com.restaurant.dao.pojos.User;
import com.restaurant.service.exceptions.ServiceException;
import com.restaurant.service.interfaces.IOrderService;
import com.restaurant.service.interfaces.IUserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = DaoException.class)
@Log4j2
public class OrderService extends BaseService<Order> implements IOrderService {

    @Autowired
    private OrderBaseDao orderDao;

    @Autowired
    private CourseBaseDao courseDao;

    @Autowired
    private IUserService userService;

    private Order currentOrder = new Order();

    @Override
    public List<Order> getOrders() throws ServiceException {
        try {
            return orderDao.getOrders();
        } catch (DaoException e) {
            log.error("Error performing getting orders service method", e);
            throw new ServiceException(OrderService.class, "Error performing getting orders service method", e);
        }
    }

    @Override
    public Order makeNewOrder(User user, int courseId) throws ServiceException {
        Course course = null;
        try {
            course = courseDao.get(courseId);

            Order order = new Order();
            order.setUser(user);
            order.getCourseSet().add(course);
            int orderId = orderDao.add(order);
            order.setId(orderId);
            currentOrder = order;
        } catch (DaoException e) {
            log.error("Error performing make new order service method", e);
            throw new ServiceException(OrderService.class, "Error performing make new order service method", e);
        }
        return currentOrder;
    }

    @Override
    public void addItemToCurrentOrder(int orderId, int courseId) throws ServiceException {
        Course course = null;
        try {
            course = (Course) courseDao.get(courseId);
            currentOrder = (Order) orderDao.get(orderId);
            currentOrder.getCourseSet().add(course);
            orderDao.update(currentOrder);
        } catch (DaoException e) {
            log.error("Error performing add item to current order service method", e);
            throw new ServiceException(OrderService.class, "Error performing add item to current order service method", e);
        }
    }

    @Override
    public void deleteItemFromOrder(int courseIdToDelete, int orderId) throws ServiceException {
        try {
            Course courseToDelete = (Course) courseDao.get(courseIdToDelete);
            Order order = (Order) orderDao.getOrderById(orderId);
            order.getCourseSet().remove(courseToDelete);
            orderDao.update(order);
        } catch (DaoException e) {
            log.error("Error performing delete item from current order service method", e);
            throw new ServiceException(OrderService.class, "Error performing delete item from current order service method", e);
        }
    }

    public Order getOrderById(int orderId) throws ServiceException {
        Order order;
        try {
            order = orderDao.getOrderById(orderId);
        } catch (DaoException e) {
            log.error("Error performing get order by id service method", e);
            throw new ServiceException(OrderService.class, "Error performing get order by id service method", e);
        }
        return order;
    }
}




