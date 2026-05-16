package metromarketers.model;

public class Product {
    private String productId;
    private String productName;
    private String category;
    private double price;
    private int stock;

    public Product(String id, String name, String category, double price, int stock) {
        this.productId = id;
        this.productName = name;
        this.category = category;
        this.price = price;
        this.stock = stock;
    }

    public String getProductId() { return productId; }
    public String getProductName() { return productName; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }
    
    public void setStock(int stock) { this.stock = stock; }
    public void setPrice(double price) { this.price = price; }
}