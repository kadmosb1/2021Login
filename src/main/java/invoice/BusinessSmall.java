package invoice;
/*
 * Een kleine ondernemer hoeft geen BTW aan zijn klanten in rekening te brengen.
 */

public class BusinessSmall extends Business {

    @Override
    protected String getSubject () {
        return "Totaal";
    }

    protected String getSpecificLastLines (double priceExcludingVAT) {
        String lines = InvoiceFormatter.getEmptyLine ();
        return lines + InvoiceFormatter.getLine (true, "Factuur is vrijgesteld van OB o.g.v. artikel 25 Wet OB  ");
    }
}