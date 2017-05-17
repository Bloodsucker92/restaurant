package utils;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

/* Util class which provides the error logger to write errors to the log files using log4j*/

public class ErrorLogger {

    private static ErrorLogger instance;
    private static org.apache.logging.log4j.Logger logger;


    public static ErrorLogger getInstance(String className) {
        if (instance == null) {
            logger = LogManager.getLogger(className);
            instance = new ErrorLogger();
        }
        return instance;
        }

    public void writeError (Exception message){
        logger.error(message.toString());
    }

    public void writeWarning (String message) {
        logger.warn(message);
    }

    public void writeInfo (String message) {logger.info(message);}


}

