package Member;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JOptionPane;

public class ProductDAO {
	
	Scanner scan = new Scanner(System.in);
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	@SuppressWarnings("rawtypes")
	Vector data = null;
	
	public void getConn() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "hr";
			String pw = "hr";	
					
				conn = DriverManager.getConnection(url,user,pw);
		}catch(Exception e) {
			System.out.println("get Conn ����");
		}
	}


public void close() {
	
	try {
		if(rs!=null)			
			rs.close();
		if(psmt!=null)
			psmt.close();
		if(conn!=null)
			conn.close();
			} catch (Exception e) {					
				e.printStackTrace();
			}
}

public void select() {

	getConn();
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");

	//3. SQL�� ����
			
		String sql = "Select * from PMG";						
		psmt = conn.prepareStatement(sql);
				
		rs = psmt.executeQuery(); // table�� ����� �����Ҷ��� executeUpdate();
		System.out.println("��ǰ��ȣ\t�޴���\t\t����\t���\t�Ǹŷ�\t�������");
		while(rs.next()) { //rs.next()�� ��ȯ�� => boolean
		System.out.print(rs.getString(1)+"\t");
		System.out.print(rs.getString(2)+"\t");
		System.out.print(rs.getString(3)+"\t");
		System.out.print(rs.getString(4)+"\t");
		System.out.print(rs.getInt(5)+"\t");
		System.out.print(rs.getDate(6)+"\n");
	

		}
	}catch (Exception e) {
		System.out.println("Select ����...");
		e.printStackTrace();	 		
	}
		
finally {
	
	close();
	}
}


public void select_exp() {

	getConn();
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");

	//3. SQL�� ����
			
		String sql = "Select p_name,e_date from PMG where e_date<=(sysdate+3)";						
		psmt = conn.prepareStatement(sql);
				
		rs = psmt.executeQuery(); // table�� ����� �����Ҷ��� executeUpdate();				
		while(rs.next()) { //rs.next()�� ��ȯ�� => boolean
		 JOptionPane.showMessageDialog(null, "��������� �ӹ��� ��ǰ�� �ֽ��ϴ�!!!\n"
		 		+ "��ǰ�� : "+rs.getString(1)+"\n"+ "������� : "+rs.getDate(2)+"\n");
		
		}
	}catch (Exception e) {
		System.out.println("Select ����...");
		e.printStackTrace();	 		
	}
		
finally {
	
	close();
	}
}

public void p_Insert() {
	
	getConn();
	
	
	try{
			System.out.print("��ǰ��ȣ : ");
			String p_id = scan.nextLine();			
			System.out.print("�޴��� : ");
			String p_name = scan.nextLine();			
			System.out.print("���� : ");
			String cost = scan.nextLine();
			int p_cost=Integer.valueOf(cost);
			System.out.print("��� : ");
			String remain = scan.nextLine();
			int p_remain =Integer.valueOf(remain);
			System.out.print("������� : ");
			String date = scan.nextLine();
			Date p_expire = Date.valueOf(date);
			
			String sql = "insert into PMG values(?,?,?,?,?,?)";
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, p_id);
			psmt.setString(2, p_name);
			psmt.setInt(3, p_cost);
			psmt.setInt(4,p_remain);
			psmt.setInt(5, 0);
			psmt.setDate(6, p_expire);
		
			
			int cnt = psmt.executeUpdate();
			if(cnt>0)
				System.out.println("������ �߰� ����!!");
			else
				System.out.println("������ �߰� ����...");	
			
	} catch (Exception e) {
		System.out.println("insert ����!");
		e.printStackTrace();			
	}// 4. ����ݱ�
	finally {
		close();
	}
	}		


}
