package Member;


import java.awt.Rectangle;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;


public class MemberSalesSell extends JPanel{
	  private static final long serialVersionUID = 1L;	         
	          private JTable table;   
	          private JScrollPane scrollPane;        // 테이블 스크롤바 자동으로 생성되게 하기
	          
	          private String driver = "oracle.jdbc.driver.OracleDriver";       
	          private String url = "jdbc:oracle:thin:@localhost:1521:xe";        
	          private String colNames[] = {"상품번호","상품명","가격","판매량","잔여량"};  // 테이블 컬럼 값들
	          private DefaultTableModel model = new DefaultTableModel(colNames, 0); //  테이블 데이터 모델 객체 생성      
	          private Connection con = null;
	          private PreparedStatement pstmt = null;
	          private ResultSet rs = null;   
	          int p_code=0;
	          int rownum=0;
	          String tp_n1 =null;
	          int tp_c1=0;
	  			int tp_q1=0;
	  			int tp_c2=0;
	  			String tp_n2 =null;
	  			int tp_q2=0;
	  			int tp_c3=0;
	  			String tp_n3 =null;
	  			int tp_q3=0;
	  			
	          private JTextField tfP_name;
	          private JTextField tfP_count;
	   
	          public MemberSalesSell() {
	              setLayout(null);        // 레이아웃 배치관리자 삭제
	              table = new JTable(model);
	              table.getColumnModel().getColumn(0).setPreferredWidth(45);  
	              table.getColumnModel().getColumn(1).setPreferredWidth(160);
	              table.getColumnModel().getColumn(2).setPreferredWidth(50);
	              table.getColumnModel().getColumn(3).setPreferredWidth(30);	  
	              table.setRowHeight(25);              
	       

	              DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();
	              tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);         
	              TableColumnModel tcmSchedule = table.getColumnModel();
	              tcmSchedule.getColumn(0).setCellRenderer(tScheduleCellRenderer);
	              tcmSchedule.getColumn(2).setCellRenderer(tScheduleCellRenderer);
	              tcmSchedule.getColumn(3).setCellRenderer(tScheduleCellRenderer);
	
	             
	              table.setFont(new Font("맑은 고딕", Font.PLAIN,18));
	              table.addMouseListener(new JTableMouseListener());        // 테이블에 마우스리스너 연결
	              scrollPane = new JScrollPane(table);            // 테이블에 스크롤 생기게 하기
	              scrollPane.setLocation(0, 0);
	              scrollPane.setSize(606, 577);
	              scrollPane.setPreferredSize(new Dimension(1030,506));
	              add(scrollPane);
	              
	              JLabel lblPname = new JLabel("\uC0C1\uD488\uBA85 :");
	              lblPname.setHorizontalAlignment(SwingConstants.TRAILING);
	              lblPname.setFont(new Font("맑은 고딕", Font.BOLD, 25));
	              lblPname.setBounds(642, 105, 146, 44);
	              add(lblPname);
	              
	              JLabel lblPcount = new JLabel("\uD310\uB9E4 \uAC1C\uC218 :");
	              lblPcount.setHorizontalAlignment(SwingConstants.TRAILING);
	              lblPcount.setFont(new Font("맑은 고딕", Font.BOLD, 25));
	              lblPcount.setBounds(642, 219, 146, 44);
	              add(lblPcount);
	              
	              tfP_name = new JTextField();
	              tfP_name.setFont(new Font("맑은 고딕", Font.BOLD, 25));
	              tfP_name.setBounds(802, 105, 207, 44);
	              add(tfP_name);
	              tfP_name.setColumns(10);
	              
	              tfP_count = new JTextField();
	              tfP_count.setFont(new Font("맑은 고딕", Font.BOLD, 25));
	              tfP_count.setColumns(10);
	              tfP_count.setBounds(802, 219, 207, 44);
	              add(tfP_count);
	              
