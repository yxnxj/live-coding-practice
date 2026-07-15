class Order {
	String orderId;
	String customerName;
	long amount;
	String status;

	Order(String orderId, String customerName, long amount, String status) {
		this.orderId = orderId;
		this.customerName = customerName;
		this.amount = amount;
		this.status = status;
	}
}