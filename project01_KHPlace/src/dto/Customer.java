package dto;

import service.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Customer 객체 생성 시 생성자를 통해 주문 목록 랜덤 생성.
 * getOrderList() 함수를 통해서 주문 목록(List<Order>)을 불러와 사용할 수 있다.
 */
public class Customer {

    private List<Order> orderList = new ArrayList<>(); // 주문 목록(랜덤으로 생성)
    private List<Product> productList = Service.getProductList(); // 상품 목록
    private static final int MAX_MENU_ORDER = 3; // 1주문 당 주문 메뉴 최대 갯수
    private static final int MAX_PRODUCT_ORDER = 5; // 메뉴 당 최대 주문량(손님)

    // 객체 생성 시 주문 목록 랜덤으로 생성
    public Customer() {
        int menuNum = (int)(Math.random() * MAX_MENU_ORDER + 1); // 주문 메뉴 종류: 1 ~ 3개
        for(int i=0; i<menuNum; i++) {
            int product = (int)(Math.random() * productList.size()); // 주문 메뉴: 0 ~ productList.size()-1
            int productCount = (int)(Math.random() * MAX_PRODUCT_ORDER + 1); // 메뉴 당 최대 주문량(손님): 1 ~ 5개
            orderList.add(new Order(productList.get(product), productCount));
        }
    }

    public List<Order> getOrderList() {
        return orderList;
    }
}
