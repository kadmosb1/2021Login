package logging;

import login.Login;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logging {

    private static Logging singleton;
    private File logFile;

    protected String getFormattedDate () {
        return LocalDate.now ().format (DateTimeFormatter.ofPattern ("dd-MM-yyyy"));
    }

    protected String getFormattedDateAndTime() {
        return getFormattedDate () + LocalDateTime.now ().format (DateTimeFormatter.ofPattern (" hh:mm:ss"));
    }

    private Logging () {
    }

    public static Logging getInstance () {

        if (singleton == null) {
            singleton = new Logging ();
        }

        return singleton;
    }

    private void setLogFilename () {
        logFile = new File ("src\\main\\resources\\Logging\\" + getFormattedDate () + ".log");
    }

    protected boolean logFileExists () {
        return logFile.exists();
    }

    protected String getLogString (String message) {

        String header = "";
        setLogFilename ();

        if (logFileExists()) {
            header = String.format ("%-19s %-20s %s%n", "Date", "Gebruikersnaam", "Logging");
        }

        String pre = String.format ("%-19s ", getFormattedDateAndTime());
        pre += String.format ("%-20s ", Login.getInstance ().getUserNameOfActiveUser ());

        return String.format ("%s%s%s%n", header, pre, message);
    }

    public void printLog (String message) {

        try {
            String logString = getLogString (message);
            PrintWriter writer = new PrintWriter (new FileOutputStream (logFile,true));
            writer.append (logString);
            writer.close ();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace ();
        }
    }
}
