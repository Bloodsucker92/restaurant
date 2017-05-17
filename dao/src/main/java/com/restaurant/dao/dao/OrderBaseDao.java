package com.restaurant.dao.dao;


import com.restaurant.dao.dao.exceptions.DaoException;
import com.restaurant.dao.pojos.Order;

import java.util.List;

public interface OrderBaseDao extends BaseDao<Order> {

    List<Order> getOrders () throws DaoException;

    Order getOrderById(int orderId) throws DaoException;





}
