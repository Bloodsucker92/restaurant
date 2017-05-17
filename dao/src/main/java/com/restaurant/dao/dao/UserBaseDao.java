package com.restaurant.dao.dao;



import com.restaurant.dao.dao.exceptions.DaoException;

import java.util.List;

public interface UserBaseDao<User> extends BaseDao<User> {

    User getUserByLoginAndPassword(String login, String password) throws DaoException;

    User getUserByLogin(String login) throws DaoException;

    List<User> getAllUsers () throws DaoException;

}
