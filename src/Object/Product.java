package Object;

public class Product {
	private int productId;
	private int productPrice;
	
	public Product(){}
	
	public Product(int productId, int productPrice){
		this.productId = productId;
		this.productPrice = productPrice;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}
	
}
