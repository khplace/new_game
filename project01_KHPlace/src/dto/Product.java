package dto;

public class Product {

    private String name; // 제품명
    private int buyingPrice; // 살 때 가격
    private int sellingPrice; // 팔 때 가격
    private int revenue;

    
    public Product(String name, int buyingPrice, int sellingPrice) {
        this.name = name;
        this.buyingPrice = buyingPrice;
        this.sellingPrice = sellingPrice;
    }

    public String getName() {
		return name;
	}
    public int getRevenue() {
    	return this.buyingPrice-this.sellingPrice;
    }

	public void setName(String name) {
		this.name = name;
	}

	public int getBuyingPrice() {
		return buyingPrice;
	}

	public void setBuyingPrice(int buyingPrice) {
		this.buyingPrice = buyingPrice;
	}

	public int getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(int sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	@Override
    public String toString() {
        return name + " / " + buyingPrice + " / " + sellingPrice;
    }

}
