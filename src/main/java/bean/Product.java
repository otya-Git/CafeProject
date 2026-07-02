package bean;

import java.io.Serializable;

public class Product implements Serializable {

    private int productId;
    private String productName;
    private int costPrice;
    private int price;
    private String description;
    private String categoryName;


    // コンストラクタ
    public Product() {
    }


    // 商品ID
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }


    // 商品名
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }


    // 原価
    public int getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(int costPrice) {
        this.costPrice = costPrice;
    }


    // 販売価格
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    // 商品説明
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    // カテゴリ名
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

}