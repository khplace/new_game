package service;

import dto.Order;
import dto.Owner;
import dto.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Service {

    private static Owner owner;
    private static List<Product> productList = new ArrayList<>(); // 물건 목록

    public static void fillProductList() {
        productList.add(new Product("아메리카노",150,200));
        productList.add(new Product("카페라뗴",100,200));
        productList.add(new Product("카푸치노",300,500));
        productList.add(new Product("베이글",250,200));
        productList.add(new Product("케이크",100,200));
        productList.add(new Product("샌드위치",50,250));
    }

    public static void createOwner(String name, String ceo) {
        owner = new Owner(name, ceo);
    }

    public static Owner getOwner() {
        return owner;
    }
    
    public static List<Product> getProductList() {
    	return productList;
    }

    // 이번 턴에 내야하는 이자 계산
    public static int TodayDept() {
    // 구현
    	
    	int nowInterest = (int)(owner.getDept() * Owner.INTEREST);
    			
        return nowInterest;
    }

    // 물건 구매
    public static void Buying(List<Order> orderList) {
    	int sum = 0; //구매액 총합
    	for(int i = 0 ; i<orderList.size(); i++) {
    		int price = orderList.get(i).getCount() * orderList.get(i).getProduct().getBuyingPrice();
    		sum +=price;
    	}
    	if(owner.getMoney()<sum) {
    		System.out.println("잔고가 부족하여 구매를 실패하셨습니다.");
    		return; // 메인메뉴로
    	}else {
    		// 물품 재고
        	Map<Product, Integer> stock = owner.getStock();
        	
        	for(int i = 0 ; i<orderList.size(); i++) {
        		int curStock;
        		if (stock.get(orderList.get(i).getProduct()) == null) 
        			curStock = 0;
        		else
        			curStock = stock.get(orderList.get(i).getProduct());
        		stock.put(orderList.get(i).getProduct(), curStock + orderList.get(i).getCount());
        	}
        	
        	// 잔고
        	owner.setMoney(owner.getMoney()-sum);        
    	}
    }

    // 장사 개시
    public static void openShop() {

    }

    // 이자 상환
    public static void repayment(int input) {
    	owner.setDept(owner.getDept() - input);
    	owner.setMoney(owner.getMoney() - input);
    }
    
    // 순수익 (매출액 - 매입원가)
    // 매출액 : sellproduct 클래스
    // 매입 원가 : 
    public static void totalrevenue() {
    	
    }
    
    // 프로그램 종료
    public static void exit(int status) {
    	  Runtime.getRuntime().exit(status);
    }


    /**
     * 콘솔 화면 새로고침
     */
    public static void clearScreen() {
        try {
            final String os = System.getProperty("os.name");
            if(os.contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        }catch (Exception e) {
            // TODO: handle exception
        }
    }
}
