package utils;


import org.apache.log4j.Logger;

public class ErrorLogger {

    private static ErrorLogger instance;
    private static Logger logger;


    public static ErrorLogger getInstance(String className) {
        if (instance == null) {
            logger = Logger.getLogger(className);
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

