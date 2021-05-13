package invoice;

import java.util.ArrayList;

public class Main {

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
