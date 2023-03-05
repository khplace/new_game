package view;

import service.Service;

import java.util.Scanner;

import dto.Owner;

public class RepaymentView {

    Scanner sc = new Scanner(System.in);

    public void displayRepaymentMenu() {

        Owner owner = Service.getOwner();
  
        Service.clearScreen(); // 화면 초기화
        
        // 대출금이 남았는지 검사
        if(owner.getMoney() == 0) {
        	System.out.println("· ------------------- · ◈ · ------------------- ·\n");
        	System.out.printf("  %s 카페 %s 사장님! \n",owner.getCeo(),owner.getName());
        	System.out.println("  더 이상 갚을 대출금이 없습니다.");
        	System.out.println("  카페 운영을 계속해서 목표금액을 달성하세요!\n");
        	System.out.println("  엔터를 눌러 메인메뉴로 돌아갑니다.");
        	System.out.println("· ------------------- · ◈ · ------------------- ·\n");
        	sc.nextLine();
        }else {
        
	        int repay = 0; // 갚을 대출금
	        while(true) {
	            System.out.println("· ------------------- · ◈ · ------------------- ·\n");
	            System.out.println("                    중 도 상 환 \n");
	            
	            while(true) {
		        System.out.println("· ------------------- · ◈ · ------------------- ·\n");
	            	System.out.printf("  남은 대출금 : %5d kh\n",owner.getDept());
	                System.out.printf("  남은 잔액 : %5d kh\n",owner.getMoney());
	                System.out.print("  갚을 대출금을 입력해주세요(kh) : ");
	                repay = sc.nextInt();
	                sc.nextLine();
	                
	                if(repay<0 || repay>owner.getMoney()) {
	                	System.out.println("  잔액이 부족합니다.");
	                	continue;
	                }else {
	                	break;
	                }
	            }
	            
	            Service.repayment(repay);
	            
	            if(owner.getDept() == 0) {
	            	System.out.println("· ------------------- · ◈ · ------------------- ·\n");
	            	System.out.println("  축하합니다~!!");
	            	System.out.printf("  %s 카페 %s 사장님! 대출금을 모두 갚았습니다!\n",owner.getCeo(),owner.getName());
	            	System.out.println("  카페 운영을 계속해서 목표금액을 달성하세요!\n");
	            	System.out.println("  엔터를 눌러 메인메뉴로 돌아갑니다.");
	            	System.out.println("· ------------------- · ◈ · ------------------- ·\n");
	            	sc.nextLine();
	            	break;
	            }else {
		            System.out.println("\n· ------------------- · ◈ · ------------------- ·\n");
	            	System.out.printf("  남은 대출금 : %5d kh\n",owner.getDept());
	  	            System.out.printf("  남은 잔액 : %5d kh\n\n",owner.getMoney());
	  	            
	  	            System.out.println("  1. 대출금 더 갚기");
	  	            System.out.println("  2. 메인메뉴로 돌아가기\n");
	  	            System.out.print("  입력 : ");
	  	            int select = sc.nextInt();
	  	            if(select == 2) {
	  	                System.out.println("  메인메뉴로 돌아갑니다.");
	  	                break;
	  	            }
	            }
	        }
        }
    }
}
