package invoice;

public class Product {

    private String name;
    private double price;

    public Product (String name, double price) {

        // Alleen als een gebruiker is ingelogd en toegang heeft tot producten, kan hij/zij een product aanmaken.
        if (Login.isAuthenticated () && Login.isAuthorized ("product")) {
            this.name = name;
            this.price = price;
        }
        else {
            this.name = "Unauthorized to create product data";
            this.price = 0.0;
            Logging.printLog ("Product:Product - User is not allowed to create product");
        }
    }

    public String getName() {
        return name;
    }

    public double getPrice() {

        // Als bekend is wie de gebruiker is en als hij toegang heeft tot producten, mag hij/zij de prijs opvragen.
        if (Login.isActive () && Login.isAuthorized ("product")) {
            return price;
        }
        else {
            Logging.printLog ("Product:getPrice - User is not allowed to request the price of a product");
            return -1.0;
        }
    }
}