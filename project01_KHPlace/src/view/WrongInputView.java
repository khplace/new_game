package view;

import service.Service;

import java.util.Scanner;

public class WrongInputView {

    private static Scanner sc = new Scanner(System.in);

    public static void wrongInput() {
        Service.clearScreen(); // 화면 초기화
        System.out.println("· ------------------- · ◈ · ------------------- ·\n");
        System.out.println("  유효하지 않은 입력입니다.");
        System.out.println("  엔터를 눌러 이전 화면으로 이동...");
        System.out.println("\n· ------------------- · ◈ · ------------------- ·");
        sc.nextLine();
    }
}
