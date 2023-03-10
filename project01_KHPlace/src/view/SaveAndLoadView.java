package view;

import dto.Owner;
import dto.SaveData;
import service.SaveAndLoadService;
import service.Service;

import java.text.SimpleDateFormat;
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
                CurrentSavedDataPrint(saveAndLoadService.getSavedData());
                System.out.println("\n· ------------------- · ◈ · ------------------- ·\n");
                System.out.println("  1. 기존 진행상황 불러오기");
                System.out.println("  2. 메인메뉴로 돌아가기");
                System.out.println("\n· ------------------- · ◈ · ------------------- ·\n");
                System.out.print("  이 데이터를 불러오겠습니까? >> ");
                input = Integer.parseInt(scanner.nextLine());

                switch (input) {
                    case 1:
                        if( !saveAndLoadService.isSavedDataExists() ) { // 저장된 파일이 존재하지 않을 시
                            noSavedData(); return;
                        } else if(saveAndLoadService.gameLoad()) {  // 게임 불러오기 서비스 호출
                            loadSuccess();                          // 불러오기 성공 화면 출력
                            new MainMenuView().displayMainMenu();   // 메인메뉴 출력
                        } else loadFail();                          // 불러오기 실패 화면 출력

                    case 2: return;                                 // 프로그램 초기 화면으로 복귀

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

    /**
     * 게임 저장시 출력 화면
     * @param exit-저장 후 게임으로 돌아갈지 게임을 종료할지 구분(0: 게임으로 복귀, 1: 게임 종료)
     */
    public void displaySaveMenu(int exit) {
        int input = 0;

        while(true) {
            try {
                Service.clearScreen(); // 화면 초기화
                System.out.println("· ------------------- · ◈ · ------------------- ·\n");
                System.out.println("                 진 행 상 황   저 장");
                System.out.println("\n· ------------------- · ◈ · ------------------- ·\n");

                if( saveAndLoadService.isSavedDataExists() ) {
                    System.out.println("!!!!!!!!!!!!!!!!!!!!!WARNING!!!!!!!!!!!!!!!!!!!!!");
                    System.out.println(" 저장 데이터가 있습니다. 아래 내용을 지우고 새로 저장하시겠습니까?");
                    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
                    CurrentSavedDataPrint(saveAndLoadService.getSavedData());
                } else {
                    System.out.println("           현재 저장된 데이터가 없습니다.");
                }
                System.out.println("\n· ------------------- · ◈ · ------------------- ·\n");
                System.out.println("  1. 현재 진행상황 저장하기");
                System.out.println("  2. 게임으로 돌아가기");
                System.out.println("\n· ------------------- · ◈ · ------------------- ·\n");
                System.out.print("  무엇을 하시겠습니까? >> ");
                input = Integer.parseInt(scanner.nextLine());

                switch (input) {
                    case 1:
                        if( saveAndLoadService.gameSave() ) { // 저장 서비스
                            if( exit == 0) saveSuccess();  // 저장 성공 화면
                            else saveSuccessAndGameExit(); // 저장 성공 후 게임 종료 화면
                        } else saveFail();  // 저장 실패 화면
                    case 2: return; // 진행중이던 게임으로 복귀
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

    /**
     * 게임 저장 성공 시 출력할 화면
     */
    private void saveSuccess() {
        Service.clearScreen(); // 화면 초기화
        System.out.println("· ------------------- · ◈ · ------------------- ·\n");
        System.out.println("  저장 성공!");
        System.out.println("  엔터를 눌러 게임으로 돌아갑니다...");
        System.out.println("\n· ------------------- · ◈ · ------------------- ·\n");
        scanner.nextLine();
    }

    /**
     * 게임 저장 성공 후 게임 종료할 때 출력할 화면
     */
    private void saveSuccessAndGameExit() {Service.clearScreen(); // 화면 초기화
        System.out.println("· ------------------- · ◈ · ------------------- ·\n");
        System.out.println("  저장 성공!");
        System.out.println("  엔터를 눌러 게임을 종료합니다...");
        System.out.println("\n· ------------------- · ◈ · ------------------- ·\n");
        scanner.nextLine();
    }

    /**
     * 게임 저장 실패 시 출력할 화면
     */
    private void saveFail() {
        Service.clearScreen(); // 화면 초기화
        System.out.println("· ------------------- · ◈ · ------------------- ·\n");
        System.out.println("  저장 실패");
        System.out.println("  저장하지 않고 게임 종료 시 진행 데이터를 잃을 수 있으니 주의하시기 바랍니다.");
        System.out.println("  엔터를 눌러 게임으로 돌아갑니다...");
        System.out.println("\n· ------------------- · ◈ · ------------------- ·\n");
        scanner.nextLine();
    }

    /**
     * 게임 불러오기 성공 시 출력할 화면
     */
    private void loadSuccess() {
        Service.clearScreen(); // 화면 초기화
        System.out.println("· ------------------- · ◈ · ------------------- ·\n");
        System.out.println("  불러오기 성공!");
        System.out.println("  엔터를 눌러 게임을 이어서 시작합니다...");
        System.out.println("\n· ------------------- · ◈ · ------------------- ·\n");
        scanner.nextLine();
    }

    /**
     * 게임 불러오기 실패(저장된 데이터 없음) 시 출력할 화면
     */
    private void noSavedData() {
        Service.clearScreen(); // 화면 초기화
        System.out.println("· ------------------- · ◈ · ------------------- ·\n");
        System.out.println("  불러오기 실패");
        System.out.println("  저장된 파일이 없습니다.");
        System.out.println("  게임 시작을 눌러 새로운 게임을 시작해 주세요.");
        System.out.println("  엔터를 눌러 초기 화면으로 돌아갑니다...");
        System.out.println("\n· ------------------- · ◈ · ------------------- ·\n");
        scanner.nextLine();
    }

    /**
     * 게임 불러오기 실패 시 출력할 화면
     */
    private void loadFail() {
        Service.clearScreen(); // 화면 초기화
        System.out.println("· ------------------- · ◈ · ------------------- ·\n");
        System.out.println("  불러오기 실패");
        System.out.println("  엔터를 눌러 게임으로 돌아갑니다...");
        System.out.println("\n· ------------------- · ◈ · ------------------- ·\n");
        scanner.nextLine();
    }

    private void CurrentSavedDataPrint(SaveData saveData) {
        Owner owner = saveData.getOwner();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 HH:mm:ss");

        System.out.println("  [ 기존 저장 데이터 ]\n");
        System.out.println("    저장 일시 : " + sdf.format(saveData.getDate()));
        System.out.println("    카페 이름 : " + owner.getName());
        System.out.println("    사장님 성함 : " + owner.getCeo());
        System.out.println("    진행 일수 : " + owner.getDay());
        System.out.println("    잔액 : " + owner.getMoney());
        System.out.println("    남은 대출금 : " + owner.getDept());
    }
}
