package Object;

public class Order {
	
	private boolean orderProcess;
	private int currentOrderNumber;
	private Product orderProduct;
	private User orderUser;
	private static int orderNumber=1;
	
	public Order(Product orderProduct, User orderUser)
	{
		this.orderProcess=false;
		this.currentOrderNumber = orderNumber++;
		this.orderProduct = orderProduct;
		this.orderUser = orderUser;
	}
	
	//주문번호(int) 유저아이디(string) 유저주소(string) 상품번호(int) 상품가격(int) + 배송상태(boolean)
	public Order(int currentOrderNumber, String userId, String userAddress, int productId, int productPrice, boolean order){
		this.currentOrderNumber = currentOrderNumber;
		this.orderProcess = order;
		this.orderProduct = new Product(productId, productPrice);
		this.orderUser = new User(userId, userAddress);
	}


	public Product getOrderProduct() {
		return orderProduct;
	}
	public void setOrderProduct(Product orderProduct) {
		this.orderProduct = orderProduct;
	}
	public User getOrderUser() {
		return orderUser;
	}
	public void setOrderUser(User orderUser) {
		this.orderUser = orderUser;
	}
	public boolean isOrderProcess() {
		return orderProcess;
	}
	public void setOrderProcess(boolean orderProcess) {
		this.orderProcess = orderProcess;
	}
	public int getCurrentOrderNumber() {
		return currentOrderNumber;
	}
	public void setCurrentOrderNumber(int currentOrderNumber) {
		this.currentOrderNumber = currentOrderNumber;
	}

	@Override
	public String toString() {
		return "Order [orderProcess=" + orderProcess + ", currentOrderNumber=" + currentOrderNumber + ", orderProduct.productId="
				+ orderProduct.getProductId() +  ", orderProduct.productprice="+ orderProduct.getProductPrice() + ", orderUser=" + orderUser.getUserId() +
				", orderUser.address=" + orderUser.getUserAddress() + "]";
	}
	
}
