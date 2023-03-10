package view;

import java.util.Scanner;

import dto.Owner;
import service.Service;

public class EndView {

    private static Owner owner = Service.getOwner();

	Scanner sc = new Scanner(System.in);

    public void displayEndView() {
		while(true) {
			Service.clearScreen(); // 화면 초기화
			System.out.println("· ------------------- · ◈ · ------------------- ·\n");
			System.out.printf("  %d일간의 장사를 마치고,\n", owner.getDay());
			System.out.println("  당신은 카페를 그만 두기로 결심했습니다.\n");
			if( displayLastEndView() ) break;
		}
    }

    public void displayWinEndView() {
		System.out.println("· ------------------- · ◈ · ------------------- ·\n");
    	System.out.println("                  **축하드립니다!**");
    	System.out.println("                  대출금 상환 완료!");
    	System.out.println();
		System.out.println("\n· ------------------- · ◈ · ------------------- ·");
		dislayGameResult();
    }

	public void dislayGameResult() {
		Service.clearScreen(); // 화면 초기화
		System.out.println("· ------------------- · ◈ · ------------------- ·\n");
		System.out.println("                 플 레 이   결 과\n");
		System.out.println("장사 기간 : " + owner.getDay());
		System.out.println("순 수익(매출액-매출원가) : ");
		System.out.println("\n· ------------------- · ◈ · ------------------- ·");
		displayLastEndView();
	}

    public void displayLoseEndView() {
		System.out.println("· ------------------- · ◈ · ------------------- ·\n");
    	System.out.println("\t\t\t\tGAME OVER");
    	System.out.println("\t\t\t\t파산하셨습니다");

    	displayLastEndView();
    }

    public boolean displayLastEndView() {

       	System.out.println("  1. 처음으로 돌아가기");
       	System.out.println("  2. 진행상황 저장 후 종료");
       	System.out.println("  0. 프로그램 종료");
		System.out.println("\n· ------------------- · ◈ · ------------------- ·\n");
        System.out.print("  무엇을 하시겠습니까? >> ");

		try {
			int input = Integer.parseInt(sc.nextLine());

			StartView startView = new StartView();
			switch (input) {
				case 1: startView.displayInitialScreenView(); return true; // 처음으로 돌아가기
				case 2: new SaveAndLoadView().displaySaveMenu(1); // 게임 저장 후 종료
				case 0: Service.exit(0); // 종료
				default: WrongInputView.wrongInput(); // 잘못된 입력 시 경고문구 출력
			}
		} catch (NumberFormatException e) { // 숫자 입력이 아닌 경우
			WrongInputView.wrongInput(); // 잘못된 입력 시 경고문구 출력
		} catch (Exception e) {
			Service.clearScreen(); // 화면 초기화
			System.out.println("알 수 없는 예외 발생\n");
			e.printStackTrace();
		}
		return false;
    }
    
    
    
    
    
    
    
    
    
    
    
}