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
			System.out.println("get Conn 오류");
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

	//3. SQL문 전송
			
		String sql = "Select * from PMG";						
		psmt = conn.prepareStatement(sql);
				
		rs = psmt.executeQuery(); // table에 명령을 전달할때는 executeUpdate();
		System.out.println("상품번호\t메뉴명\t\t가격\t재고량\t판매량\t유통기한");
		while(rs.next()) { //rs.next()의 반환값 => boolean
		System.out.print(rs.getString(1)+"\t");
		System.out.print(rs.getString(2)+"\t");
		System.out.print(rs.getString(3)+"\t");
		System.out.print(rs.getString(4)+"\t");
		System.out.print(rs.getInt(5)+"\t");
		System.out.print(rs.getDate(6)+"\n");
	

		}
	}catch (Exception e) {
		System.out.println("Select 오류...");
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

	//3. SQL문 전송
			
		String sql = "Select p_name,e_date from PMG where e_date<=(sysdate+3)";						
		psmt = conn.prepareStatement(sql);
				
		rs = psmt.executeQuery(); // table에 명령을 전달할때는 executeUpdate();				
		while(rs.next()) { //rs.next()의 반환값 => boolean
		 JOptionPane.showMessageDialog(null, "유통기한이 임박한 상품이 있습니다!!!\n"
		 		+ "상품명 : "+rs.getString(1)+"\n"+ "유통기한 : "+rs.getDate(2)+"\n");
		
		}
	}catch (Exception e) {
		System.out.println("Select 오류...");
		e.printStackTrace();	 		
	}
		
finally {
	
	close();
	}
}

public void p_Insert() {
	
	getConn();
	
	
	try{
			System.out.print("상품번호 : ");
			String p_id = scan.nextLine();			
			System.out.print("메뉴명 : ");
			String p_name = scan.nextLine();			
			System.out.print("가격 : ");
			String cost = scan.nextLine();
			int p_cost=Integer.valueOf(cost);
			System.out.print("재고량 : ");
			String remain = scan.nextLine();
			int p_remain =Integer.valueOf(remain);
			System.out.print("유통기한 : ");
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
				System.out.println("데이터 추가 성공!!");
			else
				System.out.println("데이터 추가 실패...");	
			
	} catch (Exception e) {
		System.out.println("insert 오류!");
		e.printStackTrace();			
	}// 4. 연결닫기
	finally {
		close();
	}
	}		


}
