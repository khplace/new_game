package view;

import service.Service;

import java.util.Scanner;

import dto.Owner;

public class RepaymentView {

    Scanner sc = new Scanner(System.in);

    public void displayRepaymentMenu() {

        Owner owner = Service.getOwner();
  

        while(true) {
            System.out.println("======중도상환======");
            System.out.printf("남은 대출금 : %5d kh\n",owner.getDept());
            System.out.printf("남은 잔액 : %5d kh\n",owner.getMoney());
            System.out.print("갚을 대출금을 입력해주세요(kh) : ");
            int repay = sc.nextInt();
            sc.nextLine();

            Service.repayment(repay);
            System.out.printf("남은 대출금 : %5d kh\n",owner.getDept());
            System.out.printf("남은 잔액 : %5d kh\n",owner.getMoney());
            
            System.out.println("1. 대출금 갚기");
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
