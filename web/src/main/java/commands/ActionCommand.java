package commands;

import utils.ErrorManager;

import javax.servlet.http.HttpServletRequest;

public abstract class ActionCommand {

    protected static ErrorManager errorManager;

    public ActionCommand() {
        errorManager = ErrorManager.getInstance();
    }

    public abstract String execute(HttpServletRequest request);
}