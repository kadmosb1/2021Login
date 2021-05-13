package Invoice;

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