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
			System.out.println("드라이버 로딩 오류");
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
	
	
	// 공지사항
	public void notice_select_view() {
		getConn();
		
		try {
			String sql="select * from notice";
			
			psmt=conn.prepareStatement(sql);
			rs=psmt.executeQuery();
			
			System.out.print("공지번호 \t");
			System.out.print("제목 \t");
			System.out.print("일자 \t");
			System.out.println("내용");
			
			while(rs.next()) {
				System.out.print(rs.getString(1)+"\t");
				System.out.print(rs.getString(2)+"\t");
				System.out.print(rs.getString(3)+"\t");
				System.out.println(rs.getString(4));
			}
					
		} catch (Exception e) {
			System.out.println("공지사항 조회 실패!");
			e.printStackTrace();
		}
		
		finally {
			close();
		}
		
		
		
		
	}
	
	public void notice_insert() {
		getConn();
		
		try {
			System.out.println("제목");
			String title=sc.nextLine();
			System.out.println("내용");
			String contents=sc.nextLine();
			
			String sql="insert into notice(n_number, n_title, n_content) values(n_number_seq.nextval, ?, ?)";
			
			psmt=conn.prepareStatement(sql);
			
			psmt.setString(1, title);
			psmt.setString(2, contents);
			
			int cnt=psmt.executeUpdate();
			
			if(cnt>0) {
				System.out.println("수정되었습니다.");
			}else{
				System.out.println("취소되었습니다.");
			}
			
		} catch (Exception e) {
			System.out.println("수정 오류");
			e.printStackTrace();
		}
		
		finally {
			close();
		}
	}

	public void notice_delete() {
		getConn();
		
		try {
			System.out.print("삭제할 공지사항 번호를 입력하세요");
			int notice_num=sc.nextInt();
			
			String sql="delete from notice where n_number=?";
			
			psmt=conn.prepareStatement(sql);
			
			psmt.setInt(1, notice_num);
			
			int cnt=psmt.executeUpdate();
			
			if(cnt>0) {
				System.out.println("삭제되었습니다.");
			}else {
				System.out.println("취소되었습니다.");
			}
			
		} catch (Exception e) {
			System.out.println("삭제 오류");
			e.printStackTrace();
		}
		finally {
			close();
		}
		
	}
	
	public void notice_update() {
		getConn();
		
		try {
			System.out.println("수정할 공지사항 번호를 입력하세요 >> ");
			String s_num=sc.nextLine();
			int n_num= Integer.parseInt(s_num);
			System.out.println("수정할 공지사항 제목을 입력하세요 >> ");
			String n_tt_update=sc.nextLine();
			System.out.println("수정할 공지사항 내용을 입력하세요 >> ");
			String n_con_update=sc.nextLine();
			
			
			String sql="update notice set n_title=?, n_content=? where n_number=? ";
			
			psmt=conn.prepareStatement(sql);
			
			psmt.setString(1, n_tt_update);
			psmt.setString(2, n_con_update);
			psmt.setInt(3, n_num);
			
			int cnt=psmt.executeUpdate();
			
			if(cnt>0) {
				System.out.println("내용을 수정하였습니다.");
			}else {
				System.out.println("수정이 취소되었습니다.");
			}
		} catch (Exception e) {
			System.out.println("수정 오류");
			e.printStackTrace();
		}
		
		finally {
			close();
		}
		
	}

	// 지점관리
	public void branch_select_view() {
		getConn();
		
		try {
			String sql="select * from MMG";
			
			psmt=conn.prepareStatement(sql);
			rs=psmt.executeQuery();
			
			System.out.print("회원번호 \t");
			System.out.print("아이디 \t");
			System.out.print("비밀번호 \t");
			System.out.println("지역코드");
			
			while(rs.next()) {
				System.out.print(rs.getString(1)+"\t");
				System.out.print(rs.getString(2)+"\t");
				System.out.print(rs.getString(3)+"\t");
				System.out.println(rs.getString(4));
			}
					
		} catch (Exception e) {
			System.out.println("지점 조회 실패");
			e.printStackTrace();
		}
		
		finally {
			close();
		}
		
		
		
		
	}
	
	public void branch_insert() {
		getConn();
		
		try {
			System.out.print("새로운 아이디 >> ");
			String insert_id=sc.next();
			System.out.print("비밀번호 >> ");
			String insert_pw=sc.next();
			System.out.print("지역코드 >> ");
			int insert_loc=sc.nextInt();
						
			
			String sql="insert into MMG values(m_number_seq.nextval, ?, ?,?)";
			
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, insert_id);
			psmt.setString(2, insert_pw);
			psmt.setInt(3, insert_loc);
			
			int cnt=psmt.executeUpdate();
			
			if(cnt>0) {
				System.out.println("추가를 완료했습니다.");
			}else {
				System.out.println("추가를 실패했습니다.");
			}
		} catch (Exception e) {
			System.out.println("지점 추가 실패");
			e.getStackTrace();
		}
		
		close();
	}

	public void branch_delete() {
		getConn();
		
		try {
			System.out.println("삭제할 지점번호를 선택하세요");
			int m_num=sc.nextInt();
			
			String sql="delete from MMG where m_number=?";
			
			psmt=conn.prepareStatement(sql);
			
			psmt.setInt(1, m_num);
			
			int cnt=psmt.executeUpdate();
			
			if(cnt>0) {
				System.out.println("삭제되었습니다.");
			}else {
				System.out.println("삭제가 취소되었습니다.");
			}
		} catch (Exception e) {
			System.out.println("삭제 오류");
			e.printStackTrace();
		}
		finally {
			close();
		}
		
	}
	
	public void branch_update() {
		getConn();
		
		try {
			System.out.println("수정할 지점번호를 선택하세요");
			int m_num_update=sc.nextInt();
			System.out.println("==수정==");
			System.out.println("수정할 지점명을 입력하세요");
			String m_name=sc.next();
			System.out.println("수정할 지역코드를 입력하세요");
			String m_location=sc.next();
			
			String sql="update MMG set m_id=? m_location=? where m_number=?";
			
			psmt=conn.prepareStatement(sql);
			
			int cnt=psmt.executeUpdate();
			
			if(cnt>0) {
				System.out.println("수정하였습니다.");
			}else {
				System.out.println("수정이 취소되었습니다.");
			}
						
		} catch (Exception e) {
			System.out.println("수정 오류");
			e.printStackTrace();
		}
		finally {
			close();
		}
	}

}
