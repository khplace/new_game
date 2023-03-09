package view;

import java.util.List;
import java.util.Scanner;

import dto.Customer;
import dto.Order;
import service.Service;

public class SellProductPlayingView {


    public void sellProductPlayingView() {

        Scanner sc = new Scanner(System.in);
        Customer customer = new Customer();
        List<Order> orderList = customer.getOrderList();

        int day = Service.getOwner().getDay(); // 회차
        int totalSellProductSum = 0;
        int menuNum = 1; // 메뉴번호를 업데이트


        Service.clearScreen(); // 화면 초기화
        System.out.println("· ------------------- · ◈ · ------------------- ·\n");
        System.out.printf("            %d 일 차   장 사 시 작\n", day);
        System.out.println("================================================");


        int guestNum = (int)(Math.random() * 7); // 0 이상 7 미만의 랜덤한 손님 수 생성

        for(int i=1; i<guestNum; i++){ // 손님 1명 입장

            for(int order=0; order<orderList.size(); order++) { // 손님의 주문 n개
                System.out.printf("손님 %d이 %s %d개를 구매하려고 합니다.\n", i, orderList.get(order).getProduct().getName(), orderList.get(order).getCount());
            }
            System.out.println("1. 팔기");
            System.out.println("2. 울면서 보내기");
            System.out.print("입력 하세요 >> ");
            int input = sc.nextInt();
            sc.nextLine();

            if (input == 1) Service.sellProductPlaying(); // 1. 팔기 선택 시
            else if (input == 2) continue; // 2. 울면서 보내기 선택 시
            else System.out.println("1이나 2를 입력해 주세요");
            // 아무것도 입력하지 않은 경우, 다른 숫자를 입력한 경우, 숫자 외의 다른 것을 입력한 경우

        }

        new SellProductView().displaySellProductMenu();

    }
}