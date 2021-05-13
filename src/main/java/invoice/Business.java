package invoice;

public abstract class Business {

    protected abstract String getSpecificLastLines (double priceExcludingVAT);
    protected abstract String getSubject ();

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