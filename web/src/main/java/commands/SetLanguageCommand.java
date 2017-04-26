package commands;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*Defines logic for SetLanguage command*/

public class SetLanguageCommand extends ActionCommand{

    private final String BASENAME = "locale_";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String lang = request.getParameter("language");
        HttpSession session = request.getSession();
        if (lang!=null) {
            session.setAttribute("locale", BASENAME + lang.toLowerCase());
        }

        return null;
    }
}
