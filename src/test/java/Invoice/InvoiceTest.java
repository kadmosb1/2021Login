package Invoice;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class InvoiceTest {

    private static Customer customer;
    private static ArrayList<InvoiceLine> lines;
    private static Invoice smallInvoice;
    private static Invoice normalInvoice;

    private String getExpectedSmallInvoice () {

        return "========================================================================\r\n" +
               "= Invoice.Invoice Number: 13                                           =\r\n" +
               "=                                                                      =\r\n" +
               "= Testklant                                                            =\r\n" +
               "= Teststraat 15                                                        =\r\n" +
               "= 2282 CD  DELFT                                                       =\r\n" +
               "=                                                                      =\r\n" +
               "=    2 Testproduct 1                                      €    44,00   =\r\n" +
               "= 1000 Testproduct 2                                      €    18,00   =\r\n" +
               "=                                                         ---------- + =\r\n" +
               "=                                               Totaal    €    62,00   =\r\n" +
               "=                                                                      =\r\n" +
               "=             Factuur is vrijgesteld van OB o.g.v. artikel 25 Wet OB   =\r\n" +
               "========================================================================\r\n";
    }

    private String getExpectedNormalInvoice () {

        return "========================================================================\r\n" +
               "= Invoice.Invoice Number: 26                                           =\r\n" +
               "=                                                                      =\r\n" +
               "= Testklant                                                            =\r\n" +
               "= Teststraat 15                                                        =\r\n" +
               "= 2282 CD  DELFT                                                       =\r\n" +
               "=                                                                      =\r\n" +
               "=    2 Testproduct 1                                      €    44,00   =\r\n" +
               "= 1000 Testproduct 2                                      €    18,00   =\r\n" +
               "=                                                         ---------- + =\r\n" +
               "=                                               Subtotaal €    62,00   =\r\n" +
               "=                                               21% BTW   €    13,02   =\r\n" +
               "=                                                         ---------- + =\r\n" +
               "=                                               Totaal    €    75,02   =\r\n" +
               "========================================================================\r\n";
    }

    @BeforeAll
    public static void init () {
        customer = new Customer("Testklant", "Teststraat 15", "2282 CD", "DELFT");
        lines = new ArrayList<> ();
        lines.add (new InvoiceLine(2, new Product("Testproduct 1", 22.0)));
        lines.add (new InvoiceLine(1000, new Product("Testproduct 2", 0.018)));
        smallInvoice = new Invoice (new BusinessSmall ());
        normalInvoice = new Invoice (new BusinessNormal ());
    }

    @Test
    void getInvoiceString() {
        String expectedInvoice = getExpectedSmallInvoice ();
        String actualInvoice = smallInvoice.getInvoiceString (customer, lines);
        assertEquals (expectedInvoice, actualInvoice);

        expectedInvoice = getExpectedNormalInvoice ();
        actualInvoice = normalInvoice.getInvoiceString (customer, lines);
        assertEquals (expectedInvoice, actualInvoice);
    }
}