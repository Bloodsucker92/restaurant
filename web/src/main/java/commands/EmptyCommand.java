package commands;

import utils.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmptyCommand extends ActionCommand {
@Override
public String execute(HttpServletRequest request, HttpServletResponse response) {
 /* в случае ошибки или прямого обращения к контроллеру
  * переадресация на страницу ввода логина */
 String page = ConfigurationManager. getProperty("path.page.login");
 return page;
}
}