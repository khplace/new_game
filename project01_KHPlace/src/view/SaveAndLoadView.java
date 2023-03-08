package view;

import service.SaveAndLoadService;
import service.Service;

import java.util.Scanner;

public class SaveAndLoadView {

    SaveAndLoadService saveAndLoadService = new SaveAndLoadService();
    Scanner scanner = new Scanner(System.in);

    public void displayLoadMenu() {
        int input = 0;

        while(true) {
            try {
                Service.clearScreen(); // 화면 초기화
                System.out.println("· ------------------- · ◈ · ------------------- ·\n");
                System.out.println("                    불 러 오 기");
                System.out.println("\n· ------------------- · ◈ · ------------------- ·\n");
                System.out.println("  1. 기존 진행상황 불러오기");
                System.out.println("  2. 메인메뉴로 돌아가기");
                System.out.println("\n· ------------------- · ◈ · ------------------- ·\n");
                System.out.print("  무엇을 하시겠습니까? >> ");
                input = Integer.parseInt(scanner.nextLine());

                switch (input) {
                    case 1: saveAndLoadService.gameLoad(); break; // 게임저장 서비스
                    case 2: return; // 물건 구매 화면
                    default: WrongInputView.wrongInput(); // 잘못된 입력 시 경고문구 출력
                }
            } catch (NumberFormatException e) { // 숫자 입력이 아닌 경우
                WrongInputView.wrongInput();
            } catch (Exception e) {
                Service.clearScreen(); // 화면 초기화
                System.out.println("알 수 없는 예외 발생\n");
                e.printStackTrace();
                return;
            }
            if(input == 0) break;
        }
    }

    public void displaySaveMenu() {
        int input = 0;

        while(true) {
            try {
                Service.clearScreen(); // 화면 초기화
                System.out.println("· ------------------- · ◈ · ------------------- ·\n");
                System.out.println("                 진 행 상 황   저 장");
                System.out.println("\n· ------------------- · ◈ · ------------------- ·\n");
                System.out.println("  ** 현재 진행 상황을 저장할 시 기존 저장 내용은 사라집니다. **\n");
                System.out.println("    저장 일시\t: ");
                System.out.println("    카페 이름\t: ");
                System.out.println("    사장님 성함\t: ");
                System.out.println("    진행 일수\t: ");
                System.out.println("    잔액\t: ");
                System.out.println("    남은 대출금\t: ");
                System.out.println("\n· ------------------- · ◈ · ------------------- ·\n");
                System.out.println("  1. 현재 진행상황 저장하기");
                System.out.println("  2. 게임으로 돌아가기");
                System.out.println("\n· ------------------- · ◈ · ------------------- ·\n");
                System.out.print("  무엇을 하시겠습니까? >> ");
                input = Integer.parseInt(scanner.nextLine());

                switch (input) {
                    case 1: saveAndLoadService.gameSave(); break; // 불러오기 서비스
                    case 2: return; // 게임 메인화면으로 이동
                    default: WrongInputView.wrongInput(); // 잘못된 입력 시 경고문구 출력
                }
            } catch (NumberFormatException e) { // 숫자 입력이 아닌 경우
                WrongInputView.wrongInput();
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
