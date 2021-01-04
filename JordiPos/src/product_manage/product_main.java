package product_manage;
import javax.swing.JOptionPane;
import java.util.Scanner;

public class product_main {

	public static void main(String[] args) {
		ProductDAO dao = new ProductDAO();
		Scanner sc = new Scanner(System.in);
		boolean isOk = true;	
		
		
		while(isOk) {
		System.out.println("[1]재고 조회 [2]상품 추가 [3]종료");
		System.out.print("메뉴 선택 >> ");
		int menu = sc.nextInt();		
		switch(menu) {
		case 1:
			System.out.println("재고 목록 조회");
			dao.select_exp();
			dao.select();
			break;
		case 2:
			System.out.println("상품 추가");
			dao.p_Insert();
			break;
		case 3:
			System.out.println("종료");
			isOk = false;
			break;
		

		}
	}
}
}