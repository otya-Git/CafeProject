package bean;

import java.io.Serializable;

public class Order_Item implements Serializable {

    private static final long serialVersionUID = 1L;

    private String order_item_id;
    private String order_id;
    private int product_id;

    private String product_name;

    private Integer quantity;

    private Integer price;


    // 注文状態追加
    private String status;



    public Order_Item() {}



    public String getOrder_item_id() {
        return order_item_id;
    }

    public void setOrder_item_id(String order_item_id) {
        this.order_item_id = order_item_id;
    }



    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }



    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }



    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }



    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }



    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }



    // status追加

    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }

}