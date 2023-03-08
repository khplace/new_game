package dto;

import service.Service;

import java.io.Serializable;

public class Order implements Serializable {

    private Product product; // 제품
    private int count; // 주문 갯수
    
    public Order(Product product, int count) {
    	this.product = product;
    	this.count = count;
    }

	public Order(int productIndex, int count) {
		this.product = Service.getProductList().get(productIndex);
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

	public int calculateSellingPrice() {
		return product.getSellingPrice() * count;
	}

	public int calculateBuyingPrice() {
		return product.getBuyingPrice() * count;
	}
}
