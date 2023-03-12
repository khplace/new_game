package view;

import dto.CashBook;
import dto.Order;
import dto.Owner;
import dto.Product;
import service.Service;

import java.util.List;
import java.util.Scanner;

public class SellProductView {

    Scanner sc = new Scanner(System.in);
    EndView endView = new EndView();
    Owner owner = Service.getOwner();
    Service service = new Service();
    

    public void displaySellProductMenu() {
       Service.clearScreen();
        //Service.openShop();
        CashBook cashBook = owner.getTodayCashBook();

        Service.clearScreen(); // 화면 초기화
        System.out.println("· ------------------- · ◈ · ------------------- ·\n");
        System.out.println("                    일 일 결 산\n");
        System.out.println("    " + owner.getDay() + " 일차 매출");
        System.out.println("================================================");
        // 매출목록 출력
        List<Product> productList= Service.getProductList(); //판매제품목록
        List<Order> o = cashBook.getOrderList(); // 하루 총 판매목록
        int[] result = service.todaySellingCount(o); // 하루 총 판매액
        
        for(int i=0 ; i<result.length; i++) {
//        	System.out.println(productList.get(i).getName()+"/"+result[i]);
        	 System.out.printf("    %s.\t%s\t\t%2d잔 :\t%4d kh\n",i+1,productList.get(i).getName(),result[i],result[i]*productList.get(i).getSellingPrice());
        }
        System.out.println("\n    " + owner.getDay() + " 일차 비용");
        System.out.println("================================================");
        System.out.printf("    발주 비용 : \t%d kh\n", cashBook.getOutcome());
        // 발주목록은 출력할지 말지 결정(cashBook.getTodayBuyingList())
        System.out.printf("    건물 임대료 : \t%d kh\n", CashBook.RENT*owner.getlevel());
        System.out.println("================================================");
        System.out.printf("    %-8s\t\t(+)%5d kh\n","총 매출", cashBook.getIncome());
        System.out.printf("    %-8s\t\t(-)%5d kh\n","총 지출", cashBook.getOutcome() + CashBook.RENT*owner.getlevel());
        System.out.printf("    %-8s\t\t   %5d kh\n","잔액", owner.getMoney()); // 하루 종료 후 보유 금액
        System.out.printf("    %-8s\t\t   %5d kh\n","남은 대출금", owner.getDept());  // 하루 종료 후 남은 대출금
        System.out.println("================================================");
        // 기타 이벤트 발생 시 내용 추가
//        System.out.println("  기타 이벤트 발생 시 내용 추가\n");
        System.out.println("· ------------------- · ◈ · ------------------- ·\n");
        System.out.println("  다음 날로 넘어가려면 엔터를 눌러주세요...");
        sc.nextLine();
        
        Service.accumulateSales(cashBook.getIncome(), cashBook.getOutcome()); // 매출액 누적
        ViewEnding();      // 엔딩 조건 확인
        Service.nextDay(); // 다음 날 시작
    }
    
    public void ViewEnding() {
       if ( Service.judgingWin() )      endView.displayWinEndView();
       if ( Service.judgingBankrupt() ) endView.displayLoseEndView();
    }
}