package bean;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class Order implements Serializable {

    private static final long serialVersionUID = 1L;


    private String order_id;

    private String table_id;

    private String status;

    private String payment_method;

    private LocalDateTime ordered_at;


    // DBの合計金額
    private int total_amount;



    // 注文明細
    private List<Order_Item> orderItems;



    public Order() {}



    // ==========================
    // getter
    // ==========================


    public String getOrder_id() {
        return order_id;
    }


    public String getTable_id() {
        return table_id;
    }


    public String getStatus() {
        return status;
    }


    public String getPayment_method() {
        return payment_method;
    }


    public LocalDateTime getOrdered_at() {
        return ordered_at;
    }


    public int getTotal_amount() {
        return total_amount;
    }



    public List<Order_Item> getOrderItems() {
        return orderItems;
    }




    // ==========================
    // setter
    // ==========================


    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }



    public void setTable_id(String table_id) {
        this.table_id = table_id;
    }



    public void setStatus(String status) {
        this.status = status;
    }



    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }



    public void setOrdered_at(LocalDateTime ordered_at) {
        this.ordered_at = ordered_at;
    }



    public void setTotal_amount(int total_amount) {
        this.total_amount = total_amount;
    }



    public void setOrderItems(List<Order_Item> orderItems) {
        this.orderItems = orderItems;
    }


}