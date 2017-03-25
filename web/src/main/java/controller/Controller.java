package controller;

import commands.ActionCommand;
import commands.factory.ActionFactory;
import utils.ConfigurationManager;
import utils.ExceptionManager;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*The Front Controller class */

@WebServlet("/controller")
public class Controller extends HttpServlet {

  private ExceptionManager exceptionManager = ExceptionManager.getInstance();


  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    processRequest(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    processRequest(request, response);
  }

  private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String page = null;
    // defining command obtained from the jsp
    ActionFactory client = new ActionFactory();
    ActionCommand command = client.defineCommand(request);
    /*
     * invoking the implemented execute() method and passing parameters to the certain
     * command class
     */
    try {
      page = command.execute(request, response);
    } catch (SQLException | NamingException e) {
      exceptionManager.writeErrorToLog(Controller.class.getName(), e, true);
    }
    // returns the response page
    if (page != null) {
      RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
      // calling the page and providing the response to the user
      dispatcher.forward(request, response);
    } else {
      page = ConfigurationManager.getProperty("path.page.index");
      RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
      dispatcher.forward(request, response);

    }
  }
}