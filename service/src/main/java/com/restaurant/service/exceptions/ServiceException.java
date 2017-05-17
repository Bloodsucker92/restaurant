package com.restaurant.service.exceptions;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ServiceException extends Exception {
    public ServiceException(String msg) {
        super(msg);
    }
    public ServiceException(String msg, Throwable e) {
        super(msg, e);
    }
    public ServiceException(Class clazz, String msg, Throwable e) {
        super(msg, e);
        Logger logger = LogManager.getLogger(clazz);
        logger.log(Level.ERROR, msg, e);
    }
}
