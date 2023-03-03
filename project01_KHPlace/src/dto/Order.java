package dto;

public class Order {

    private Product product; // 제품
    private int count; // 주문 갯수
    
    public Order(Product product, int count) {
    	this.product = product;
    	this.count = count;
    }

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
    
    
}
