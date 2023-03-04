package view;

import dto.CashBook;
import dto.Owner;
import dto.Product;
import service.Service;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class SellProductView {

    Scanner sc = new Scanner(System.in);
    Owner owner = Service.getOwner();

    public void displaySellProductMenu() {

        Service.openShop();
        CashBook cashBook = owner.getTodayCashBook();

        Service.clearScreen(); // 화면 초기화
        System.out.println("· ------------------- · ◈ · ------------------- ·\n");
        System.out.println("                    일 일 결 산\n");
        System.out.println("================================================");
        System.out.println("  " + owner.getDay() + " 일차 매출");
        // 매출목록 출력(cashBook.getTodayOrderList())
        System.out.printf("    1. 아메리카노 3잔 : 600 kh\n", cashBook.getOutcome());
        System.out.println("================================================");
        System.out.println("  " + owner.getDay() + " 일차 비용");
        System.out.printf("    1. 발주 비용 : %d kh\n", cashBook.getOutcome());
        // 발주목록은 출력할지 말지 결정(cashBook.getTodayBuyingList())
        System.out.printf("    2. 건물 임대료 : %d kh\n", CashBook.RENT);
        System.out.println("================================================");
        System.out.printf("  총 매출              +%d kh\n", cashBook.getIncome());
        System.out.printf("  총 지출              -%d kh\n", cashBook.getOutcome() + CashBook.RENT);
        System.out.printf("  잔액                 %d kh\n", owner.getMoney()); // 하루 종료 후 보유 금액
        System.out.printf("  남은 대출금           %d kh\n", owner.getDept());  // 하루 종료 후 남은 대출금
        System.out.println("================================================");
        // 기타 이벤트 발생 시 내용 추가
        System.out.println("  기타 이벤트 발생 시 내용 추가\n");
        System.out.println("· ------------------- · ◈ · ------------------- ·\n");
        System.out.println("  다음 날로 넘어가려면 엔터를 눌러주세요...");
        sc.nextLine();
    }
}
