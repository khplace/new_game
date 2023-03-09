package dto;

import service.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Owner implements Serializable {
    private String name; // 사장 이름
    private String ceo; // 가게 이름
    private int level; // 난이도
    private int money; // 현재 소지금액
    private int dept; // 남은 대출금
    private int day; // 현재 진행날짜

    private Map<Product, Integer> stock = new HashMap<>(); // 현재 물량 재고
    private List<CashBook> cashBookList = new ArrayList<>(); // 일일 판매 결과 가계부
    
    public Owner(String name, String ceo) {
        this.name = name;
        this.ceo = ceo;
        this.level = 1;
        this.money = 9999;
        this.dept = 100;
        this.day = 1;

        // 재고량 모두 0으로 초기화
        List<Product> list = Service.getProductList();
        for(Product p : list) stock.put(p, 0);

        // 1일차 가계부 생성
        // 이후 판매 종료 시점에 다음날 가계부 생성
        cashBookList.add(new CashBook());
        cashBookList.add(new CashBook());
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

    public int getlevel() {
    	return this.level;
    }
    
    public void setlevel(int level) {
    	this.level = level;
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

    public int getProductStock(Product product) {
    	return stock.get(product);
    }

    public List<CashBook> getCashBookList() {
        return cashBookList;
    }

    public void setCashBookList(List<CashBook> cashBookList) {
        this.cashBookList = cashBookList;
    }

    public void setStock(Product product, int i) {
		stock.put(product, i);
	}

    public CashBook getTodayCashBook() {
        return cashBookList.get(day);
    }

    public void addStock(Product product, int count) {
        stock.put(product, stock.get(product) + count);
    }

    public void addStock(int productIndex, int count) {
        Product p = Service.getProductList().get(productIndex);
        stock.put(p, stock.get(p) + count);
    }

    public int totalIncome() {
        int totIncome = 0;
        for(CashBook c : cashBookList) {
            totIncome += c.getIncome();
        }
        return totIncome;
    }

    public int totalOutcome() {
        int Outcome = 0;
        for(CashBook c : cashBookList) {
            Outcome += c.getOutcome();
        }
        return Outcome;
    }
}
