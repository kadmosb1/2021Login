package invoice;

/*
 * Een normale ondernemer is verplicht om BTW aan zijn klanten door te rekenen.
 */
public class BusinessNormal extends Business {

    // Voor de BTW geldt een tarief van 21%.
    private static final int VAT = 21;

    /*
     * Om de prijs inclusief BTW te bepalen, wordt het geldende tarief bij het bedrag excl. BTW opgeteld.
     */
    private double getPriceIncludingVAT(double priceExcludingVAT) {
        return (1 + VAT / 100.0) * priceExcludingVAT;
    }

    /*
     * Het geldende tarief voor de BTW wordt toegepast om te bepalen hoe hoog de BTW is.
     */
    private double getVAT (double priceExcludingVAT) {
        return VAT / 100.0 * priceExcludingVAT;
    }

    /*
     * Omdat daarna nog BTW bij het bedrag excl. BTW opgeteld moet worden, wordt voor het totaalbedrag (excl. BTW)
     * van de factuur 'Subtotaal' getoond.
     */
    @Override
    protected String getSubject() {
        return "Subtotaal";
    }

    /*
     * Onderaan de factuur worden nog een aantal specifieke regels toegevoegd:
     *
     *      =                                             21% BTW   €   210,00   =
     *      =                                                       ---------- + =
     *      =                                             Totaal    € 1.210,00   =
     */
    @Override
    protected String getSpecificLastLines(double priceExcludingVAT) {
        double vat = getVAT (priceExcludingVAT);
        double priceIncludingVAT = getPriceIncludingVAT (priceExcludingVAT);

        String lines = InvoiceFormatter.getLine (true, "21%% BTW   € %8.2f  ", vat);
        lines += InvoiceFormatter.getLine (true, "---------- +");
        lines += InvoiceFormatter.getLine (true, "Totaal    € %8.2f  ", priceIncludingVAT);
        return lines;
    }
}
