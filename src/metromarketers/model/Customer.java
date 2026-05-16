package metromarketers.model;

public abstract class Customer {
    private String customerId;
    private String name;
    private String address;
    private String phone;
    private String email;

    public Customer(String customerId, String name, String address, String phone, String email) {
        this.customerId = customerId;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public String getCustomerId() { return customerId; }
    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    
    public abstract String getCustomerType();
}