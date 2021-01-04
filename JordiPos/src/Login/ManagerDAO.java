package Login;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ManagerDAO {
	Scanner sc=new Scanner(System.in);

	Connection conn=null;
	PreparedStatement psmt=null;
	ResultSet rs=null;
	
	public void getConn() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String id = "hr";
			String pw = "hr";

			conn = DriverManager.getConnection(url, id, pw);

	

		} catch (ClassNotFoundException e) {
			System.out.println("����̹� �ε� ����");
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public void close() {
		try {
			if(rs!=null) {
				rs.close();
			}
			if(psmt !=null) {
				psmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	// ��������
	public void notice_select_view() {
		getConn();
		
		try {
			String sql="select * from notice";
			
			psmt=conn.prepareStatement(sql);
			rs=psmt.executeQuery();
			
			System.out.print("������ȣ \t");
			System.out.print("���� \t");
			System.out.print("���� \t");
			System.out.println("����");
			
			while(rs.next()) {
				System.out.print(rs.getString(1)+"\t");
				System.out.print(rs.getString(2)+"\t");
				System.out.print(rs.getString(3)+"\t");
				System.out.println(rs.getString(4));
			}
					
		} catch (Exception e) {
			System.out.println("�������� ��ȸ ����!");
			e.printStackTrace();
		}
		
		finally {
			close();
		}
		
		
		
		
	}
	
	public void notice_insert() {
		getConn();
		
		try {
			System.out.println("����");
			String title=sc.nextLine();
			System.out.println("����");
			String contents=sc.nextLine();
			
			String sql="insert into notice(n_number, n_title, n_content) values(n_number_seq.nextval, ?, ?)";
			
			psmt=conn.prepareStatement(sql);
			
			psmt.setString(1, title);
			psmt.setString(2, contents);
			
			int cnt=psmt.executeUpdate();
			
			if(cnt>0) {
				System.out.println("�����Ǿ����ϴ�.");
			}else{
				System.out.println("��ҵǾ����ϴ�.");
			}
			
		} catch (Exception e) {
			System.out.println("���� ����");
			e.printStackTrace();
		}
		
		finally {
			close();
		}
	}

	public void notice_delete() {
		getConn();
		
		try {
			System.out.print("������ �������� ��ȣ�� �Է��ϼ���");
			int notice_num=sc.nextInt();
			
			String sql="delete from notice where n_number=?";
			
			psmt=conn.prepareStatement(sql);
			
			psmt.setInt(1, notice_num);
			
			int cnt=psmt.executeUpdate();
			
			if(cnt>0) {
				System.out.println("�����Ǿ����ϴ�.");
			}else {
				System.out.println("��ҵǾ����ϴ�.");
			}
			
		} catch (Exception e) {
			System.out.println("���� ����");
			e.printStackTrace();
		}
		finally {
			close();
		}
		
	}
	
	public void notice_update() {
		getConn();
		
		try {
			System.out.println("������ �������� ��ȣ�� �Է��ϼ��� >> ");
			String s_num=sc.nextLine();
			int n_num= Integer.parseInt(s_num);
			System.out.println("������ �������� ������ �Է��ϼ��� >> ");
			String n_tt_update=sc.nextLine();
			System.out.println("������ �������� ������ �Է��ϼ��� >> ");
			String n_con_update=sc.nextLine();
			
			
			String sql="update notice set n_title=?, n_content=? where n_number=? ";
			
			psmt=conn.prepareStatement(sql);
			
			psmt.setString(1, n_tt_update);
			psmt.setString(2, n_con_update);
			psmt.setInt(3, n_num);
			
			int cnt=psmt.executeUpdate();
			
			if(cnt>0) {
				System.out.println("������ �����Ͽ����ϴ�.");
			}else {
				System.out.println("������ ��ҵǾ����ϴ�.");
			}
		} catch (Exception e) {
			System.out.println("���� ����");
			e.printStackTrace();
		}
		
		finally {
			close();
		}
		
	}

	// ��������
	public void branch_select_view() {
		getConn();
		
		try {
			String sql="select * from MMG";
			
			psmt=conn.prepareStatement(sql);
			rs=psmt.executeQuery();
			
			System.out.print("ȸ����ȣ \t");
			System.out.print("���̵� \t");
			System.out.print("��й�ȣ \t");
			System.out.println("�����ڵ�");
			
			while(rs.next()) {
				System.out.print(rs.getString(1)+"\t");
				System.out.print(rs.getString(2)+"\t");
				System.out.print(rs.getString(3)+"\t");
				System.out.println(rs.getString(4));
			}
					
		} catch (Exception e) {
			System.out.println("���� ��ȸ ����");
			e.printStackTrace();
		}
		
		finally {
			close();
		}
		
		
		
		
	}
	
	public void branch_insert() {
		getConn();
		
		try {
			System.out.print("���ο� ���̵� >> ");
			String insert_id=sc.next();
			System.out.print("��й�ȣ >> ");
			String insert_pw=sc.next();
			System.out.print("�����ڵ� >> ");
			int insert_loc=sc.nextInt();
						
			
			String sql="insert into MMG values(m_number_seq.nextval, ?, ?,?)";
			
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, insert_id);
			psmt.setString(2, insert_pw);
			psmt.setInt(3, insert_loc);
			
			int cnt=psmt.executeUpdate();
			
			if(cnt>0) {
				System.out.println("�߰��� �Ϸ��߽��ϴ�.");
			}else {
				System.out.println("�߰��� �����߽��ϴ�.");
			}
		} catch (Exception e) {
			System.out.println("���� �߰� ����");
			e.getStackTrace();
		}
		
		close();
	}

	public void branch_delete() {
		getConn();
		
		try {
			System.out.println("������ ������ȣ�� �����ϼ���");
			int m_num=sc.nextInt();
			
			String sql="delete from MMG where m_number=?";
			
			psmt=conn.prepareStatement(sql);
			
			psmt.setInt(1, m_num);
			
			int cnt=psmt.executeUpdate();
			
			if(cnt>0) {
				System.out.println("�����Ǿ����ϴ�.");
			}else {
				System.out.println("������ ��ҵǾ����ϴ�.");
			}
		} catch (Exception e) {
			System.out.println("���� ����");
			e.printStackTrace();
		}
		finally {
			close();
		}
		
	}
	
	public void branch_update() {
		getConn();
		
		try {
			System.out.println("������ ������ȣ�� �����ϼ���");
			int m_num_update=sc.nextInt();
			System.out.println("==����==");
			System.out.println("������ �������� �Է��ϼ���");
			String m_name=sc.next();
			System.out.println("������ �����ڵ带 �Է��ϼ���");
			String m_location=sc.next();
			
			String sql="update MMG set m_id=? m_location=? where m_number=?";
			
			psmt=conn.prepareStatement(sql);
			
			int cnt=psmt.executeUpdate();
			
			if(cnt>0) {
				System.out.println("�����Ͽ����ϴ�.");
			}else {
				System.out.println("������ ��ҵǾ����ϴ�.");
			}
						
		} catch (Exception e) {
			System.out.println("���� ����");
			e.printStackTrace();
		}
		finally {
			close();
		}
	}

}
