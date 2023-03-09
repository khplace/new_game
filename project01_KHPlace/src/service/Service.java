package service;

import dto.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Service {

    private static Owner owner;
    private static List<Product> productList = new ArrayList<>(); // 물건 목록

    public static void fillProductList() {
        productList.add(new Product("아메리카노",150,200));
        productList.add(new Product("카페라뗴",100,200));
        productList.add(new Product("카푸치노",300,500));
        productList.add(new Product("베이글",250,200));
        productList.add(new Product("케이크",100,200));
        productList.add(new Product("샌드위치",50,250));
    }

    public static void createOwner(String name, String ceo) {
        owner = new Owner(name, ceo);
    }

    public static Owner getOwner() {
        return owner;
    }

    public static List<Product> getProductList() {
        return productList;
    }

    public static void gameInitialization(String cafeName, String ceoName, int selectlevel) {
        if( productList.isEmpty() )
            Service.fillProductList();              // 물건 목록 채우기
        if( Service.getOwner() == null ) {
            Service.createOwner(cafeName, ceoName); // 플레이어 정보 생성
            Service.gamelevel(selectlevel); // 난이도 설정
        }
    }

    public static void gameInitialization(Owner owner) {
        if( productList.isEmpty() )
            Service.fillProductList();             // 물건 목록 채우기
        Service.owner = owner;
    }


    // 이번 턴에 내야하는 이자 계산
    public static int TodayDept() {
        int nowInterest = (int)(owner.getDept() * CashBook.INTEREST);
        return nowInterest;
    }

    /**
     * 물건 구매 서비스
     */
    public static boolean Buying(List<Order> orderList) {

        /* 구매 총액, 잔고 확인 */
        int sum = 0; // 장바구니 금액 총합
        for(Order o : orderList) {
            sum += o.calculateBuyingPrice();
        }
        if(owner.getMoney() < sum) return false; // 구매실패 -> 메인메뉴로

        /* 물품 재고 업데이트 */
        for(Order o : orderList) {
            owner.addStock(o.getProduct(), o.getCount());
        }

        /* 잔고 업데이트 */
        owner.setMoney(owner.getMoney() - sum);

        /* 가계부 업데이트 */
        CashBook cashBook = owner.getTodayCashBook(); // 오늘자 가계부 받아오기
        cashBook.updateBuyingList(orderList); // 상품 주문 목록 업데이트
        cashBook.setOutcome(cashBook.getOutcome() + sum); // 지출 금액 업데이트

        return true; // 구매
    }

    // 장사 개시
    public static void openShop() {

        System.out.println("재고가 부족합니다. 상품을 구입해주세요");
        Customer cus = new Customer();
        Random r = new Random();
        int day = owner.getDay(); // 회차
        List<Order> list = cus.getOrderList();// product 타입 선언되어야함 * 지금 에러뜸
        int sum = 0;
        int i = 1; // 메뉴번호를 업데이트

        for (Order o : list) {
            int guest = r.nextInt(10); // 손님수
            if(guest>o.getCount()){ // 재고보다 손님이 많은경우에
                guest = 0;
                sum+= o.getProduct().getSellingPrice() * guest; // 총매출
                int temp = o.getProduct().getSellingPrice()*guest;//제품마다의 매출
                int myMoney = o.getProduct().getRevenue()*guest; // 나의 잔액을 업데이트해주기위한 변수
                Service.getOwner().addMoney(myMoney); // 업데이트
                int orgin = Service.getOwner().getKey(o.getProduct()); // 원래 있던 재고
                Service.getOwner().setStock(o.getProduct(),(orgin-guest)); //재고 업데이트(손님수만큼 재고수를 줄여줌)
                System.out.printf("%d. %s %d개 x %dkh = %5dkh\n",i,o.getProduct().getName(),guest,o.getProduct().getSellingPrice(),temp);
            }
            else{
                sum+= o.getProduct().getSellingPrice() * guest; // 총매출
                int temp = o.getProduct().getSellingPrice()*guest;//제품마다의 매출
                int myMoney = o.getProduct().getRevenue()*guest; // 나의 잔액을 업데이트해주기위한 변수
                Service.getOwner().addMoney(myMoney); // 업데이트
                int orgin = Service.getOwner().getKey(o.getProduct()); // 원래 있던 재고
                Service.getOwner().setStock(o.getProduct(),(orgin-guest)); //재고 업데이트(손님수만큼 재고수를 줄여줌)
                System.out.printf("%d. %s %d개 x %dkh = %5dkh\n",i,o.getProduct().getName(),guest,o.getProduct().getSellingPrice(),temp);
            }
            i++; // 메뉴 번호 업데이트
        }

        int money = Service.getOwner().getMoney(); // 잔액 계속 업데이트

        // 가계부 업데이트
        CashBook cashBook = owner.getTodayCashBook(); // 오늘자 가계부 받아오기
//        cashBook.getTodayOrderList().addAll(/*오늘 처리한 주문 목록*/); // 오늘 판매한 목록 cashBook.todayOrderList에 추가
//        cashBook.setIncome(/*총 수익*/); // 판매 금액 cashBook.income에 추가

        Service.endDay(); // 임대료 지불 후 하루 종료
    }

    /**
     * 하루 종료
     */
    public static void endDay() {
        owner.setMoney(owner.getMoney() - CashBook.RENT); // 오늘자 임대료 납부
        owner.setDept(owner.getDept() + Service.TodayDept()); // 오늘자 대출이자 누적
    }
    public static void nextDay() {
        owner.setDay(owner.getDay() + 1); // 날짜 업데이트
        owner.getCashBookList().add(new CashBook()); // 다음날 가계부 생성
    }

    // 이자 상환
    public static void repayment(int input) {
        owner.setDept(owner.getDept() - input);
        owner.setMoney(owner.getMoney() - input);
    }

    // 순수익 (매출액 - 매입원가)
    // 매출액 : sellproduct 클래스
    // 매입 원가 :
    public static void totalrevenue() {

    }

    // 프로그램 종료
    public static void exit(int status) {
        Runtime.getRuntime().exit(status);
    }

    /**
     * 콘솔 화면 새로고침
     */
    public static void clearScreen() {
        try {
            final String os = System.getProperty("os.name");
            if(os.contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        }catch (Exception e) {
            // TODO: handle exception
        }
    }

    public static void lottoService() {
        int[] arr = null;

        if(owner.getMoney() <= 0 ){
            System.out.println("잔액 부족");
            return;
        }else {

            arr = new int[1];
            int sum =0;
            int num = (int)(Math.random() * 100 + 0);

            owner.setMoney(owner.getMoney()-10);  // 복권 금액

            for (int i = 0; i<arr.length; i++) {
                arr[i] = (int)(Math.random() * 5 + 1);
                if(arr[i]>3) {

                    sum = owner.getMoney() + num;
                    owner.setMoney(sum);
                    System.out.println("당첨입니다! : " + num + "원");
                    System.out.println();

                }else {
                    sum=owner.getMoney();
                    System.out.println("어머나! 꽝!");
                }
                for (int j = 0; j < i; j++) {
                    if (arr[j] == arr[i]) {
                        i--;
                        break;
                    }
                }
            }
            System.out.println("현재 잔액 : " + sum);
        }
    }

    public boolean judgingEnding() {
        if (owner.getDept() == 0 || owner.getMoney() >= 10000) return true;
        if (owner.getMoney() < 0 ) return false;
        return false;
    }

    public static void gamelevel(int selectlevel) { // 난이도 선택
        owner.setlevel(owner.getlevel()*selectlevel);

    }

    // 재고에서 물건 빼고, 판매 내역 기록하고, 매출 업데이트 하고, 다음 포문 돌리고
    public static void sellProductPlaying() {

        Scanner sc = new Scanner(System.in);
        Customer customer = new Customer();
        List<Order> orderList = customer.getOrderList();

        int day = Service.getOwner().getDay(); // 회차
        int totalSellProductSum = 0;
        int menuNum = 1; // 메뉴번호를 업데이트

        for (Order o : orderList) { // 손님1의 주문 하나씩 판매 처리

            // 재고에서 물건 빼기
            int orgin = Service.getOwner().getKey(o.getProduct()); // 원래 있던 재고
            Service.getOwner().setStock(o.getProduct(),o.getCount()); //재고 업데이트(손님수만큼 재고수를 줄여줌)

            // 판매 내역 기록
            totalSellProductSum += o.getProduct().getSellingPrice() * o.getCount(); // 총매출

            // 매출 업데이트
            int temp = o.getProduct().getSellingPrice()*o.getCount(); //제품마다의 매출
            int myMoney = o.getProduct().getRevenue()*o.getCount(); // 나의 잔액을 업데이트해주기위한 변수
            Service.getOwner().addMoney(myMoney); // 업데이트
        }
    }
} // 클래스 끝