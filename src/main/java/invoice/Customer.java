package invoice;

public class Customer {

    private String name;
    private String address;
    private String zipcode;
    private String city;

    public Customer (String name, String address, String zipcode, String city) {

        if (Login.isAuthenticated () && Login.isAuthorized ("customer")) {
            this.name = name;
            this.address = address;
            this.zipcode = zipcode;
            this.city = city;
        }
        else {
            Logging.printLog ("Customer:Customer - User is not authenticated or not authorized to create customer.");
        }
    }

    @Override
    public String toString() {

        if (Login.isActive () && Login.isAuthorized ("customer")) {
            String customer = "";
            customer += InvoiceFormatter.getLine(false, name);
            customer += InvoiceFormatter.getLine(false, address);
            customer += InvoiceFormatter.getLine(false, "%s  %s", zipcode, city);
            return customer;
        }
        else {
            Logging.printLog ("Customer:toString - User is not authorized to get data of customer.");
            return InvoiceFormatter.getLine (false, "unauthorized to get customer data.");
        }
    }
}