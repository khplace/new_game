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
                System.out.println("· ------------------- · ◈ · ------------------- ·");
                System.out.printf("  안녕하세요~! %s카페 %s사장님 %d일차 장사 준비를 해주세요! \n",owner.getCeo(),owner.getName(),owner.getDay());
                System.out.printf("  현재 남은 잔고는 %dkh 입니다.\n",owner.getMoney());
                System.out.println("  남은 대출금 : " + owner.getDept());    // 현 잔여 출액 입력
                System.out.println("  다음 턴에 내야하는 이자 : " + Service.TodayDept());
                System.out.println("· ------------------- · ◈ · ------------------- ·");
                System.out.println("  1. 재고 확인");
                System.out.println("  2. 물건 구매");
                System.out.println("  3. 장사 시작");
                System.out.println("  4. 중도 상환");
                System.out.println("  0. 프로그램 종료 ");
                System.out.println("· ------------------- · ◈ · ------------------- ·");

                System.out.print("  무엇을 하시겠습니까? >> ");
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
            } catch (NullPointerException e) { // 첫시작 후 아무것도 안하고 0입력-프로그램종료시 null값있다고 오류발생
            	e.printStackTrace();;
            	return; // 맨처음 화면으로 돌아가기
            }catch (Exception e) {
                Service.clearScreen(); // 화면 초기화
                System.out.println("알 수 없는 예외 발생\n");
                e.printStackTrace();
                return; // 맨처음 화면으로 돌아가기
            }
            if(input == 0) break;
        }
    }
}