package Invoice;

public class Customer {

    private String name;
    private String address;
    private String zipcode;
    private String city;

    public Customer (String name, String address, String zipcode, String city) {
        this.name = name;
        this.address = address;
        this.zipcode = zipcode;
        this.city = city;
    }

    @Override
    public String toString() {
        String customer = "";
        customer += InvoiceFormatter.getLine (false, name);
        customer += InvoiceFormatter.getLine (false, address);
        customer += InvoiceFormatter.getLine (false, "%s  %s",zipcode, city);
        return customer;
    }
}