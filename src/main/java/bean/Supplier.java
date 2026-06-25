package bean;

public class Supplier {
	
	private long supplierId;
	private String supplierName;
	private String phone;
	private String address;
	
	public Supplier() {
	}
	
	public long getSupplierId() {
		return supplierId;
	}
	
	public void getSupplierId(long supplierId) {
		this.supplierId = supplierId;
	}
	
	public String getSupplierName() {
		return supplierName;
	}
	
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
}