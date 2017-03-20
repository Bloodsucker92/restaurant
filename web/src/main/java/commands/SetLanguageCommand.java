package commands;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SetLanguageCommand extends ActionCommand{

    private final String BASENAME = "locale_";
    //Cookies store duration
    private final int ONE_DAY = 60*60*24;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String lang = request.getParameter("language");//'av' means action value
        HttpSession session = request.getSession();
        session.setAttribute("locale", BASENAME + lang.toLowerCase());
        Cookie c = new Cookie("locale", lang);
        c.setMaxAge(ONE_DAY);
        response.addCookie(c);
        return null;
    }
}
