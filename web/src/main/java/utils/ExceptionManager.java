package utils;


/* Util class which provides the error manager to write errors to the log files using log4j*/


public class ExceptionManager {

    private static ExceptionManager instance;
    private static ErrorLogger logger;

    public ExceptionManager() {
    }

    public static ExceptionManager getInstance() {
        if (instance == null) {
            instance = new ExceptionManager();
        }
        return instance;
    }

    public static void writeErrorToLog(String className, Exception e, boolean toLog){
        if (toLog){
            logger.getInstance(className).writeError(e);
        }

    }

}
