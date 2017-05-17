package com.restaurant.service.interfaces;

import com.restaurant.dao.pojos.Order;
import com.restaurant.dao.pojos.User;
import com.restaurant.service.exceptions.ServiceException;

import java.util.List;

public interface IOrderService extends IService<Order> {

    List<Order> getOrders () throws ServiceException;

    Order makeNewOrder (User user, int courseId) throws ServiceException;

    void addItemToCurrentOrder (int orderId, int courseId) throws ServiceException;

    void deleteItemFromOrder (int courseIdToDelete, int orderId) throws ServiceException;

    Order getOrderById (int orderId) throws ServiceException;

}
