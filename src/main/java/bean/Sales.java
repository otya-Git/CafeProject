package bean;

import java.time.LocalDateTime;

public class Sales implements java.io.Serializable {
    private int sales_id;
    private long order_id;       
    private int product_id;
    private LocalDateTime created_at; 
    private int total_amount;     
    private int customer_count; 
    
    public int getSales_id() {
        return sales_id;
    }
    
    public void setSales_id(int sales_id) {
        this.sales_id = sales_id;
    }
    
    public long getOrder_id() {
        return order_id;
    }
    
    public void setOrder_id(long order_id) {
        this.order_id = order_id;
    }
    
    public int getProduct_id() {
        return product_id;
    }
    
    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }
    
    public LocalDateTime getCreated_at() {
        return created_at;
    }
    
    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }
    
    public int getTotal_amount() {
        return total_amount;
    }
    
    public void setTotal_amount(int total_amount) {
        this.total_amount = total_amount;
    }
    
    public int getCustomer_count() {
        return customer_count;
    }
    
    public void setCustomer_count(int customer_count) {
        this.customer_count = customer_count;
    }
}