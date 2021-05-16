package invoice;

import java.util.ArrayList;

public class Main {

    /*
     * Er worden 2 facturen getoond: 1 voor een kleine ondernemer en 1 voor een normale ondernemer.
     */
    public static void main(String[] args) {
        Customer customer = new Customer("Testklant", "Teststraat 15", "2282 CD", "DELFT");
        ArrayList <InvoiceLine> lines = new ArrayList<> ();
        lines.add (new InvoiceLine(2, new Product("Testproduct 1", 22.0)));
        lines.add (new InvoiceLine(1000, new Product("Testproduct 2", 0.018)));
        new Invoice(new BusinessSmall()).printInvoice (customer, lines);
        System.out.println ();
        new Invoice (new BusinessNormal()).printInvoice (customer, lines);
    }
}
