package com.restaurant.service.interfaces;

import com.restaurant.dao.pojos.User;
import com.restaurant.service.exceptions.ServiceException;

import java.util.List;

public interface IUserService extends IService<User> {

    User findUserByLoginAndPassword(String enterLogin, String enterPassword) throws ServiceException;

    User getUserByLogin(String login) throws ServiceException;

    List<User> getAllUsers () throws ServiceException;


}
