package view;

import service.Service;

import java.util.Scanner;

public class CreatersView {

    public void displayCreatersView() {
        Service.clearScreen(); // 화면 초기화
        System.out.println("· ------------------- · ◈ · ------------------- ·\n");
        System.out.println("                     권 재 웅");
        System.out.println("                     김 자 민");
        System.out.println("                     장 진 혁");
        System.out.println("                     조 우 형");
        System.out.println("                     최 유 나");
        System.out.println("\n                Special Thanks To");
        System.out.println("                     백 동 현");
        System.out.println("\n· ------------------- · ◈ · ------------------- ·\n");
        System.out.print("  엔터를 눌러 메인화면으로 돌아가기...");
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
    }
}
