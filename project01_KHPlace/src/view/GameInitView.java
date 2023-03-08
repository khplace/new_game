package view;

import service.Service;

import java.util.Scanner;

public class GameInitView {

    private String cafeName; // 카페 이름
    private String ceoName; // 사장 이름
    private int selectlevel; // 난이도

    private Scanner sc = new Scanner(System.in);

    public void startNewGame() {

        while(true) {
            Service.clearScreen(); // 화면 초기화
            System.out.println("· ------------------- · ◈ · ------------------- ·\n");
            System.out.println("  축하드립니다!");
            System.out.println("  당신은 꿈에 그리던 카페 사장이 되었습니다.");
            System.out.println("  카페 이름을 결정하고 계약을 완료해주세요.");
            System.out.println("  난이도를 설정해주세요.");
            System.out.println("  | 1.기본임대료 | 2.임대료 2배 | 3.임대료 3배 |");
            System.out.println("\n· ------------------- · ◈ · ------------------- ·\n");

            System.out.print("  카페 이름 : ");
            cafeName = sc.nextLine();
            System.out.print("  사장 이름 : ");
            ceoName = sc.nextLine();
            System.out.print("  난이도 : ");
            selectlevel = sc.nextInt();

            // cafeName과 ceoName이 모두 제대로 입력되었을때 while문 탈출
            if( !cafeName.isBlank() && !ceoName.isBlank()) break;

            // cafeName 또는 ceoName이 공백일 때
            Service.clearScreen(); // 화면 초기화
            System.out.println("· ------------------- · ◈ · ------------------- ·\n");
            System.out.println("  카페이름과 사장이름, 난이도를 올바르게 설정해주세요!");
            System.out.println("  엔터를 눌러 재설정합니다...");
            System.out.println("\n· ------------------- · ◈ · ------------------- ·");
            sc.nextLine();
        }

        Service.clearScreen(); // 화면 초기화
        System.out.println("· ------------------- · ◈ · ------------------- ·\n");
        System.out.println("  " + ceoName + " 사장님!");
        System.out.println("  " + cafeName + " 카페 오픈 준비가 모두 끝났습니다.");
        System.out.println("  대출금을 모두 갚고 성공적으로 가게를 운영하시길 바랍니다!");
        System.out.println("  엔터를 눌러 다음으로 진행 해 주세요...");
        System.out.println("\n· ------------------- · ◈ · ------------------- ·");
        sc.nextLine();

        Service.fillProductList();              // 물건 목록 채우기
        Service.createOwner(cafeName, ceoName); // 플레이어 정보 생성
        Service.gamelevel(selectlevel); // 난이도 설정
        new MainMenuView().displayMainMenu();   // 메인메뉴 출력
    }
}
