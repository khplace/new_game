package view;

import dto.Product;
import service.Service;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class StockProductView {
   
    private Scanner scanner = new Scanner(System.in);
    private List<Product> productList = Service.getProductList();
   
    public void printStock(Map<Product, Integer> stock) {
    	Service.clearScreen(); // 화면 초기화
       // 재고 리스트
        System.out.println("================== 재 고  화 면 ==================");
        System.out.println("  번호    메 뉴\t\t\t\t개 수");
        System.out.println("================================================");

        // 각 메뉴는 객체로 존재
        // 반복문을 이용해서 전체 메뉴 출력
        for( int i=0; i<productList.size(); i++ ) {
        	int amount = stock.get(productList.get(i)) == null ? 0 :  stock.get(productList.get(i));
            System.out.printf("%4d\t%s\t\t\t\t%d 개\n",i+1,productList.get(i).getName(),stock.get(productList.get(i)));
        }
            
        System.out.println("================================================");


        while(true) {
     
            // 예외 처리
            try {
               System.out.print("이전 화면으로(q) >>> ");
               MainMenuView mainView = new MainMenuView();
               String inputs = scanner.nextLine().toLowerCase();
               if (inputs.charAt(0) == 'q') {
                  mainView.displayMainMenu(); // 메인메뉴로 돌아가기
               }
               else {
                  System.out.println("q를 입력해 주세요");
               }
            
            } catch (StringIndexOutOfBoundsException e) { // 공백 입력 시
            	System.out.println("입력 값을 입력해 주세요"); continue;

            }
        }
    }  
       
    }
    