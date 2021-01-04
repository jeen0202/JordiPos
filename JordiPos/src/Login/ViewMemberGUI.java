package Login;
import java.awt.Rectangle;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;


public class ViewMemberGUI extends JPanel{
	  private static final long serialVersionUID = 1L;
	          private JButton jBtnAddRow = null;    // 테이블 한줄 추가 버튼
	          private JButton jBtnAddROw;
	          private JButton jBtnSaveRow = null;    // 테이블 한줄 저장 버튼
	          private JButton jBtnEditRow = null;    // 테이블 한줄 저장 버튼
	          private JButton jBtnDelRow = null;        // 테이블 한줄 삭제 벅튼
	          private JTable table;   
	          private JScrollPane scrollPane;        // 테이블 스크롤바 자동으로 생성되게 하기
	          
	          private String driver = "oracle.jdbc.driver.OracleDriver";       
	          private String url = "jdbc:oracle:thin:@localhost:1521:xe";        
	          private String colNames[] = {"회원번호","아이디","비밀번호","지역번호","1순위","판매량","2순위","판매량","3순위","판매량"};  // 테이블 컬럼 값들
	          private DefaultTableModel model = new DefaultTableModel(colNames, 0); //  테이블 데이터 모델 객체 생성      
	          private Connection con = null;
	          private PreparedStatement pstmt = null;
	          private ResultSet rs = null;   // 리턴받아 사용할 객체 생성 ( select에서 보여줄 때 필요 )
	   
	          public ViewMemberGUI() {
	              setLayout(null);        // 레이아웃 배치관리자 삭제
	              table = new JTable(model);
	  /*           table.getColumnModel().getColumn(0).setPreferredWidth(5);  
	              table.getColumnModel().getColumn(1).setPreferredWidth(30);
	              table.getColumnModel().getColumn(2).setPreferredWidth(20);
	              table.getColumnModel().getColumn(3).setPreferredWidth(20);
	              table.getColumnModel().getColumn(4).setPreferredWidth(50);  
	              table.getColumnModel().getColumn(5).setPreferredWidth(10);
	              table.getColumnModel().getColumn(6).setPreferredWidth(50);
	              table.getColumnModel().getColumn(7).setPreferredWidth(10);
	              table.getColumnModel().getColumn(8).setPreferredWidth(50);
	              table.getColumnModel().getColumn(9).setPreferredWidth(10);
	           */   table.setRowHeight(20);
	             
	              table.setFont(new Font("맑은 고딕", Font.PLAIN,18));
	              table.addMouseListener(new JTableMouseListener());        // 테이블에 마우스리스너 연결
	              scrollPane = new JScrollPane(table);            // 테이블에 스크롤 생기게 하기
	              scrollPane.setLocation(19, 0);
	              scrollPane.setSize(1101, 158);
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
	        	   ManagerDAO dao = new ManagerDAO();
	              String query = "select m.m_number,m.m_id,m.m_pw,m.m_loc,t.tp_n1,t.tp_q1,t.tp_n2,t.tp_q2,t.tp_n3,t.tp_q3 from MMG m,TPM t where m.m_number = t.m_number(+)"; 
	              		   
	              try{
	                  Class.forName(driver);
	                  con = DriverManager.getConnection(url, "hr", "hr");
	                  pstmt = con.prepareStatement(query);
	                  rs = pstmt.executeQuery(); // 리턴받아와서 데이터를 사용할 객체생성
	                   
	                  while(rs.next()){            // 각각 값을 가져와서 테이블값들을 추가
	                      model.addRow(new Object[]{rs.getInt("m_number"),rs.getString("m_id"),rs.getString("m_pw"),rs.getInt("m_loc"),
	                    		  rs.getString("tp_n1"),rs.getInt("tp_q1"),rs.getString("tp_n2"),rs.getInt("tp_q2"),rs.getString("tp_n3"),rs.getInt("tp_q3")});
	                   
	                  }
	              }catch(Exception e){
	            	  e.printStackTrace();
	              }finally{
	                  	dao.close();
	              }
	          }
	       
