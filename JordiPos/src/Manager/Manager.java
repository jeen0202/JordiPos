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
			System.out.println("======관리자모드======");
			System.out.println("[1] 공지사항  \t [2] 지점관리 \t [3] 종료");
			System.out.print("메뉴를 선택하세요 >>  ");

			int manager_num = sc.nextInt();

			switch (manager_num) {
			case 1:
				while (isOk2) {
					System.out.println("====공지사항=====");
					System.out.println("[1] 조회 \t [2] 입력 \t [3] 수정 \t [4] 삭제 \t [5] 종료");
					System.out.print("메뉴를 선택하세요 >>  ");
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
						System.out.println("종료되었습니다.");
						isOk2 = false;
						break;
					}
				}
				break;
			case 2:
				while (isOk3) {
					System.out.println("==== 지 점 =====");
					System.out.println("[1] 조회 \t [2] 입력 \t [3] 수정 \t [4] 삭제 \t [5] 종료");
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
						System.out.println("종료되었습니다.");
						isOk3 = false;
						break;
					}
				}
				break;
			case 3:
				System.out.println("종료되었습니다.");
				isOk1 = false;
				break;
			}
		}

	}

}
