package invoice;

/*
 * Een kleine ondernemer hoeft geen BTW aan zijn klanten in rekening te brengen.
 */
public class BusinessSmall extends Business {

    /*
     * Omdat het bedrag excl. BTW ook het eindbedrag van de factuur is, wordt voor het totaalbedrag (excl. BTW)
     * van de factuur 'Totaal' getoond.
     */
    @Override
    protected String getSubject () {
        return "Totaal";
    }

    /*
     * De factuur wordt afgesloten met een melding dat kleine ondernemers geen BTW hoeven te heffen.
     */
    @Override
    protected String getSpecificLastLines (double priceExcludingVAT) {
        String lines = InvoiceFormatter.getEmptyLine ();
        return lines + InvoiceFormatter.getLine (true, "Factuur is vrijgesteld van OB o.g.v. artikel 25 Wet OB  ");
    }
}