package commands;

import utils.ExceptionManager;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/* Defines common abstract execute() method which is implemented in each command class */

public abstract class ActionCommand {

    protected static ExceptionManager exceptionManager;

    public ActionCommand() {
        exceptionManager = ExceptionManager.getInstance();
    }

    public abstract String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, NamingException;
}