	              JButton btnSell = new JButton("\uD310\uB9E4");
	              btnSell.addMouseListener(new MouseAdapter() {
	              	@Override
	              	public void mouseClicked(MouseEvent e) {
	              		String p_name = tfP_name.getText();
	              		String s_count = tfP_count.getText();
	              		int count = Integer.parseInt(s_count);	 
	              		
	              		int sum = 0;
	              		int p_price =0;
	              		int d_sale = 0;
	              		int m_sale = 0;
	              		int r_quantity = 0;
	              		int d_sq = 0;
	              		
	              		String query = "select p_price from PMG where p_name = ?";
	                      try{
	                          Class.forName(driver);  // 드라이버 로딩
	                          con = DriverManager.getConnection(url, "hr", "hr"); // DB 연결
	                         	
	                          pstmt = con.prepareStatement(query.toString());  	  
	                          pstmt.setString(1, p_name.toString());
	                          rs = pstmt.executeQuery();
	                          while(rs.next()) {
	              				p_price = rs.getInt(1);
	                          }
	                         // System.out.println(p_price);
	                          sum = (p_price * count);	                          
	                          //System.out.println(sum);
	                          
	                          query = "select d_sale, m_sale from SMG where m_number = 101";
	                          pstmt = con.prepareStatement(query.toString()); 
	                          rs = pstmt.executeQuery();
	                          while(rs.next()) {
		              				d_sale= rs.getInt(1);
		              				m_sale= rs.getInt(2);
	                          }
	                          query = "update SMG set d_sale=?, m_sale=? where m_number=101";
	                          pstmt = con.prepareStatement(query.toString());
	                          pstmt.setInt(1, d_sale+ sum);
	              			  pstmt.setInt(2, m_sale + sum);
	              			  int cnt = pstmt.executeUpdate();
	              			  
	              			query = "select r_quantity from PMG where p_name = ?";
	           			 pstmt = con.prepareStatement(query);
	           			 pstmt.setString(1, p_name); 
	           			 
	           				rs = pstmt.executeQuery();
	           	
	           				while (rs.next()) {
	           					r_quantity = rs.getInt(1);		 

	           				}
	           			 int s_quan = r_quantity-count;
	           			// System.out.println(s_quan);
	           			 
	           			query = "update PMG set r_quantity=? where p_name = ?";
	           			pstmt = con.prepareStatement(query);
	           			pstmt.setInt(1, s_quan);
	           			pstmt.setString(2, p_name);
	           			cnt = pstmt.executeUpdate();
	           			
	           			query = "select d_sq from PMG where p_name = ?";
	       			 pstmt = con.prepareStatement(query);
	       			 pstmt.setString(1, p_name); 
	       			 
	       				rs = pstmt.executeQuery();
	       	
	       				while (rs.next()) {
	       					d_sq = rs.getInt(1);		 
	       				}
	       			 
	       			query = "update PMG set d_sq=? where p_name = ?";
	       			pstmt = con.prepareStatement(query);
	       			pstmt.setInt(1,d_sq+count);
	       			pstmt.setString(2, p_name);    	
	       			       			
	       			
	       			cnt = pstmt.executeUpdate();
	           		
	           		
	       			query = "select rownum, p_code,d_sq from (select p_code,d_sq from PMG order by d_sq desc) where rownum<4 ";
	    			
	    			pstmt = con.prepareStatement(query);
	    			rs = pstmt.executeQuery();
	    			int i=0;
	    			int arr[][] = new int [3][3];
	    			while (rs.next()) {				
	    				rownum = rs.getInt(1);
	    				p_code = rs.getInt(2);
	    				d_sq = rs.getInt(3);	
	    				arr[0][i] = rownum;
	    				arr[1][i] = p_code;
	    				arr[2][i] = d_sq;

	    			//	System.out.print(arr[0][i] +" ");
	    			//	System.out.print(arr[1][i]+ " ");
	    			//	System.out.println(arr[2][i]+ " ");
	    				i++;
	    			}	    			
	    			
	    			
	    			
	    			
	    			/*query ="select tp_n1,tp_q1,tp_n2,tp_q2,tp_n3,tp_q3 from TPM ";
	    			pstmt = con.prepareStatement(query);
	    			
	    			
	    			rs = pstmt.executeQuery();
	    			
	    			while (rs.next()) {
	    				tp_n1 = rs.getString(1);
	    				tp_q1 = rs.getInt(2);
	    				tp_n2 = rs.getString(3);
	    				tp_q2 = rs.getInt(4);
	    				tp_n3 = rs.getString(5);
	    				tp_q3 = rs.getInt(6);
	    				
	    			}	*/
	    			tp_c1=arr[1][0];
	    			tp_q1=arr[2][0];
	    			tp_c2=arr[1][1];
	    			tp_q2=arr[2][1];
	    			tp_c3=arr[1][2];
	    			tp_q3=arr[2][2];

	    			query = "select p_name from PMG where p_code = ?";
	    			pstmt = con.prepareStatement(query);
	    			pstmt.setInt(1, tp_c1);
	    			rs= pstmt.executeQuery();
	    			while(rs.next())
	    			tp_n1 = rs.getString(1);
	    			//System.out.println(tp_n1);
	    			
	    			query = "select p_name from PMG where p_code = ?";
	    			pstmt = con.prepareStatement(query);
	    			pstmt.setInt(1, tp_c2);
	    			rs= pstmt.executeQuery();
	    			while(rs.next())	    			
	    			tp_n2 = rs.getString(1);
	    			//System.out.println(tp_n2);
	    			
	    			query = "select p_name from PMG where p_code = ?";
	    			pstmt = con.prepareStatement(query);
	    			pstmt.setInt(1, tp_c3);	    			
	    			rs= pstmt.executeQuery();
	    			while(rs.next())
	    			tp_n3 = rs.getString(1);
	    			//System.out.println(tp_n3);
	    			
	    			query = "update TPM set tp_n1=?,tp_q1=?,tp_n2=?,tp_q2=?,tp_n3=?,tp_q3=?";
	    			
	    			pstmt = con.prepareStatement(query);
	    			pstmt.setString(1,tp_n1);
	    			pstmt.setInt(2,tp_q1);
	    			pstmt.setString(3,tp_n2);
	    			pstmt.setInt(4,tp_q2);
	    			pstmt.setString(5,tp_n3);
	    			pstmt.setInt(6,tp_q3);
	    				
	    			
	    			
	    			cnt = pstmt.executeUpdate();
	    			
	                      }catch(Exception eeee){
	                    	 eeee.printStackTrace();
	                      }finally{
	                          try {
	                              pstmt.close();
	                              con.close();
	                          } catch (Exception e2) {}
	                      }
	                      model.setRowCount(0);
	                      select();
	              	}
	              });
	              btnSell.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
	              btnSell.setBounds(620, 366, 217, 82);
	              add(btnSell);
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
	        	  
	              String query = "select p_code,p_name,p_price,d_sq,r_quantity from PMG";    
	              try{
	                  Class.forName(driver);
	                  con = DriverManager.getConnection(url, "hr", "hr");
	                  pstmt = con.prepareStatement(query);
	                  rs = pstmt.executeQuery(); // 리턴받아와서 데이터를 사용할 객체생성
	                   
	                  while(rs.next()){            // 각각 값을 가져와서 테이블값들을 추가
	                      model.addRow(new Object[]{rs.getString("p_code"),rs.getString("p_name"),
	                                                              rs.getString("p_price"),rs.getNString("d_sq"),rs.getString("r_quantity")});
	                      
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
	       
	          private void initialize() {          // 액션이벤트와 버튼 컴포넌트 설정
	        	 	         	          }
	           
	          
	      public static void main(String[] args) {
	           
	    	  MemberSalesSell panel = new MemberSalesSell();
	          JFrame win = new JFrame();
	           
	          win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	           
	          win.getContentPane().add(panel);
	         win.setSize(1100,600);
	         
	          win.setVisible(true);
	 
	     } 
	  }


