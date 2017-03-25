package commands;

import utils.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*Defines logic for the Empty command*/

public class EmptyCommand extends ActionCommand {
@Override
public String execute(HttpServletRequest request, HttpServletResponse response) {

 /* Redirecting to the login page in case of the exception or accessing the controller directly
  */
 String page = ConfigurationManager. getProperty("path.page.login");
 return page;
}
}