package dto;

import java.util.List;
import java.util.Map;

public class Owner {
    String name; // 사장 이름
    String ceo; // 가게 이름
    int money; // 현재 소지금액
    int dept; // 남은 대출금
    int day; // 현재 진행날짜
    int totalRevenue; // 순수익

    public static final double INTEREST = 0.1;
    
    Map<Product, Integer> stock;
    
    public Owner(String name, String ceo) {
        this.name = name;
        this.ceo = ceo;
        this.money = 5000;
        this.dept = -5000;
        day = 1;
        totalRevenue = 0;
    }
    public int getTotal(){
        return this.totalRevenue;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCeo() {
        return this.ceo;
    }

    public void setCeo(String ceo) {
        this.ceo = ceo;
    }

    public int getMoney() {
        return this.money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
    public void addMoney(int m){
        this.money+= m;
    }

    public int getDept() {
        return this.dept;
    }

    public void setDept(int dept) {
        this.dept = dept;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public Map<Product, Integer> getStock() {
        return stock;
    }

    public void setStock(Map<Product, Integer> stock) {
        this.stock = stock;
    }
    public int getKey(Product product) {
    	return stock.get(product);
    }
	public void setStock(Product product, int i) {
		// TODO Auto-generated method stub
		stock.put(product, i);
		
	}
}
