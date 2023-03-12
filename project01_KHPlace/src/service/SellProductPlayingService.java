package service;

import dto.CashBook;
import dto.Order;
import dto.Owner;

import java.util.List;

public class SellProductPlayingService {

    private Owner owner = Service.getOwner();

    // 재고에서 물건 빼고, 판매 내역 기록하고, 매출 업데이트 하고, 다음 포문 돌리고
    public void sellProductPlaying(List<Order> orderList) {

        CashBook cashBook = owner.getTodayCashBook(); // 오늘자 가계부 받아오기
        int totalSellProductSum = 0; // 손님 당 판매수익 합계

        for (Order o : orderList) { // 손님1의 주문 하나씩 판매 처리
            // 재고 확인 (stock의 프로덕트 개수가 orderList의 프로덕트 개수보다 적을 때)
            if (owner.getStock().get(o.getProduct()) < o.getCount()) {
                System.out.printf("  %s의 재고가 부족합니다. (재고 : %d 개)\n", o.getProduct().getName(), owner.getStock().get(o.getProduct()));
                continue;
            }
            // 재고 확인 (stock의 프로덕트 개수가 orderList의 프로덕트 개수보다 같거나 많을 때)
            else {
                // 재고에서 물건 빼기
                int origin = owner.getProductStock(o.getProduct()); // 원래 있던 재고
                owner.setStock(o.getProduct(), origin - o.getCount()); //재고 업데이트

                // 판매 내역 기록
                totalSellProductSum += o.getProduct().getSellingPrice()*o.getCount(); // 총매출
            
                System.out.printf("  %s %d개가 판매되었습니다. (재고 : %d 개)\n", o.getProduct().getName(), o.getCount() , owner.getStock().get(o.getProduct()));
            }
 
            cashBook.getOrderList().add(o); // 방금 판매한 품목 cashBook.todayOrderList에 추가
        }
        System.out.println("· ------------------- · ◈ · ------------------- ·\n");
        // 매출 업데이트
        owner.addMoney(totalSellProductSum); // 잔액 최신화
        cashBook.addIncome(totalSellProductSum); // 판매 금액 cashBook.income에 더하기 / 하루 판매액 
    }
}
