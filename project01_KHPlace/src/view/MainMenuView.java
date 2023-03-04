package view;

import dto.Owner;
import service.Service;

import java.util.Scanner;

public class MainMenuView {

    private Scanner sc = new Scanner(System.in);
    private Owner owner;

    public MainMenuView() {
        owner = Service.getOwner();
    }

    /**
     * 1. 게임 시작을 눌렀을 때 시작되는 화면
     */
    public void displayMainMenu() {
        int input = 0;

        while(true) {
            try {
                Service.clearScreen(); // 화면 초기화
                System.out.println(owner.getDay() + "일차");              // 회차 입력(변수 정해지면 printf로 변경)
                System.out.println("잔고 : " + owner.getMoney());         // 현 잔고 입력
                System.out.println("남은 대출금 : " + owner.getDept());    // 현 잔여 출액 입력
                System.out.println("다음 턴에 내야하는 이자 : " + Service.TodayDept());

                System.out.println("\n====KH PLACE====\n");
                System.out.println("1. 재고 확인");
                System.out.println("2. 물건 구매");
                System.out.println("3. 장사 시작");
                System.out.println("4. 중도 상환");
                System.out.println("0. 프로그램 종료 ");
                // 중간에 중단할 수 있는 프로그램 종료 버튼도 만드는게 좋지 않을까 해서 넣어 봄

                System.out.println();

                System.out.print("무엇을 하시겠습니까? >> ");
                input = Integer.parseInt(sc.nextLine());

                switch (input) {
                    case 1: new StockProductView().printStock(owner.getStock()); break; // 재고 확인 화면
                    case 2: new OrderProductView().displayOrderProductMenu(); break; // 물건 구매 화면
                    case 3: new SellProductView().displaySellProductMenu(); break; // 장사 시작 화면
                    case 4: new RepaymentView().displayRepaymentMenu(); break; // 중도 상환 화면
                    case 0: new EndView().displayEndView(); break; // 게임 오버 화면으로 넘김
                    default: WrongInputView.wrongInput(); // 잘못된 입력 시 경고문구 출력
                }
            } catch (NumberFormatException e) { // 숫자 입력이 아닌 경우
                new WrongInputView().wrongInput(); 
            } catch (Exception e) {
                Service.clearScreen(); // 화면 초기화
                System.out.println("알 수 없는 예외 발생\n");
                e.printStackTrace();
                return;
            }
            if(input == 0) break;
        }
    }
}