package commands.client;

import commands.*;

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
 }
 ;

ActionCommand command;

public ActionCommand getCurrentCommand() {
 return command;
}
}