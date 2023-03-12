package view;

import java.util.InputMismatchException;
import java.util.Scanner;
import service.Service;

public class LottoView {

    Scanner sc = new Scanner(System.in);

    public void displayLotto() {

        Service.clearScreen();
	            System.out.println("· ------------------- · ◈ · ------------------- ·\n");
	            System.out.println("                    복 권 구 매 \n");
	            System.out.println("· ------------------- · ◈ · ------------------- ·\n");
	            System.out.println( );
	            System.out.println("               복권 가격은 1회 10원 입니다");
	            System.out.println("         복권을 구매하시려면 엔터키를 입력해주세요!!!!");
	            sc.nextLine();
	            System.out.println();
	            while(true) {
	            	try {
			            System.out.println("· ------------------- · ◈ · ------------------- ·\n");
			            Service.lottoService();
				            System.out.println();
				            System.out.println("· ------------------- · ◈ · ------------------- ·\n");
				            System.out.println("1. 한번 더 하기!");
				            System.out.println("2. 돌아가기");
				            System.out.print("입력 : ");
				            int select = sc.nextInt();
				            sc.nextLine();
				        if(select == 1) {
				        	System.out.println();
				        	continue;
				        }
				        else if(select == 2) {
			                System.out.println("메인메뉴로 돌아갑니다.");
			                break;
			            }
				        else System.out.println("유요하지 않은 입력입니다.");
				        
	            	} catch (InputMismatchException e) { // 숫자 입력이 아닌 경우
	            		System.out.println("  1또는 2를 입력해 주세요\n");
	            		sc.nextLine();
	    	            System.out.println("· ------------------- · ◈ · ------------------- ·\n");
	    	            System.out.println( );
	    	            System.out.println("               복권 가격은 1회 10원 입니다");
	    	            System.out.println("         복권을 구매하시려면 엔터키를 입력해주세요!!!!");
	    	            sc.nextLine();

	            	} catch (Exception e) {
	            		Service.clearScreen(); // 화면 초기화
	            		System.out.println("알 수 없는 예외 발생\n");
	            		e.printStackTrace();
	            		return;
	            	}
	        }
    }
}