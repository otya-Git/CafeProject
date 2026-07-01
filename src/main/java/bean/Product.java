package bean;

import java.io.Serializable;

public class Product implements Serializable {

    private int product_id;
    private String product_name;
    private int cost_price;
    private int price;
    private String description;
    private String category_name;

    public Product() {
    }

    public int getProductId() {
        return product_id;
    }

    public void setProductId(int product_id) {
        this.product_id = product_id;
    }

    public String getProductName() {
        return product_name;
    }

    public void setProductName(String product_name) {
        this.product_name = product_name;
    }

    public int getCostPrice() {
        return cost_price;
    }

    public void setCostPrice(int cost_price) {
        this.cost_price = cost_price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoryName() {
        return category_name;
    }

    public void setCategoryName(String category_name) {
        this.category_name = category_name;
    }
}