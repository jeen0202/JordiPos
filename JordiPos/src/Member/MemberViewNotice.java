package Member;
import java.awt.Rectangle;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;


public class MemberViewNotice extends JPanel{
	  private static final long serialVersionUID = 1L;
	          private JTable table;   
	          private JScrollPane scrollPane;        // 테이블 스크롤바 자동으로 생성되게 하기
	          
	          private String driver = "oracle.jdbc.driver.OracleDriver";       
	          private String url = "jdbc:oracle:thin:@localhost:1521:xe";        
	          private String colNames[] = {"공지번호","제목","내용","게시날짜"};  // 테이블 컬럼 값들
	          private DefaultTableModel model = new DefaultTableModel(colNames, 0); //  테이블 데이터 모델 객체 생성      
	          private Connection con = null;
	          private PreparedStatement pstmt = null;
	          private ResultSet rs = null;   // 리턴받아 사용할 객체 생성 ( select에서 보여줄 때 필요 )
	   
	          public MemberViewNotice() {
	              setLayout(null);        // 레이아웃 배치관리자 삭제
	              table = new JTable(model);
	              table.getColumnModel().getColumn(0).setPreferredWidth(30);  
	              table.getColumnModel().getColumn(1).setPreferredWidth(30);
	              table.getColumnModel().getColumn(2).setPreferredWidth(30);
	              table.getColumnModel().getColumn(3).setPreferredWidth(30);
	              table.setRowHeight(30);
	             
	              table.setFont(new Font("맑은 고딕", Font.PLAIN,20));
	              table.addMouseListener(new JTableMouseListener());        // 테이블에 마우스리스너 연결
	              scrollPane = new JScrollPane(table);            // 테이블에 스크롤 생기게 하기
	              scrollPane.setLocation(14, 0);
	              scrollPane.setSize(767, 318);
	              scrollPane.setPreferredSize(new Dimension(1013,506));
	              add(scrollPane);
	              initialize();   
	              select();
	   
	          }
	   
	          private class JTableMouseListener implements MouseListener{    // 마우스로 눌려진값확인하기
	              public void mouseClicked(java.awt.event.MouseEvent e) {    // 선택된 위치의 값을 출력
	                   
	                  JTable jtable = (JTable)e.getSource();
	                  int row = jtable.getSelectedRow();                // 선택된 테이블의 행값
	                  int col = jtable.getSelectedColumn();         // 선택된 테이블의 열값
	               
	                  System.out.println(model.getValueAt(row, col));   // 선택된 위치의 값을 얻어내서 출력
	                       
	              }
	              public void mouseEntered(java.awt.event.MouseEvent e) {
	              }
	              public void mouseExited(java.awt.event.MouseEvent e) {   
	              }
	              public void mousePressed(java.awt.event.MouseEvent e) {
	              }
	              public void mouseReleased(java.awt.event.MouseEvent e) {
	              }
	          }
	                   
	          private void select(){        // 테이블에 보이기 위해 검색
	        	  
	              String query = "select* from notice order by n_date";    
	              try{
	                  Class.forName(driver);
	                  con = DriverManager.getConnection(url, "hr", "hr");
	                  pstmt = con.prepareStatement(query);
	                  rs = pstmt.executeQuery(); // 리턴받아와서 데이터를 사용할 객체생성
	                   
	                  while(rs.next()){            // 각각 값을 가져와서 테이블값들을 추가
	                      model.addRow(new Object[]{rs.getInt("n_number"),rs.getString("n_title"),rs.getNString("n_content"),rs.getDate("n_date")});
	                   
	                  }
	              }catch(Exception e){
	                  System.out.println(e.getMessage());
	              }finally{
	            	  try {
	          			if(rs!=null) {
	          				rs.close();
	          			}
	          			if(pstmt !=null) {
	          				pstmt.close();
	          			}
	          			if (con != null) {
	          				con.close();
	          			}
	          		} catch (Exception e) {
	          			e.printStackTrace();
	          		}
	              }
	          }
	       
	          private void initialize() {
	          };
	          
	           

	 /*    public static void main(String[] args) {
	           
	    	  MemberViewNotice panel = new MemberViewNotice();
	          JFrame win = new JFrame();
	           
	          win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	           
	          win.getContentPane().add(panel);
	         win.setSize(700,450);
	         
	          win.setVisible(true);
	      }
	      */
	     }
	  


