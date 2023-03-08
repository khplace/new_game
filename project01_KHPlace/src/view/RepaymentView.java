package view;

import service.Service;

import java.util.Scanner;

import dto.Owner;

public class RepaymentView {

    Scanner sc = new Scanner(System.in);

    public void displayRepaymentMenu() {

        Owner owner = Service.getOwner();
  
        // 대출금이 남았는지 검사
        if(owner.getMoney() == 0) {
	    Service.clearScreen(); // 화면 초기화
	    System.out.println("· ------------------- · ◈ · ------------------- ·\n");
            System.out.printf("  %s 카페 %s 사장님! \n",owner.getCeo(),owner.getName());
            System.out.println("  더 이상 갚을 대출금이 없습니다.");
            System.out.println("  카페 운영을 계속해서 목표금액을 달성하세요!\n");
            System.out.println("  엔터를 눌러 메인메뉴로 돌아갑니다.");
            System.out.println("\n· ------------------- · ◈ · ------------------- ·\n");
            sc.nextLine();
	
	// 대출금 상환
        } else {
	    int repay = 0; // 갚을 대출금
	    while(true) {
		Service.clearScreen(); // 화면 초기화
	        System.out.println("· ------------------- · ◈ · ------------------- ·\n");
	        System.out.println("                    중 도 상 환 \n");
	        System.out.printf("  남은 대출금 : %5d kh\n",owner.getDept());
	        System.out.printf("  남은 잔액 : %5d kh\n",owner.getMoney());
		System.out.println("\n· ------------------- · ◈ · ------------------- ·\n");
	    
	        while(true) {
	            System.out.print("  갚을 대출금을 입력해주세요(kh) : ");
	            repay = sc.nextInt(); sc.nextLine();
	                
	            if (repay <= 0) { // 0 또는 음수 입력 시
		        System.out.println("  1 kh 이상의 금액만 입력 가능합니다.");
		    } else if (repay > owner.getMoney()) { // 잔액을 초과하는 금액 입력 시
	                System.out.println("  잔액이 부족합니다.");
		    } else if (repay > owner.getDept()) { // 대출금을 초과하는 금액 입력 시
		        System.out.println("  대출금을 초과해서 상환할 수 없습니다.");
	            } else {
	               	break;
		    }
	        }
	            
	        Service.repayment(repay); // 대출금 상환 서비스 호출
		
                // 대출금 상환 결과
	        if (owner.getDept() == 0) {
		    Service.clearScreen(); // 화면 초기화
		    System.out.println("· ------------------- · ◈ · ------------------- ·\n");
		    System.out.println("  축하합니다~!!");
		    System.out.printf("  %s 카페 %s 사장님! 대출금을 모두 갚았습니다!\n", owner.getCeo(), owner.getName());
		    System.out.println("  카페 운영을 계속해서 목표금액을 달성하세요!");
		    System.out.println("\n· ------------------- · ◈ · ------------------- ·\n");
		    System.out.println("  엔터를 눌러 메인메뉴로 돌아갑니다...\n");
		    sc.nextLine();
		    break;
	        } else {
		    Service.clearScreen(); // 화면 초기화
		    System.out.println("\n· ------------------- · ◈ · ------------------- ·\n");
		    System.out.printf("  남은 대출금 : %5d kh\n", owner.getDept());
		    System.out.printf("  남은 잔액 : %5d kh\n\n", owner.getMoney());
		    System.out.println("  1. 대출금 더 갚기");
		    System.out.println("  2. 메인메뉴로 돌아가기");
		    System.out.println("\n· ------------------- · ◈ · ------------------- ·\n");
		    System.out.print("  입력 : ");
		    int select = sc.nextInt();
		    if(select == 2) break;
	        }
	    }
        }
    }
}
