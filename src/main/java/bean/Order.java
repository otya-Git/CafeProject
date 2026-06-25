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
	
	// 注文アイテムのリストを保持する変数を追加
	private List<Order_Item> orderItems;
	
	public Order() {}
	
	// 合計金額を注文アイテムから自動計算するゲッター
	public Integer getTotal_amount() {
		// アイテムが空っぽなら合計金額は 0 円
		if (this.orderItems == null || this.orderItems.isEmpty()) {
			return 0;
		}
		
		int total = 0;
		for (Order_Item item : this.orderItems) {
			// 各アイテムの単価と個数が存在する場合のみ計算（nullチェック）
			if (item.getPrice() != null && item.getQuantity() != null) {
				total += item.getPrice() * item.getQuantity();
			}
		}
		return total;
	}
	
	// 注文アイテムリストのゲッター・セッター
	public List<Order_Item> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<Order_Item> orderItems) {
		this.orderItems = orderItems;
	}

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
	
}
