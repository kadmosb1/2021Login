package invoice;

/*
 * Deze Class wordt gebruikt om voor logging terug te kunnen vallen op een verkorte vorm.
 */
public class Logging {
    public static void printLog (String message) {
        logging.Logging.getInstance ().printLog (message);
    }
}
