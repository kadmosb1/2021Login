package invoice;

/*
 * Factuurregel. Voor elke regel in een factuur wordt in deze Class bijgehouden hoeveel eenheden van een product
 * er verkocht zijn.
 */
public class InvoiceLine {

    private int numberOfProducts;
    private Product product;

    public InvoiceLine (int numberOfProducts, Product product) {
        this.numberOfProducts = numberOfProducts;
        this.product = product;
    }

    public int getNumberOfProducts() {
        return numberOfProducts;
    }

    public Product getProduct() {
        return product;
    }
}