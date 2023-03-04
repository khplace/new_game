package dto;

import java.util.ArrayList;
import java.util.List;

public class CashBook {

    private List<Order> todayOrderList; // 하루 총 주문 목록
    private List<Order> todayBuyingList; // 하루 총 구매 목록
    private int income; // 하루 총 수입
    private int outcome; // 하루 총 지출
    public static final int RENT = 500; // 임대료

    public CashBook() {
        this.todayOrderList = new ArrayList<>();
        this.todayBuyingList = new ArrayList<>();
        this.income = 0;
        this.outcome = 0;
    }

    public List<Order> getTodayBuyingList() {
        return todayBuyingList;
    }

    public void setTodayBuyingList(List<Order> todayBuyingList) {
        this.todayBuyingList = todayBuyingList;
    }

    public List<Order> getTodayOrderList() {
        return todayOrderList;
    }

    public void setTodayOrderList(List<Order> todayOrderList) {
        this.todayOrderList = todayOrderList;
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
}