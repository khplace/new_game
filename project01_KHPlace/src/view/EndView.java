package view;

import java.util.Scanner;

import dto.Owner;
import service.Service;

public class EndView {
	
    private static Owner owner;
    
    
	
    public void displayEndView() {
    	    	
 //   int day = owner.getDay();  day에 값이 없어 nullpointexception 발생. 일단 주석 처리 중
    System.out.println("\n========게임 종료========\n");
    System.out.print("n일간의 장사를 마치고,");
    System.out.println("당신은 카페를 그만 두기로 결심했습니다.");
    System.out.println("");
    
    displayLastEndView();
    
    }
    
    
    
    
    public void displayWinEndView() {
    		
    	System.out.println("\t\t\t\t**축하드립니다!**");
    	System.out.println("\t\t\t대출금 상환 완료!");
    	System.out.println();
    	System.out.println("플레이 결과 : ");
 //   	System.out.println("일자 : " + owner.getDay());
    	System.out.println("순 수익(매출액-매출원가) : " ); // 보륜
    	
    	displayLastEndView();
    	
    }	    
    
    	    
    
    
    public void displayLoseEndView() {
    	
    	System.out.println("\t\t\t\tGAME OVER");
    	System.out.println("\t\t\t\t파산하셨습니다");
    	System.out.println();
    	System.out.println("플레이 결과 : ");
//    	System.out.println("일자 : " + owner.getDay());
    	System.out.println("순 수익(매출액-매출원가) : " ); // 보륜
    	
    	displayLastEndView();

    }
    	    
    	
     
    public void displayLastEndView() {
    		
       	System.out.println("1. 처음으로 돌아가기");
       	System.out.println("2. 프로그램 종료");
        	   
        	    
       	    System.out.println();

       	    System.out.print("무엇을 하시겠습니까? >> ");
       	    Scanner sc = new Scanner(System.in);
       	    int input = sc.nextInt();
       	    sc.nextLine(); // 입력버퍼에 남은 개행문자 제거

       	    System.out.println();

        	    
       	    InitialScreenView initialScreenView = new InitialScreenView();
       	    switch (input) {
      	        case 1:
       	        	initialScreenView.displayInitialScreenView(); break; // 처음으로 돌아가기
       	        case 2:
       	        	Service.exit(0); break; // 종료
       	        default:
       	            System.out.println("[잘못 입력하셨습니다.]");
    	}
    	
    	
    }
    
}
    
    
    

	


