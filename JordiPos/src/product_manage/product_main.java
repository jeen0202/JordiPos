package product_manage;
import javax.swing.JOptionPane;
import java.util.Scanner;

public class product_main {

	public static void main(String[] args) {
		ProductDAO dao = new ProductDAO();
		Scanner sc = new Scanner(System.in);
		boolean isOk = true;	
		
		
		while(isOk) {
		System.out.println("[1]��� ��ȸ [2]��ǰ �߰� [3]����");
		System.out.print("�޴� ���� >> ");
		int menu = sc.nextInt();		
		switch(menu) {
		case 1:
			System.out.println("��� ��� ��ȸ");
			dao.select_exp();
			dao.select();
			break;
		case 2:
			System.out.println("��ǰ �߰�");
			dao.p_Insert();
			break;
		case 3:
			System.out.println("����");
			isOk = false;
			break;
		

		}
	}
}
}