package metromarketers.model;

public class Transaction {
    private String transactionId;
    private double totalAmount;
    private String paymentMethod;
    private Customer customer;

    public Transaction(String id, double amount, String paymentMethod, Customer customer) {
        this.transactionId = id;
        this.totalAmount = amount;
        this.paymentMethod = paymentMethod;
        this.customer = customer;
    }

    public String getTransactionId() { return transactionId; }
    public double getTotalAmount() { return totalAmount; }
    public String getPaymentMethod() { return paymentMethod; }
    public Customer getCustomer() { return customer; }
}