package bean;

import java.io.Serializable;

public class Product implements Serializable {

<<<<<<< HEAD
    private int product_id;
    private String product_name;
    private int cost_price;
=======
    private int productId;
    private String productName;
    private int costPrice;
>>>>>>> branch 'master' of https://github.com/otya-Git/CafeProject.git
    private int price;
    private String description;
<<<<<<< HEAD
    private String category_name;
=======
    private String categoryName;
>>>>>>> branch 'master' of https://github.com/otya-Git/CafeProject.git


    // コンストラクタ
    public Product() {
    }

<<<<<<< HEAD
    public int getProductId() {
        return product_id;
=======

    // 商品ID
    public int getProductId() {
        return productId;
>>>>>>> branch 'master' of https://github.com/otya-Git/CafeProject.git
    }

<<<<<<< HEAD
    public void setProductId(int product_id) {
        this.product_id = product_id;
=======
    public void setProductId(int productId) {
        this.productId = productId;
>>>>>>> branch 'master' of https://github.com/otya-Git/CafeProject.git
    }

<<<<<<< HEAD
    public String getProductName() {
        return product_name;
=======

    // 商品名
    public String getProductName() {
        return productName;
>>>>>>> branch 'master' of https://github.com/otya-Git/CafeProject.git
    }

<<<<<<< HEAD
    public void setProductName(String product_name) {
        this.product_name = product_name;
=======
    public void setProductName(String productName) {
        this.productName = productName;
>>>>>>> branch 'master' of https://github.com/otya-Git/CafeProject.git
    }

<<<<<<< HEAD
    public int getCostPrice() {
        return cost_price;
    }
=======

    // 原価
    public int getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(int costPrice) {
        this.costPrice = costPrice;
    }

>>>>>>> branch 'master' of https://github.com/otya-Git/CafeProject.git

<<<<<<< HEAD
    public void setCostPrice(int cost_price) {
        this.cost_price = cost_price;
    }

=======
    // 販売価格
>>>>>>> branch 'master' of https://github.com/otya-Git/CafeProject.git
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

<<<<<<< HEAD
=======

    // 商品説明
>>>>>>> branch 'master' of https://github.com/otya-Git/CafeProject.git
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

<<<<<<< HEAD
    public String getCategoryName() {
        return category_name;
=======

    // カテゴリ名
    public String getCategoryName() {
        return categoryName;
>>>>>>> branch 'master' of https://github.com/otya-Git/CafeProject.git
    }

<<<<<<< HEAD
    public void setCategoryName(String category_name) {
        this.category_name = category_name;
=======
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
>>>>>>> branch 'master' of https://github.com/otya-Git/CafeProject.git
    }

}