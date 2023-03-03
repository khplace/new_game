package view;

import java.util.Scanner;

import service.Service;

public class InitialScreenView {

    private Scanner sc = new Scanner(System.in);
    private MainView mainView = new MainView();
    private CreatersView creatersView = new CreatersView();
    private EndView endView = new EndView();

    public void displayInitialScreenView() {
        int input = 0;
        
        Service.clearScreen(); // 화면 초기화

        while(true) {
            try {
                System.out.println("KH PLACE");
                System.out.println("1. 게임 시작");
                System.out.println("2. 만든 사람");
                System.out.println("3. 프로그램 종료\n");

                System.out.print("입력>>>>");
                input = sc.nextInt(); sc.nextLine();

                switch (input) {
                    case 1: mainView.initialInput(); break;
                    case 2: creatersView.displayCreatersView(); return;
                    case 3: Service.exit(0); return;
                    default: System.out.println("잘못된 입력 방식입니다");
                }

            } catch (Exception e) {
                System.out.println("[잘못된 형식의 입력입니다.]\n");
                sc.nextLine(); //
                input = -1; //
            }
        }
    }
}