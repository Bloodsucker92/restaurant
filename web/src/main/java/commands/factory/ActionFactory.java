package commands.factory;

import utils.ExceptionManager;
import commands.ActionCommand;
import commands.EmptyCommand;
import commands.client.CommandEnum;
import utils.MessageManager;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/* Provides logic of defining the command obtained from the request*/

public class ActionFactory {

  private ExceptionManager exceptionManager = ExceptionManager.getInstance();

  public ActionCommand defineCommand(HttpServletRequest request) {
    ActionCommand current = new EmptyCommand();
    // retrieving the command name from the request
    String action = request.getParameter("command");
    if (action == null || action.isEmpty()) {
      // if the command is not set in the current request
      return current;
    }
    // retrieving the object which corresponds with the command name
    try {
      CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());

      current = currentEnum.getCurrentCommand();
    } catch (IllegalArgumentException e) {
      exceptionManager.writeErrorToLog(ActionFactory.class.getName(), e, true);
      request.setAttribute("wrongAction", action + MessageManager.getProperty("message.wrongaction"));
    }
    return current;
  }
}