package Manager;

import java.util.Scanner;

public class Manager {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ManagerDAO mn = new ManagerDAO();
		boolean isOk1 = true;
		boolean isOk2 = true;
		boolean isOk3 = true;

		while (isOk1) {
			System.out.println("======�����ڸ��======");
			System.out.println("[1] ��������  \t [2] �������� \t [3] ����");
			System.out.print("�޴��� �����ϼ��� >>  ");

			int manager_num = sc.nextInt();

			switch (manager_num) {
			case 1:
				while (isOk2) {
					System.out.println("====��������=====");
					System.out.println("[1] ��ȸ \t [2] �Է� \t [3] ���� \t [4] ���� \t [5] ����");
					System.out.print("�޴��� �����ϼ��� >>  ");
					int managerNotice = sc.nextInt();

					switch (managerNotice) {
					case 1:
						mn.notice_select_view();
						break;
					case 2:
						mn.notice_insert();
						break;
					case 3:
						mn.notice_update();
						break;
					case 4:
						mn.notice_delete();
						break;
					case 5:
						System.out.println("����Ǿ����ϴ�.");
						isOk2 = false;
						break;
					}
				}
				break;
			case 2:
				while (isOk3) {
					System.out.println("==== �� �� =====");
					System.out.println("[1] ��ȸ \t [2] �Է� \t [3] ���� \t [4] ���� \t [5] ����");
					int managerNotice = sc.nextInt();

					switch (managerNotice) {
					case 1:
						mn.branch_select_view();
						break;
					case 2:
						mn.branch_insert();
						break;
					case 3:
						mn.branch_update();
						break;
					case 4:
						mn.branch_delete();
						break;
					case 5:
						System.out.println("����Ǿ����ϴ�.");
						isOk3 = false;
						break;
					}
				}
				break;
			case 3:
				System.out.println("����Ǿ����ϴ�.");
				isOk1 = false;
				break;
			}
		}

	}

}
