package service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

import dto.CashBook;
import dto.Order;
import dto.Owner;
import dto.Product;

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
    	int nowInterest = (int)(owner.getDept() * CashBook.INTEREST);
        return nowInterest;
    }

    // 물건 구매
    public static void Buying(List<Order> orderList) {
    	int sum = 0; //구매액 총합
    	for(int i=0 ; i<orderList.size(); i++) {
    		int price = orderList.get(i).getCount() * orderList.get(i).getProduct().getBuyingPrice();
    		sum += price;
    	}
    	if(owner.getMoney() < sum) {
    		System.out.println("잔고가 부족하여 구매를 실패하셨습니다.");
    		return; // 메인메뉴로
    	}

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

    // 장사 개시
    public static void openShop() {

        // Customer 객체 생성 시 생성자를 통해 주문 목록이 랜덤으로 생성됩니다.
        // getOrderList() 함수를 통해서 주문 목록(List<Order>)을 불러와 사용할 수 있습니다.

        Random r = new Random();
        int day = Service.getOwner().getDay(); // 회차
        List<Product> list = Service.getProductList(); // product 타입 선언되어야함
        int sum = 0;
        int i = 1; // 메뉴번호를 업데이트

        for (Product product : list) {
            int guest = r.nextInt(10); // 손님수
            sum+= product.getSellingPrice() * guest; // 총매출
            int temp = product.getSellingPrice()*guest;//제품마다의 매출
            int myMoney = product.getRevenue()*guest; // 나의 잔액을 업데이트해주기위한 변수
            Service.getOwner().addMoney(myMoney); // 업데이트
            int orgin = Service.getOwner().getKey(product); // 원래 있던 재고
            Service.getOwner().setStock(product,(orgin-guest)); //재고 업데이트(손님수만큼 재고수를 줄여줌)
            System.out.printf("%d. %s %d개 x %dkh = %5dkh\n",i,product.getName(),guest,product.getSellingPrice(),temp);
            i++; // 메뉴 번호 업데이트
        }

        int money = Service.getOwner().getMoney(); // 잔액 계속 업데이트

        // 가계부 업데이트
        CashBook cashBook = owner.getTodayCashBook(); // 오늘자 가계부 받아오기
//        cashBook.getTodayOrderList().addAll(/*오늘 처리한 주문 목록*/); // 오늘 판매한 목록 cashBook.todayOrderList에 추가
//        cashBook.setIncome(/*총 수익*/); // 판매 금액 cashBook.income에 추가

        Service.nextDay(); // 임대료 지불 후 하루 종료
    }

    /**
     * 하루 종료
     */
    public static void nextDay() {
        owner.setMoney(owner.getMoney() - CashBook.RENT); // 오늘자 임대료 납부
        owner.setDept(owner.getDept() + Service.TodayDept()); // 오늘자 대출이자 누적
        owner.setDay(owner.getDay() + 1); // 날짜 업데이트
        owner.getCashBookList().add(new CashBook()); // 다음날 가계부 생성
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
    
    public static void lottoService() {
    	if(owner.getMoney() <= 0 ){ 
    		System.out.println("잔액 부족");
    		return;
    	}else {
    		
    		int[] arr = new int[1];
    		int sum =0;
    		int num = (int)(Math.random() * 100 + 0);
    		
    		owner.setMoney(owner.getMoney()-5000);  // 복권 금액
    		
    		for (int i = 0; i<arr.length; i++) {
    			arr[i] = (int)(Math.random() * 5 + 1);
    			if(arr[i]>3) {
    				
    				sum = owner.getMoney() + num;
    				owner.setMoney(sum);
    				System.out.println("당첨입니다! : " + num + "원");
    				System.out.println();
    				
    			}else {
    				sum=owner.getMoney();
    				System.out.println("어머나! 꽝!");
    			}
    			for (int j = 0; j < i; j++) {
    				if (arr[j] == arr[i]) {
    					i--;
    					break;
    				}
    			}
    			
    		}
    		System.out.println("현재 잔액 : " + sum);
    	}
    	
		
		
    	
    }
    
    
    
}
