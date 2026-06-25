package bean;

import java.io.Serializable;

public class Product implements Serializable {

    private int product_id;
    private String product_name;
    private int stock_quantity;
    private int cost_price;
    private int price;
    private String description;
    private int category_id;

    public Product() {
    }

    public int getproduct_id() {
        return product_id;
    }

    public void setproduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getproduct_name() {
        return product_name;
    }

    public void setProduct_Name(String product_name) {
        this.product_name = product_name;
    }

    public int getstock_quantity() {
        return stock_quantity;
    }

    public void setstock_quantity(int stock_quantity) {
        this.stock_quantity = stock_quantity;
    }

    public int getcost_price() {
        return cost_price;
    }

    public void setcost_price(int cost_price) {
        this.cost_price = cost_price;
    }

    public int getprice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getdescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getcategory_id() {
        return category_id;
    }

    public void setCategoryId(int category_id) {
        this.category_id = category_id;
    }
}