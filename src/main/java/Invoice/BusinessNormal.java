package Invoice;

import Invoice.Business;

/*
 * Een normale ondernemer is verplicht om BTW aan zijn klanten door te rekenen.
 */
public class BusinessNormal extends Business {

    private static final int VAT = 21;

    private double getPriceIncludingVAT(double priceExcludingVAT) {
        return (1 + VAT / 100.0) * priceExcludingVAT;
    }

    private double getVAT (double priceExcludingVAT) {
        return VAT / 100.0 * priceExcludingVAT;
    }

    @Override
    protected String getSubject() {
        return "Subtotaal";
    }

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
