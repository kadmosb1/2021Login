package invoice;

public class InvoiceFormatter {

    private static final int WIDTH = 68;

    /*
     * De header bestaat uit een lijn met 71 ='s.
     */
    public static String getHeader () {
        return String.format ("%s%n", "=".repeat (WIDTH + 4));
    }

    /*
     * Een regel opent met een '=' en eindigt op positie 71 met een '='.
     * Object... betekent dat er 0, 1 of meer parameters van het type Object worden meegegeven aan getLine.
     */
    public static String getLine (boolean rightAligned, String line, Object... args) {

        // formatters (%s, %d etc.) in line worden vervangen door de elementen van args.
        line = String.format (line, args);

        // Als de regel breder wordt dan de beschikbare breedte (die in WIDTH is vastgelegd), dan worden
        // 0 trailing spaces voor (indien rechts uitgelijnd) of achter line toegevoegd.
        String trailingSpaces = line.length () > WIDTH ? "" : " ".repeat (WIDTH - line.length ());

        // De regel opent met een '=' en als de regel rechts moet worden uitgelijnd, wordt de regel
        // links opgevuld met spaties.

        line = rightAligned ? trailingSpaces + line : line + trailingSpaces;

        // De regel wordt aangevuld met spaties en aan het einde met een '='.
        line = "= " + line + " =\r\n";
        return line;
    }

    /*
     * Een lege regel start net als een gewone regel met een '=' en eindigt met een '='.
     */
    public static String getEmptyLine () {
        return getLine (true, "");
    }

    /*
     * De footer is gelijk aan de header.
     */
    public static String getFooter () {
        return getHeader ();
    }
}