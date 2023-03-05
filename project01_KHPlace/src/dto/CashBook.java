package dto;

import java.util.ArrayList;
import java.util.List;

public class CashBook {

    private List<Order> orderList; // 하루 총 주문 목록
    private List<Order> buyingList; // 하루 총 구매 목록
    private int income; // 하루 총 수입
    private int outcome; // 하루 총 지출
    public static final int RENT = 500; // 임대료
    public static final double INTEREST = 0.1; // 대출금 이자율

    public CashBook() {
        this.orderList = new ArrayList<>();
        this.buyingList = new ArrayList<>();
        this.income = 0;
        this.outcome = 0;
    }

    public List<Order> getBuyingList() {
        return buyingList;
    }

    public void setBuyingList(List<Order> buyingList) {
        this.buyingList = buyingList;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public int getOutcome() {
        return outcome;
    }

    public void setOutcome(int outcome) {
        this.outcome = outcome;
    }

    public void updateBuyingList(List<Order> orderList) {
        orderList.addAll(orderList);
    }
}