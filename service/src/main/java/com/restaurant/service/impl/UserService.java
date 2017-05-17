package com.restaurant.service.impl;


import com.restaurant.dao.dao.exceptions.DaoException;
import com.restaurant.dao.pojos.User;
import com.restaurant.dao.dao.UserBaseDao;
import com.restaurant.service.exceptions.ServiceException;
import com.restaurant.service.interfaces.IUserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional (propagation = Propagation.REQUIRED, rollbackFor = DaoException.class)
@Log4j2
public class UserService extends BaseService<User> implements IUserService {


    @Autowired
    private UserBaseDao userDao;


    @Override
    public User findUserByLoginAndPassword(String enterLogin, String enterPassword) throws ServiceException {
        User user;
        try {
            user = (User) userDao.getUserByLoginAndPassword(enterLogin, enterPassword);
        }
        catch (DaoException e) {
            log.error("Error performing find user by login and password service method", e);
            throw new ServiceException(UserService.class, "Error performing find user by login and password service method", e);
        }
        return user;
    }

    @Override
    public User getUserByLogin(String login) throws ServiceException {
        User user;
        try {
            user = (User) userDao.getUserByLogin(login);
        }
        catch (DaoException e) {
            log.error("Error performing find user by login service method", e);
            throw new ServiceException(UserService.class, "Error performing find user by login service method", e);
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() throws ServiceException {
        List<User> list;
        try {
            list = userDao.getAllUsers();
        }
        catch (DaoException e) {
            log.error("Error performing get all users service method", e);
            throw new ServiceException(UserService.class, "Error performing get all users service method", e);
        }
        return list;
    }
}
