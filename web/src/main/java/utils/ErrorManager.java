package utils;



public class ErrorManager {

    private static ErrorManager instance;
    private static ErrorLogger logger;

    public ErrorManager() {
    }

    public static ErrorManager getInstance() {
        if (instance == null) {
            instance = new ErrorManager();
        }
        return instance;
    }

    public void writeErrorToLog(String className, Exception e, boolean toLog){
        if (toLog){
            logger.getInstance(className).writeError(e);
        }

    }

}
