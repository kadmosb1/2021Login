package Invoice;

import java.util.ArrayList;

public class Invoice {

    private static int nextInvoiceNumber = 0;
    private Business business;

    public Invoice (Business business) {
        this.business = business;
    }

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
        System.out.print (getInvoiceString (customer, lines));
    }
}
