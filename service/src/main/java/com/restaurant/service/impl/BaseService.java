package com.restaurant.service.impl;

import com.restaurant.dao.dao.BaseDao;
import com.restaurant.dao.dao.exceptions.DaoException;
import com.restaurant.service.exceptions.ServiceException;
import com.restaurant.service.interfaces.IService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;


@Transactional
@Log4j2
public class BaseService<T> implements IService<T> {

    @Autowired
    @Qualifier (value = "baseDaoImpl")
    private BaseDao<T> baseDao;

    @Autowired
    public BaseService(BaseDao<T> baseDao) {
        this.baseDao = baseDao;
    }

    public BaseService() {
    }

    @Override
    public int add(T t) throws ServiceException {
        int id = 0;
        try {
            id = baseDao.add(t);
        } catch (DaoException e) {
            log.error("Error performing add service method", e);
            throw new ServiceException("Error performing add service method", e);
        }
        return id;
    }

    @Override
    public void update(T t) throws ServiceException {
        try {
            baseDao.update(t);
        } catch (DaoException e) {
            log.error("Error performing update service method", e);
            throw new ServiceException("Error performing update service method", e);
        }
    }

    @Override
    public T get(Serializable id) throws ServiceException {
        try {
            return baseDao.get(id);
        } catch (DaoException e) {
            log.error("Error performing get service method", e);
            throw new ServiceException("Error performing get service method", e);
        }
    }

    @Override
    public void delete(T t) throws ServiceException {
        try {
            baseDao.delete(t);
        } catch (DaoException e) {
            log.error("Error performing delete service method", e);
            throw new ServiceException("Error performing delete service method", e);
        }
    }


}