	          private void initialize() {            // 액션이벤트와 버튼 컴포넌트 설정
	               
	          // 테이블 새로 한줄 추가하는 부분
	              jBtnAddROw = new JButton();
	              jBtnAddROw.addActionListener(new ActionListener() {   
	                  public void actionPerformed(ActionEvent e) {
	                      System.out.println(e.getActionCommand());        // 선택된 버튼의 텍스트값 출력
	                      DefaultTableModel model2 = (DefaultTableModel)table.getModel();
	                      model2.addRow(new String[]{"","","","","","","","","",""});            // 새테이블의 초기값
	                  }
	              });
	              jBtnAddROw.setBounds(19,188,228, 61);
	              jBtnAddROw.setText("\uD68C\uC6D0 \uCD94\uAC00");
	              add(jBtnAddROw);
	               
	   
	          // 테이블 새로 저장하는 부분
	              jBtnSaveRow = new JButton();
	              jBtnSaveRow.addActionListener(new ActionListener() {
	                  public void actionPerformed(ActionEvent e) {
	                      System.out.println(e.getActionCommand());        // 선택된 버튼의 텍스트값 출력
	                      DefaultTableModel model2 = (DefaultTableModel)table.getModel();
	                      int row = table.getSelectedRow();
	                      if(row<0) return;     // 선택이 안된 상태면 -1리턴
	                      String query = "insert into MMG values(m_number_seq.nextval,?,?,?)";
	                       
	   
	                      try{	                    	 
	                          Class.forName(driver);  // 드라이버 로딩
	                          con = DriverManager.getConnection(url, "hr", "hr"); // DB 연결
	                          pstmt = con.prepareStatement(query);  
	                           
	                          // 물음표가 4개 이므로 4개 각각 입력해줘야한다.	                        
	                          pstmt.setString(1, model2.getValueAt(row, 1).toString());
	                          pstmt.setString(2, model2.getValueAt(row, 2).toString());
	                          pstmt.setString(3, model2.getValueAt(row, 3).toString());
	   
	                        int cnt = pstmt.executeUpdate();
	                          //pstmt.executeUpdate(); create insert update delete
	                          //pstmt.executeQuery(); select
	                      }catch(Exception eeee){
	                    	  eeee.printStackTrace();
	                      }finally{
	                          try {
	                              pstmt.close();
	                              con.close();
	                          } catch (Exception e2) {}
	                      }
	                      model2.setRowCount(0);         // 전체 테이블 화면을 지워줌
	                      select();          // 저장 후 다시 전체 값들을 받아옴.
	                  }
	              });
	              jBtnSaveRow.setBounds(343,188,222, 61);
	              jBtnSaveRow.setText("\uD68C\uC6D0 \uC815\uBCF4 \uC800\uC7A5");
	              add(jBtnSaveRow);
	               
	               
	              // 선택된 테이블 한줄 수정하는 부분
	              jBtnEditRow = new JButton();
	              
	              jBtnEditRow.addActionListener(new ActionListener() {
	                  public void actionPerformed(ActionEvent e) {       
	                               
	                      System.out.println(e.getActionCommand());  
	                      DefaultTableModel model2 = (DefaultTableModel)table.getModel();
	                      int row = table.getSelectedRow();
	                      if(row<0)
	                    	  return;         	                                                           
	                       
	                      String query = "UPDATE MMG SET m_id=?, m_pw=?, m_loc=? where m_number = ?";
	                      try{
	                          Class.forName(driver);  // 드라이버 로딩
	                          con = DriverManager.getConnection(url, "hr", "hr"); // DB 연결
	                         	
	                          pstmt = con.prepareStatement(query.toString());  	                          
	                    //      int m_loc=Integer.parseInt(model2.getValueAt(row,3)+"");
	                       //   int m_number=Integer.parseInt(model2.getValueAt(row,4)+"");
	                         
	                        
	                          pstmt.setString(1, model2.getValueAt(row,1).toString());
	                          pstmt.setString(2, model2.getValueAt(row, 2).toString());
	                          pstmt.setString(3, model2.getValueAt(row, 3).toString());	                       
	                          pstmt.setString(4, model2.getValueAt(row, 0).toString());
	   
	                         int cnt = pstmt.executeUpdate();
	                         if (cnt == 1) {
	                             System.out.println("회원정보 수정에 성공하였습니다.");
	              
	                         } else {
	                             System.out.println("회원정보 수정에 실패하였습니다.");
	                         }
	                      }catch(Exception eeee){
	                    	 eeee.printStackTrace();
	                      }finally{
	                          try {
	                              pstmt.close();
	                              con.close();
	                          } catch (Exception e2) {}
	                      }
	                      model2.setRowCount(0);         
	                      select();         
	                  }
	              });	              
	              jBtnEditRow.setBounds(19,294,228, 61);
	              jBtnEditRow.setText("\uD68C\uC6D0 \uC815\uBCF4 \uBCC0\uACBD");
	              add(jBtnEditRow);
	          
	               
	          // 선택된 테이블 한줄 삭제하는 부분
	              jBtnDelRow = new JButton();
	              
	              jBtnDelRow.addActionListener(new ActionListener() {
	                  public void actionPerformed(java.awt.event.ActionEvent e) {
	                      System.out.println(e.getActionCommand());        // 선택된 버튼의 텍스트값 출력
	                      DefaultTableModel model2 = (DefaultTableModel)table.getModel();
	                      int row = table.getSelectedRow();	                      
	                      if(row<0) return; // 선택이 안된 상태면 -1리턴
	                      String query = "delete from MMG where m_number=?";	                 
	                      try{
	                          Class.forName(driver);  // 드라이버 로딩
	                          con = DriverManager.getConnection(url, "hr", "hr"); // DB 연결
	                          pstmt = con.prepareStatement(query);  
	                           
	                          pstmt.setString(1, model2.getValueAt(row, 0).toString());	                         
	                          int cnt = pstmt.executeUpdate();
	                          if(cnt>0) {
	              				System.out.println("삭제되었습니다.");
	              			}else {
	              				System.out.println("삭제가 취소되었습니다.");
	              			}
	                    
	                      }catch(Exception eeee){
	                    	  eeee.printStackTrace();
	                      }finally{
	                          try {
	                              pstmt.close();
	                              con.close();
	                          } catch (Exception e2) {}
	                      }
	                      model2.removeRow(row);    // 테이블 상의 한줄 삭제
	                  }
	              });
	              
	              jBtnDelRow.setBounds(new Rectangle(343, 296, 222, 56));
	              jBtnDelRow.setText("삭제");
	              add(jBtnDelRow);
	          };
	          
	           

	   /*  public static void main(String[] args) {
	           
	    	  ViewMemberGUI panel = new ViewMemberGUI();
	          JFrame win = new JFrame();
	           
	          win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	           
	          win.getContentPane().add(panel);
	         win.setSize(580,450);
	         
	          win.setVisible(true);
	      }
	     */
	     }
	  


