package view;

import dto.Product;
import service.Service;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class SellProductView {

    Scanner sc = new Scanner(System.in);
    
    
    
    public void displaySellProductMenu() {
    	
    	Service.clearScreen(); // 화면 초기화
    	
        System.out.println("장사");
   
        Random r = new Random();

        int day = service.Service.getOwner().getDay(); // 회차
       
        int loan =  service.Service.getOwner().getDept();// 대출금
        int totalLoan = loan -(service.Service.TodayDept());//- service.Service.getOrder().getBuyingPrice();
        int interest = service.Service.TodayDept(); // 대출금 이자
        List<Product> list = service.Service.getList(); // product 타입 선언되어야함
        int sum = 0;
        int i = 1; // 메뉴번호를 업데이트
        for (Product product : list) {
        	int guest = r.nextInt(50); // 손님수
        	sum+= product.getSellingPrice()*guest; // 총매출
        	int temp = product.getSellingPrice()*guest;//제품마다의 매출
        	int myMoney = product.getRevenue()*guest; // 나의 잔액을 업데이트해주기위한 변수
        	service.Service.getOwner().addMoney(myMoney); // 업데이트
        	int orgin = service.Service.getOwner().getKey(product); // 원래 있던 재고 
        	service.Service.getOwner().setStock(product,(orgin-guest)); //재고 업데이트(손님수만큼 재고수를 줄여줌)
        	 System.out.printf("%d. %s %d개 x %dkh = %5dkh\n",i,product.getName(),guest,product.getSellingPrice(),temp); 
        	 i++; // 메뉴 번호 업데이트
		}
   
        int money = service.Service.getOwner().getMoney(); // 잔액 계속 업데이트
       

        
        System.out.println("=========== 일 일 결 산===========\n");
        System.out.println(day + "일차 매출");
       
       
        System.out.println();

        System.out.println(day + "일차 비용");
        System.out.printf("1.대출금 이자 : %d\n", interest);
        System.out.printf("2.판매금액 : %d\n", sum);
        System.out.println();
        System.out.printf("총 매출              +%dkh\n", sum);
        System.out.printf("총 지출              -%dkh\n", totalLoan);
        System.out.printf("잔액                %dkh\n", money); //보유 금액
        System.out.println();
        
        System.out.println("다음 날로 넘어가려면 엔터를 눌러주세요");
        sc.nextLine();
    }
}
