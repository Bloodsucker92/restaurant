package commands;

import commands.ActionCommand;
import utils.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutCommand extends ActionCommand {
@Override
public String execute(HttpServletRequest request, HttpServletResponse response) {
 String page = ConfigurationManager. getProperty("path.page.index");
 // уничтожение сессии
 request.getSession().invalidate();
 return page;
}
}