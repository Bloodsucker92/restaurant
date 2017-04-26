package commands.client;

import commands.*;
import commands.ShowUsersCommand;
import utils.ExceptionManager;

import javax.naming.NamingException;
import java.sql.SQLException;

/* Holds the names of all the commands available
*  Creates the command object of type defined by the request parameter
* */

public enum CommandEnum {

    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    },
    REGISTER {
        {
            this.command = new RegisterCommand();
        }
    },
    SHOWCOURSES {
        {
            this.command = new ShowCoursesCommand();
        }
    },
    ADDCOURSE {
        {
            this.command = new AddCourseCommand();
        }
    },

    SETLANGUAGE {
        {
            this.command = new SetLanguageCommand();
        }
    },

    SHOWUSERS {
        {
            this.command = new ShowUsersCommand();
        }
    },
    DELETEUSER {
        {
            this.command = new DeleteUserCommand();
        }
    },

    DELETECOURSE {
        {
            this.command = new DeleteCourseCommand();

        }
    },

    MAKEORDER {
        {
            this.command = new MakeOrderCommand();
        }
    },
    SHOWORDERS {
        {
            this.command = new ShowOrdersCommand();
        }
    },

    DELETEORDER {
        {
            this.command = new DeleteOrderCommand();
        }
    };

    ActionCommand command;

/* Returns the object created by a certain command*/

    public ActionCommand getCurrentCommand() {
        return command;
    }
}