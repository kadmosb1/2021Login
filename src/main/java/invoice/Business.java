package invoice;

/*
 * Een ondernemer is klein of normaal voor de belastingdienst:
 *
 * - Kleine ondernemers hebben een omzet die lager ligt dan € 20.000. Zij hoeven geen BTW te heffen bij hun klanten.
 * - Je wordt in alle andere gevallen als normale ondernemer gezien. In dat geval hef je BTW volgens de regels.
 */
public abstract class Business {

    /*
     * Kleine en normale ondernemers wijken bij het opstellen van de factuur af bij de laatste regels van de factuur.
     */
    protected abstract String getSpecificLastLines (double priceExcludingVAT);
    protected abstract String getSubject ();

    /*
     * Dit is de template method. Hierin worden de laatste regels van de factuur (met de optelling van BTW en
     * totaalbedrag) onderaan de factuur getoond (bij kleine ondernemers gebeurt dit anders dan bij normale
     * ondernemers). Deze laatste regels bestaan bijvoorbeeld (bij een kleine ondernemer) uit:
     *
     *      =                                            ---------- + =
     *      =                                   Totaal   € 1.000,00   =
     */
    public String getLastLinesOfInvoice (double priceExcludingVAT) {

        // Op de eerste regel wordt óf 'subtotaal' getoond (bij normale ondernemers) of 'totaal' (want bij kleine
        // ondernemers wordt geen BTW in rekening gebracht).
        String subject = getSubject ();

        String lines = "";
        lines += InvoiceFormatter.getLine (true, "---------- +");
        lines += InvoiceFormatter.getLine (true, "%-9s € %8.2f  ", subject, priceExcludingVAT);
        lines += getSpecificLastLines(priceExcludingVAT);
        return lines;
    }
}