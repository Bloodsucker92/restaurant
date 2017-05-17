package com.restaurant.dao.dao.exceptions;


import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DaoException extends Exception {

    public DaoException(String msg) {
        super(msg);
    }
    public DaoException(String msg, Throwable e) {
        super(msg, e);
    }
    public DaoException(Class clazz, String msg, Throwable e) {
        super(msg, e);
        Logger logger = LogManager.getLogger(clazz);
        logger.log(Level.ERROR, msg, e);
    }


}
