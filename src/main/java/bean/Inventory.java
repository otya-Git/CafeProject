package bean;

import java.sql.Date;
import java.sql.Timestamp;

public class Inventory {
	private long inventoryId;
	private long productId;
	private long supplierId;
	private double stockQuantity;
	private String unit;
	private double reorderPoint;
	private Date expiryDate;
	private Timestamp updatedAt;
	private String productName;
	private String supplierName;
	
	public Inventory() {
	}
	
	public long getInventoryId() {
		return inventoryId;
	}
	
	public void setInventoryId(long inventoryId) {
		this.inventoryId = inventoryId;
	}
	
	public long getProductId() {
	    return productId;
	}

	public void setProductId(long productId) {
	    this.productId = productId;
	}
	
	public long getSupplierId() {
		return supplierId;
	}
	
	public void setSupplierId(long supplierId) {
		this.supplierId = supplierId;
	}
	
	public double getStockQuantity() {
		return stockQuantity;
	}
	
	public void setStockQuantity(double stockQuantity) {
		this.stockQuantity = stockQuantity;
	}
	
	public String getUnit() {
		return unit;
	}
	
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	public double getReorderPoint() {
		return reorderPoint;
	}
	
	public void setReorderPoint(double reorderPoint) {
		this.reorderPoint = reorderPoint;
	}
	
	public Date getExpiryDate() {
		return expiryDate;
	}
	
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	public Timestamp getUpdatedAt() {
		return updatedAt;
	}
	
	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public String getProductName() {
	    return productName;
	}

	public void setProductName(String productName) {
	    this.productName = productName;
	}

	public String getSupplierName() {
	    return supplierName;
	}

	public void setSupplierName(String supplierName) {
	    this.supplierName = supplierName;
	}



}