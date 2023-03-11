package view;

import dto.Customer;
import dto.Order;
import dto.Owner;
import dto.Product;
import service.SellProductPlayingService;
import service.Service;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SellProductPlayingView {

    private SellProductPlayingService sellService = new SellProductPlayingService();

    public void sellProductPlayingView() {

        Scanner sc = new Scanner(System.in);
        Owner owner = Service.getOwner();

        // 재고가 없을때 장사시작중지
        if( owner.stockIsEmpty() ) {
            System.out.println("  재고가 부족합니다. 상품을 구입해주세요");
            System.out.println("  엔터를 눌러 메인메뉴로 돌아갑니다.");
            sc.nextLine();
            return;
        }

        Service.clearScreen(); // 화면 초기화
        System.out.println("· ------------------- · ◈ · ------------------- ·\n");
        System.out.printf("            %d 일 차   장 사 시 작\n", owner.getDay());
        System.out.println("================================================");

        int guestNum = (int)((Math.random() * 5) + 1) ; // 1 이상 5 미만의 랜덤한 손님 수 생성
        
        for(int i=0; i<guestNum; i++){ // 손님 1명 입장

            Customer customer = new Customer();
            List<Order> orderList = customer.getOrderList();

            // i번째 손님의 주문목록 전체출력
            for(int order=0; order<orderList.size(); order++) {
                System.out.printf("  @손님 %d이 %s %d개를 구매하려고 합니다.\n", i+1,
                        orderList.get(order).getProduct().getName(),        // 주문상품 이름
                        orderList.get(order).getCount());                   // 주문수량
            }
            while(true) {
	            System.out.println("· ------------------- · ◈ · ------------------- ·\n");
	            System.out.println("  1. 팔기");
	            System.out.println("  2. 울면서 보내기");
	            System.out.print("  입력 하세요 >> ");
	            int input = sc.nextInt();
	            System.out.println("· ------------------- · ◈ · ------------------- ·\n");
	            sc.nextLine();
	            
	            if (input == 1) { // 1. 팔기 선택 시
	                sellService.sellProductPlaying(orderList);
	                break;
	            } else if (input == 2) {  // 2. 울면서 보내기 선택 시
	                break;
	            } else { // 아무것도 입력하지 않은 경우, 다른 숫자를 입력한 경우, 숫자 외의 다른 것을 입력한 경우
	                System.out.println("  1이나 2를 입력해 주세요");
	                continue;
	            }
            }
        }

        int guestNumSum = owner.getGuestNumSum(); // 받은 손님 수 누적
        guestNumSum += guestNum;
        owner.setGuestNumSum(guestNumSum);

        Service.endDay(); // 임대료 지불 후 하루 종료
        new SellProductView().displaySellProductMenu();
        

    }
}