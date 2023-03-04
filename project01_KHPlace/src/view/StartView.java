package view;

import java.util.Scanner;

import service.Service;

public class StartView {

    private Scanner sc = new Scanner(System.in);
    
    private GameInitView gameInitView = new GameInitView(); // 1. 게임 시작 : 게임 초기 정보 입력(플레이어 정보)
    private CreatersView creatersView = new CreatersView(); // 2. 만든 사람

    public void displayInitialScreenView() {
        int input = 0;

        while(true) {
            Service.clearScreen(); // 화면 초기화
            System.out.println("· ------------------- · ◈ · ------------------- ·\n");
            System.out.println("               · K H   P L A C E ·\n");
            System.out.println("                   1. 게임 시작                  ");
            System.out.println("                   2. 만든 사람                  ");
            System.out.println("                 3. 프로그램 종료                 ");
            System.out.println("\n· ------------------- · ◈ · ------------------- ·\n");

            try {
                System.out.print("  메뉴 선택 >> ");
                input = Integer.parseInt(sc.nextLine());

                switch (input) {
                    case 1: gameInitView.startNewGame(); break; // 게임 초기 정보 입력(플레이어 정보)
                    case 2: creatersView.displayCreatersView(); break; // 만든이 정보 출력
                    case 3: Service.exit(0); return; // 프로그램 종료
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
        }
    }
}