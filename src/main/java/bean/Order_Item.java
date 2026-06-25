package bean;

import java.io.Serializable;

public class Order_Item implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String order_item_id;
	private String order_id;
	private String product_id;
	private Integer quantity;
	private Integer price;
	
	public Order_Item() {}
	
	public String getOrder_item_id() {
		return order_item_id;
	}
	
	public String getOrder_id() {
		return order_id;
	}
	
	public String getProduct_id() {
		return product_id;
	}
	
	public Integer getQuantity() {
		return quantity;
	}
	
	public Integer getPrice() {
		return price;
	}

	public void setOrder_item_id(String order_item_id) {
		this.order_item_id = order_item_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}
	
}