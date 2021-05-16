package invoice;

import java.util.ArrayList;

/*
 * Factuur.
 */
public class Invoice {

    private static int nextInvoiceNumber = 0;
    private Business business;

    // Voor het opstellen van een factuur moet bekend zijn of het om een kleine of een normale ondernemer gaat.
    public Invoice (Business business) {
        this.business = business;
    }

    // Factuurnummers zijn deelbaar door 13.
    private int getNextInvoiceNumber () {
        nextInvoiceNumber += 13;
        return nextInvoiceNumber;
    }

    protected String getInvoiceString (Customer customer, ArrayList<InvoiceLine> lines) {

        StringBuilder invoice = new StringBuilder ();

        invoice.append (InvoiceFormatter.getHeader ());
        invoice.append (InvoiceFormatter.getLine(false,"Invoice.Invoice Number: %d", getNextInvoiceNumber ()));
        invoice.append (InvoiceFormatter.getEmptyLine ());
        invoice.append (customer);
        invoice.append (InvoiceFormatter.getEmptyLine ());

        double totalPrice = 0.0;

        for (InvoiceLine line : lines) {
            Product product = line.getProduct ();
            int numberOfProducts = line.getNumberOfProducts ();
            double linePrice = product.getPrice () * numberOfProducts;
            totalPrice += linePrice;
            invoice.append (InvoiceFormatter.getLine (false, "%4d %-50s € %8.2f", numberOfProducts, product.getName (), linePrice));
        }

        invoice.append (business.getLastLinesOfInvoice (totalPrice));
        invoice.append (InvoiceFormatter.getFooter());

        return invoice.toString ();
    }


    public void printInvoice (Customer customer, ArrayList<InvoiceLine> lines) {

        // Een factuur mag alleen worden opgesteld door een ingelogde gebruiker die daar rechten voor heeft.
        if (Login.isAuthenticated () && Login.isAuthorized ("invoice")) {
            System.out.print (getInvoiceString(customer, lines));
        }
        else {
            Logging.printLog ("Invoice:printInvoice - User is not allowed to print invoice");
            System.out.print ("User is not allowed to print invoice");
        }
    }
}
