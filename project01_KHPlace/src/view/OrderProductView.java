package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dto.Order;
import dto.Product;
import service.Service;

public class OrderProductView {

    private Scanner scanner = new Scanner(System.in);
    private List<Product> productList = Service.getProductList();

    private final int MIN_ORDER = 1; // 최소주문수량
    private final int MAX_ORDER = 100; // 최대주문수량

    public void displayOrderProductMenu() {
        List<Order> orderList = new ArrayList<>(); // 발주 목록(장바구니)
        boolean orderMore = true;

    	while(orderMore) {
            Service.clearScreen(); // 화면 초기화
            System.out.println("· ------------------- · ◈ · ------------------- ·\n");
            System.out.println("                    물 품 구 매 \n");
            System.out.println("================================================");
            System.out.println("    번호           메 뉴          살 때    팔 때   ");
            // 각 메뉴는 객체로 존재
            // 메뉴가 정해지면 반복문을 이용해서 전체 메뉴 출력
            for (int i = 0; i < productList.size(); i++) {
                String productName = productList.get(i).getName();
                int buyPrice = productList.get(i).getBuyingPrice();
                int sellPrice = productList.get(i).getSellingPrice();

                System.out.printf("     %d\t\t%s\t\t%3dkh\t%3dkh\n", i + 1, productName, buyPrice, sellPrice);
            }
            System.out.println("================================================\n");

            /* 발주할 메뉴 사용자 입력 */
            while (true) {
                int menu = 0; // 발주할 메뉴
                int count = 0; // 발주할 수량

                System.out.print("메뉴/수량 입력(나가기 q, 주문 제출 y) : ");
                String[] inputs = scanner.nextLine().toLowerCase().split("/");

                try {
                    if (inputs[0].charAt(0) == 'q') return; // 메인메뉴로
                    if (inputs[0].charAt(0) == 'y') break; // 주문종료

                    menu = Integer.valueOf(inputs[0]) - 1;
                    count = Integer.valueOf(inputs[1]);
                } catch (ArrayIndexOutOfBoundsException e) { // 형식에 맞지 않은 입력 시
                    System.out.println("입력 형식을 확인해주세요.(메뉴/수량)\n");
                    continue;
                } catch (NumberFormatException e) { // 숫자와 '/' 외의 문자 입력 시
                    System.out.println("유효하지 않은 입력입니다.\n");
                    continue;
                } catch (StringIndexOutOfBoundsException e) { // 공백 입력 시
                    System.out.println("유효하지 않은 입력입니다.\n");
                    continue;
                }

                if (menu < 0 || menu >= productList.size() || count < MIN_ORDER || count > MAX_ORDER) {
                    System.out.println("입력 범위를 초과하였습니다. (최대주문수량 " + MAX_ORDER + " 개)\n");
                    continue;
                }

                orderList.add(new Order(productList.get(menu), count));
            }

            /* 현재 주문 내역 확인 (장바구니) */
            System.out.println("\n================== 주 문  확 인 ==================");
            System.out.println("    번호           메 뉴          수 량    금 액   ");
            System.out.println("================================================");

            int sum = 0; // 합계
            for (int i = 0; i < orderList.size(); i++) {

                Product product = orderList.get(i).getProduct();
                int count = orderList.get(i).getCount();
                int price = product.getBuyingPrice() * count;
                sum += price; // 총합계

                System.out.printf("     %d\t\t%s\t\t%3d 개\t%3d kh\n", i + 1,
                        product.getName(), count, product.getBuyingPrice(), price);
            }

            System.out.println("================================================");
            System.out.printf("       합   계\t\t  \t%d kh", sum);
            System.out.println();

            char input;
            while (true) {
                System.out.println("주문내용 확정시 y, 추가를 원할 시 n, 메인메뉴로 돌아가려면 q를 입력해주세요.");
                System.out.print("이대로 주문하시겠습니까? : ");
                input = scanner.nextLine().toLowerCase().charAt(0);
                if (input == 'q') return;   // 주문 취소 -> 메인메뉴로 이동
                if (input == 'n') break;    // 주문메뉴 재출력
                if (input == 'y') {         // 주문 제출 -> 주문 서비스로 이동
                    orderMore = false;
                    break;
                }
                System.out.println("유효하지 않은 입력입니다.");
            }
        }
        Service.Buying(orderList); // 발주 목록 서비스로 전달
    }  
}
