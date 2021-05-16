package logging;

import login.Login;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
 * Hiermee wordt logging van fouten en activiteiten bijgehouden in een file die bij de start van de nieuwe dag
 * wordt aangemaakt.
 * Voor deze functionaliteit wordt gebruik gemaakt van het Singleton Pattern.
 */
public class Logging {

    private static Logging singleton;
    private File logFile;

    /*
     * Er wordt een geformatteerde datum (bijv. 10-05-2021) opgevraagd.
     */
    protected String getFormattedDate () {
        return LocalDate.now ().format (DateTimeFormatter.ofPattern ("dd-MM-yyyy"));
    }

    /*
     * Er wordt een geformatteerde datum en tijd (bijv. 10-05-2021 22:01:48") opgevraagd.
     */
    protected String getFormattedDateAndTime() {
        return getFormattedDate () + LocalDateTime.now ().format (DateTimeFormatter.ofPattern (" hh:mm:ss"));
    }

    /*
     * Bij toepassing van het Singleton pattern blijft de constructor private.
     */
    private Logging () {
    }

    /*
     * Er kan maar één object van Logging worden aangemaakt. Die kan worden opgevraagd met deze methode (toepassing
     * van het Singleton Pattern).
     */
    public static Logging getInstance () {

        if (singleton == null) {
            singleton = new Logging ();
        }

        return singleton;
    }

    /*
     * Logfiles hebben bijv. de naam "10-05-2021.log" en staan in de genoemde directory.
     */
    private void setLogFilename () {
        logFile = new File ("src\\main\\resources\\Logging\\" + getFormattedDate () + ".log");
    }

    /*
     * Er wordt gecontroleerd of de logfile al bestaat.
     */
    protected boolean logFileExists () {
        return logFile.exists();
    }

    protected String getLogString (String message) {

        String header = "";
        setLogFilename ();

        /*
         * Als de logfile nog niet bestaat, wordt een header bovenaan de nieuwe file toegevoegd.
         */
        if (!logFileExists()) {
            header = String.format ("%-19s %-20s %s%n", "Date", "Gebruikersnaam", "Logging");
        }

        /*
         * Elke regel begint met een datum en tijd.
         */
        String pre = String.format ("%-19s ", getFormattedDateAndTime());

        /*
         * Daarna volgt de naam van de gebruiker (als die bekend is).
         */
        pre += String.format ("%-20s ", Login.getInstance ().getUserNameOfActiveUser ());

        /*
         * De message wordt (evt. met een header) na datum/tijd en gebruiker toegevoegd.
         */
        return String.format ("%s%s%s%n", header, pre, message);
    }

    public void printLog (String message) {

        try {
            String logString = getLogString (message);
            // Regels worden aan het einde van de file toegevoegd.
            PrintWriter writer = new PrintWriter (new FileOutputStream (logFile,true));
            writer.append (logString);
            writer.close ();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace ();
        }
    }
}
