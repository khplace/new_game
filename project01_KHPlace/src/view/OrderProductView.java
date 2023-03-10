package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dto.Order;
import dto.Owner;
import dto.Product;
import service.Service;

public class OrderProductView {
	
	Owner owner = Service.getOwner();
    private Scanner scanner = new Scanner(System.in);
    private List<Product> productList = Service.getProductList();

    private final int MIN_ORDER = 1; // 최소주문수량
    private final int MAX_ORDER = 100; // 최대주문수량

    public void displayOrderProductMenu() {
        List<Order> orderList = new ArrayList<>(); // 발주 목록(장바구니)
        boolean orderMore = true;

    	while(orderMore) {

            /* 구매 가능한 상품 목록 출력 */
            Service.clearScreen(); // 화면 초기화
            System.out.println("· ------------------- · ◈ · ------------------- ·\n");
            System.out.println("                    물 품 구 매 \n");
            System.out.println("================================================");
            System.out.println("    번호           메 뉴          살 때    팔 때   ");

            for (int i = 0; i < productList.size(); i++) {
                Product p = productList.get(i);
                System.out.printf("     %d\t\t%s\t\t%3d kh\t%3d kh\n", i + 1,
                        p.getName(), p.getBuyingPrice(), p.getSellingPrice());
            }
            System.out.println("================================================\n");

            /* 발주할 상품 사용자 입력 */
            while (true) {
                int menu = 0; // 발주할 메뉴
                int count = 0; // 발주할 수량

                System.out.print("메뉴/수량 입력(나가기 q, 주문 제출 y) : ");
                String[] inputs = scanner.nextLine().toLowerCase().split("/");

                try {
                    if (inputs[0].charAt(0) == 'q') return; // 메인메뉴로
                    if (inputs[0].charAt(0) == 'y') break; // 주문종료

                    menu = Integer.parseInt(inputs[0]) - 1;
                    count = Integer.parseInt(inputs[1]);

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

                // 중복상품 입력 시 구매 개수 누적
                boolean flag = true;
                for(int i=0; i<orderList.size(); i++) {
                	if (orderList.get(i).getProduct() == productList.get(menu)) {
                		int sum = orderList.get(i).getCount() + count;
                		orderList.get(i).setCount(sum);
                		flag = false;
                	}
                }
                if (flag) orderList.add(new Order(menu, count));
                
            }

            /* 현재 주문 내역 확인 (장바구니) */
            Service.clearScreen(); // 화면 초기화
            System.out.println("· ------------------- · ◈ · ------------------- ·\n");
            System.out.println("                    주 문 확 인\n");
            System.out.printf("  현재잔액 : %d kh\n",owner.getMoney());
            System.out.println("================================================");
            System.out.println("    번호           메 뉴          수 량    금 액   ");
            System.out.println("================================================");

            int sum = 0; // 합계
            for (int i = 0; i < orderList.size(); i++) {

                Product product = orderList.get(i).getProduct();
                int count = orderList.get(i).getCount();
                int price = product.getBuyingPrice() * count;
                sum += price; // 총합계

                System.out.printf("     %d\t\t%s\t\t%3d 개\t%3d kh\n", i + 1,
                        product.getName(), count, price);
            }

            System.out.println("================================================");
            System.out.printf("       합   계\t\t  \t%d kh\n", sum);
            System.out.println("================================================\n");
            System.out.println("· ------------------- · ◈ · ------------------- ·\n");

            char input;
            while (true) {
            	try {
	                System.out.println("주문내용 확정시 y, 추가를 원할 시 n, 메인메뉴로 돌아가려면 q를 입력해주세요.");
	                System.out.print("이대로 주문하시겠습니까? : ");
	                input = scanner.nextLine().toLowerCase().charAt(0);
	                if (input == 'q') return;   // 주문 취소 -> 메인메뉴로 이동
	                if (input == 'n') break;    // 주문메뉴 재출력
	                if (input == 'y') {         // 주문 제출 -> 주문 서비스로 이동
	                    orderMore = false;
	                    break;
	                }
            	} catch (ArrayIndexOutOfBoundsException e) { // 형식에 맞지 않은 입력 시
                    System.out.println("입력 형식을 확인해주세요.\n");
                    continue;
                } catch (NumberFormatException e) { // 숫자와 '/' 외의 문자 입력 시
                    System.out.println("유효하지 않은 입력입니다.\n");
                    continue;
                } catch (StringIndexOutOfBoundsException e) { // 공백 입력 시
                    System.out.println("유효하지 않은 입력입니다.\n");
                    continue;
                }
            	 System.out.println("유효하지 않은 입력입니다.");	
            }
        }

        /* 발주 목록 서비스로 전달 */
        if( !Service.Buying(orderList) ) {
            System.out.println("잔고가 부족하여 구매에 실패하였습니다.");
            System.out.println("엔터를 눌러 이전화면으로 돌아갑니다...");
            scanner.nextLine();
        }
    }  
}
