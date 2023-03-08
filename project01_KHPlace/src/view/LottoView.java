package view;

import java.util.List;
import java.util.Scanner;

import dto.Product;
import service.Service;

public class LottoView {

    Scanner sc = new Scanner(System.in);

    public void displayLotto() {

        Service.clearScreen();

        while(true) {
            System.out.println("· ------------------- · ◈ · ------------------- ·\n");
            System.out.println("                    복 권 구 매 \n");
            System.out.println("================================================");
            System.out.println( );
            System.out.println("복권 가격 1회 10원 입니다");
            System.out.println("복권을 구매하시려면 엔터키를 입력해주세요!!!!");
            sc.nextLine();
            
            Service.lottoService();
            
            System.out.println();
            System.out.println("================================================");

            System.out.println("1. 한번 더 하기!");
            System.out.println("2. 돌아가기");
            System.out.print("입력 : ");
            int select = sc.nextInt();
            if(select == 2) {
                System.out.println("메인메뉴로 돌아갑니다.");
                break;
            }
            System.out.println();
        }
    }
}