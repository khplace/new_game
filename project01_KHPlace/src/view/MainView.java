package view;

import dto.Owner;
import service.Service;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainView {

    private Scanner sc = new Scanner(System.in);

    private OrderProductView orderProductView = new OrderProductView();
    private RepaymentView repaymentView = new RepaymentView();
    private SellProductView sellProductView = new SellProductView();
    private StockProductView stockProductView = new StockProductView();
    private EndView endView = new EndView();

    /**
     * 1. 게임 시작을 눌렀을 때 시작되는 화면
     */
    public void initialInput() {
        Service.clearScreen(); // 화면 초기화

        System.out.println("축하드립니다!\n"
                         + "당신은 꿈에 그리던 카페 사장이 되었습니다.\n"
                         + "카페 이름을 정해주세요.\n");
        System.out.println("테스트");
        String name;
        String ceo;
        
        while(true) {
        	System.out.print("카페 이름 : ");
	        name = sc.nextLine();
	        	
	        System.out.print("사장 이름 :");
	        ceo = sc.nextLine();
	        
	        if( !(name.isBlank()) && !(ceo.isBlank())) break; // name과ceo가 모두 입력되었을때 탈출
	        
	        System.out.println("카페이름이나 사장이름을 설정해주세요!");
        }

        Service.createOwner(name, ceo);
        displayMainMenu();
    }

    public void displayMainMenu() {

        Owner owner = Service.getOwner();
       
        System.out.println();

        int input = 0;

        while(true) {

            try {
            	System.out.println(owner.getDay() + "일차");
                 // 회차 입력(변수 정해지면 printf로 변경)
            	System.out.println("잔고 : " + owner.getMoney());
                 // 현 잔고 입력
                System.out.println("남은 대출금 : " + owner.getDept());
                 // 현 잔여 출액 입력
                System.out.println("다음 턴에 내야하는 이자 : " + Service.TodayDept());
                 // 어차피 이자는 고정인데 이 항목이 꼭 있어야 하나?

                System.out.println("\n====KH PLACE====\n");
                System.out.println("1. 재고 확인");
                System.out.println("2. 물건 구매");
                System.out.println("3. 장사 시작");
                System.out.println("4. 중도 상환");
                System.out.println("0. 프로그램 종료 ");
                // 중간에 중단할 수 있는 프로그램 종료 버튼도 만드는게 좋지 않을까 해서 넣어 봄

                System.out.println();

                System.out.print("무엇을 하시겠습니까? >> ");
                input = sc.nextInt();
                sc.nextLine(); // 입력버퍼에 남은 개행문자 제거

                System.out.println();

                switch (input) {

                    case 1:
                        stockProductView.printStock(owner.getStock());
                        break; // 재고 확인 화면
                    case 2:
                        orderProductView.displayOrderProductMenu();
                        break; // 물건 구매 화면
                    case 3:
                        sellProductView.displaySellProductMenu();
                        break; // 장사 시작 화면
                    case 4:
                        repaymentView.displayRepaymentMenu();
                        break; // 중도 상환 화면
                    case 0:
                    	endView.displayEndView();
                        break; // 게임 오버 화면으로 넘김
                    default:
                        System.out.println("[잘못 입력하셨습니다.]");
                }


            } catch (InputMismatchException e) {
                System.out.println("[잘못된 형식의 입력입니다.]");
                sc.nextLine(); // 입력버퍼에 잘못 입력된 내용 제거
                input = -1; // 반복문이 종료 되는 것을 방지
            }
            System.out.println();
            if(input == 0) break;
        }

    }
}