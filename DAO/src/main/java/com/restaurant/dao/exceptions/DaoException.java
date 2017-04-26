package com.restaurant.dao.exceptions;


public class DaoException extends Exception {

    private Exception exception;

    public DaoException(Exception exception) {
        this.exception = exception;
    }

    public void renderException(){

    }


}
