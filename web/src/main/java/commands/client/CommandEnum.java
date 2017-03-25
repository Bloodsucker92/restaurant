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
  try {
   this.command = new LoginCommand();
  } catch (SQLException | NamingException e) {
   ExceptionManager.writeErrorToLog(CommandEnum.class.getName(), e, true);
  }
 }
},
LOGOUT {
 {
 this.command = new LogoutCommand();
 }
},
 REGISTER {
  {
   try {
    this.command = new RegisterCommand();
   }
   catch (SQLException | NamingException e) {
    ExceptionManager.writeErrorToLog(CommandEnum.class.getName(), e, true);
   }
  }
 },
 SHOWCOURSES {
  {
   try {
    this.command = new ShowCoursesCommand();
   } catch (SQLException | NamingException e) {
    ExceptionManager.writeErrorToLog(CommandEnum.class.getName(), e, true);
   }
  }
 },
 ADDCOURSE {
  {
   try {
    this.command = new AddCourseCommand();
   } catch (SQLException | NamingException e) {
    ExceptionManager.writeErrorToLog(CommandEnum.class.getName(), e, true);
   }
  }
 },

 SETLANGUAGE {
  {
   this.command = new SetLanguageCommand();
  }
 },

 SHOWUSERS {
  {
   try {
    this.command = new ShowUsersCommand();
   } catch (SQLException | NamingException e) {
    ExceptionManager.writeErrorToLog(CommandEnum.class.getName(), e, true);
   }
  }
 },
 DELETEUSER {
  {
   try {
    this.command = new DeleteUserCommand();
   } catch (SQLException | NamingException e) {
    ExceptionManager.writeErrorToLog(CommandEnum.class.getName(), e, true);
   }
  }
 }
 ;

ActionCommand command;

/* Returns the object created by a certain command*/

public ActionCommand getCurrentCommand() {
 return command;
}
}