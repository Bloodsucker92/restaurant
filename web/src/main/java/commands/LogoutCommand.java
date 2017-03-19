package commands;

import commands.ActionCommand;
import utils.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand extends ActionCommand {
@Override
public String execute(HttpServletRequest request) {
 String page = ConfigurationManager. getProperty("path.page.index");
 // уничтожение сессии
 request.getSession().invalidate();
 return page;
}
}