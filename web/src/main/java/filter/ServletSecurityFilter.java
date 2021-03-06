package filter;


import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/* Defines logic of user's authentification */

@WebFilter(urlPatterns = { "*.jsp"})
public class ServletSecurityFilter implements Filter {

  public void destroy() {
  }

  /*Checks the user type from the session. If the use type is null, then sets the GUEST type
  * returns the path to the login page*/

  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse resp = (HttpServletResponse) response;
    HttpSession session = req.getSession();


    ClientType type = (ClientType) session.getAttribute("userType");
    if (type == null ) {
      type = ClientType.GUEST;
      session.setAttribute("userType", type);
//      RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/index.jspx");
//      dispatcher.forward(req, resp);
      return;
    }

    // pass the request along the filter chain
    chain.doFilter(request, response);


  }

  public void init(FilterConfig fConfig) throws ServletException {

  }
}
