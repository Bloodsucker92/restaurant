package com.restaurant.service.interfaces;


import com.restaurant.service.exceptions.ServiceException;

import java.io.Serializable;

public interface IService<T> {

    int add(T t) throws ServiceException;

    void update(T t) throws ServiceException;

    T get(Serializable id) throws ServiceException;

    void delete(T t) throws ServiceException;


}
