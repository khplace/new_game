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
                System.out.printf("%s의 재고가 부족합니다. (재고 : %d 개)\n", o.getProduct().getName(), owner.getStock().get(o.getProduct()));
                System.out.println("손님을 그냥 보냈습니다.");
                System.out.println("· ------------------- · ◈ · ------------------- ·\n");
                return;
            }
            // 재고 확인 (stock의 프로덕트 개수가 orderList의 프로덕트 개수보다 같거나 많을 때)
            else {
                // 재고에서 물건 빼기
                int orgin = owner.getProductStock(o.getProduct()); // 원래 있던 재고
                owner.setStock(o.getProduct(), o.getCount()); //재고 업데이트(손님수만큼 재고수를 줄여줌)

                // 판매 내역 기록
                totalSellProductSum += o.getProduct().getSellingPrice() * o.getCount(); // 총매출

                // 매출 업데이트
                int temp = o.getProduct().getSellingPrice() * o.getCount(); //제품마다의 매출
                owner.addMoney(o.getProduct().getSellingPrice() * o.getCount()); // 업데이트
            }

            // 가계부 업데이트
            cashBook.getOrderList().addAll(orderList); // 오늘 판매한 목록 cashBook.todayOrderList에 추가
            cashBook.addIncome(totalSellProductSum); // 판매 금액 cashBook.income에 더하기
        }
    }
}
