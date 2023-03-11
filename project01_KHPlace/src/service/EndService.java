package service;

import dto.CashBook;
import dto.Order;
import dto.Product;

import java.util.List;

public class EndService {

    List<CashBook> cashBookList = Service.getOwner().getCashBookList();

    /**
     * 품목별 총 판매랑 계산
     */
    public int[] totalSellingResult() {
        List<Product> productList= Service.getProductList();
        int[] result = new int[productList.size()];

        for(CashBook c : cashBookList)  // 게임 플레이 일자만큼 생성된 CashBook 모두 탐색
        for(Order o : c.getOrderList()) // 일자별 orderList 탐색
        for(int i=0; i<productList.size(); i++) { // 품목별 판매갯수 업데이트
            if(productList.get(i).equals(o.getProduct())) {
                result[i] += o.getCount();
                break;
            }
        }
        return result;
    }
}
