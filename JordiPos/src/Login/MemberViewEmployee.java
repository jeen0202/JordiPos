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

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;


public class MemberViewEmployee extends JPanel{
	  private static final long serialVersionUID = 1L;
	          private JButton jBtnAddRow = null;    // ���̺� ���� �߰� ��ư
	          private JButton jBtnAddROw;
	          private JButton jBtnSaveRow = null;    // ���̺� ���� ���� ��ư
	          private JButton jBtnEditRow = null;    // ���̺� ���� ���� ��ư
	          private JButton jBtnDelRow = null;        // ���̺� ���� ���� ��ư
	          private JTable table;   
	          private JScrollPane scrollPane;        // ���̺� ��ũ�ѹ� �ڵ����� �����ǰ� �ϱ�
	          
	          private String driver = "oracle.jdbc.driver.OracleDriver";       
	          private String url = "jdbc:oracle:thin:@localhost:1521:xe";        
	          private String colNames[] = {"������ȣ","�̸�","����"};  // ���̺� �÷� ����
	          private DefaultTableModel model = new DefaultTableModel(colNames, 0); //  ���̺� ������ �� ��ü ����      
	          private Connection con = null;
	          private PreparedStatement pstmt = null;
	          private ResultSet rs = null;   // ���Ϲ޾� ����� ��ü ���� ( select���� ������ �� �ʿ� )
	   
	          public MemberViewEmployee() {
	              setLayout(null);        // ���̾ƿ� ��ġ������ ����
	              table = new JTable(model);
	              table.getColumnModel().getColumn(0).setPreferredWidth(30);  
	              table.getColumnModel().getColumn(1).setPreferredWidth(30);
	              table.getColumnModel().getColumn(2).setPreferredWidth(30);
	              table.setRowHeight(20);
	             
	              table.setFont(new Font("���� ���", Font.PLAIN,18));
	              table.addMouseListener(new JTableMouseListener());        // ���̺� ���콺������ ����
	              scrollPane = new JScrollPane(table);            // ���̺� ��ũ�� ����� �ϱ�
	              scrollPane.setLocation(19, 0);
	              scrollPane.setSize(584, 241);
	              scrollPane.setPreferredSize(new Dimension(1013,506));
	              add(scrollPane);
	              initialize();   
	              select();
	   
	          }
	   
	          private class JTableMouseListener implements MouseListener{    // ���콺�� ��������Ȯ���ϱ�
	              public void mouseClicked(java.awt.event.MouseEvent e) {    // ���õ� ��ġ�� ���� ���
	                   
	                  JTable jtable = (JTable)e.getSource();
	                  int row = jtable.getSelectedRow();                // ���õ� ���̺��� �ప
	                  int col = jtable.getSelectedColumn();         // ���õ� ���̺��� ����
	               
	                  System.out.println(model.getValueAt(row, col));   // ���õ� ��ġ�� ���� ���� ���
	                       
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
	                   
	          private void select(){        // ���̺� ���̱� ���� �˻�	        	   
	              String query = "select * from EMG";    
	              try{
	                  Class.forName(driver);
	                  con = DriverManager.getConnection(url, "hr", "hr");
	                  pstmt = con.prepareStatement(query);
	                  rs = pstmt.executeQuery(); // ���Ϲ޾ƿͼ� �����͸� ����� ��ü����
	                   
	                  while(rs.next()){            // ���� ���� �����ͼ� ���̺����� �߰�
	                      model.addRow(new Object[]{rs.getInt("e_number"),rs.getString("e_name"),rs.getString("e_commuting")});
	                   
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
	            	  }catch (Exception e) {
		          			e.printStackTrace();
		          		}
	              }
	          }
	          
	          private void initialize() {            // �׼��̺�Ʈ�� ��ư ������Ʈ ����
	               
	          // ���̺� ���� ���� �߰��ϴ� �κ�
	              jBtnAddROw = new JButton();
	              jBtnAddROw.addActionListener(new ActionListener() {   
	                  public void actionPerformed(ActionEvent e) {
	                      System.out.println(e.getActionCommand());        // ���õ� ��ư�� �ؽ�Ʈ�� ���
	                      DefaultTableModel model2 = (DefaultTableModel)table.getModel();
	                      model2.addRow(new String[]{"","",""});            // �����̺��� �ʱⰪ
	                  }
	              });
	              jBtnAddROw.setBounds(19,253,254, 91);
	              jBtnAddROw.setText("\uC9C1\uC6D0 \uCD94\uAC00");
	              add(jBtnAddROw);
	               
	   
	          // ���̺� ���� �����ϴ� �κ�
	              jBtnSaveRow = new JButton();
	              jBtnSaveRow.addActionListener(new ActionListener() {
	                  public void actionPerformed(ActionEvent e) {
	                      System.out.println(e.getActionCommand());        // ���õ� ��ư�� �ؽ�Ʈ�� ���
	                      DefaultTableModel model2 = (DefaultTableModel)table.getModel();
	                      int row = table.getSelectedRow();
	                      if(row<0) return;     // ������ �ȵ� ���¸� -1����
	                      String query = "insert into EMG values(e_number_seq.nextval,?,?)";
	                       
	   
	                      try{	                    	 
	                          Class.forName(driver);  // ����̹� �ε�
	                          con = DriverManager.getConnection(url, "hr", "hr"); // DB ����
	                          pstmt = con.prepareStatement(query);  
	                           
	                          // ����ǥ�� 4�� �̹Ƿ� 4�� ���� �Է�������Ѵ�.	                        
	                          pstmt.setString(1, model2.getValueAt(row, 1).toString());
	                          pstmt.setString(2, model2.getValueAt(row, 2).toString());
	   
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
	                      model2.setRowCount(0);         // ��ü ���̺� ȭ���� ������
	                      select();          // ���� �� �ٽ� ��ü ������ �޾ƿ�.
	                  }
	              });
	              jBtnSaveRow.setBounds(349,253,254, 91);
	              jBtnSaveRow.setText("\uC0C8 \uC9C1\uC6D0 \uC800\uC7A5");
	              add(jBtnSaveRow);
	               
	               
	              // ���õ� ���̺� ���� �����ϴ� �κ�
	              jBtnEditRow = new JButton();
	              
	              jBtnEditRow.addActionListener(new ActionListener() {
	                  public void actionPerformed(ActionEvent e) {       
	                               
	                      System.out.println(e.getActionCommand());  
	                      DefaultTableModel model2 = (DefaultTableModel)table.getModel();
	                      int row = table.getSelectedRow();
	                      if(row<0)
	                    	  return;         	                                                           
	                       
	                      String query = "UPDATE EMG SET e_name=?, e_commuting=? where e_number = ?";
	                      try{
	                          Class.forName(driver);  // ����̹� �ε�
	                          con = DriverManager.getConnection(url, "hr", "hr"); // DB ����
	                         	
	                          pstmt = con.prepareStatement(query.toString());  	                          
	                    //      int m_loc=Integer.parseInt(model2.getValueAt(row,3)+"");
	                       //   int m_number=Integer.parseInt(model2.getValueAt(row,4)+"");
	                         
	                        
	                          pstmt.setString(1, model2.getValueAt(row,1).toString());
	                          pstmt.setString(2, model2.getValueAt(row, 2).toString());
	                          pstmt.setString(3, model2.getValueAt(row, 0).toString());
	   
	                         int cnt = pstmt.executeUpdate();
	                         if (cnt == 1) {
	                             System.out.println("�������� ������ �����Ͽ����ϴ�.");
	              
	                         } else {
	                             System.out.println("�������� ������ �����Ͽ����ϴ�.");
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
	              jBtnEditRow.setBounds(19,399,254, 91);
	              jBtnEditRow.setText("\uC9C1\uC6D0 \uC815\uBCF4 \uBCC0\uACBD");
	              add(jBtnEditRow);
	          
	               
	          // ���õ� ���̺� ���� �����ϴ� �κ�
	              jBtnDelRow = new JButton();
	              
	              jBtnDelRow.addActionListener(new ActionListener() {
	                  public void actionPerformed(java.awt.event.ActionEvent e) {
	                      System.out.println(e.getActionCommand());        // ���õ� ��ư�� �ؽ�Ʈ�� ���
	                      DefaultTableModel model2 = (DefaultTableModel)table.getModel();
	                      int row = table.getSelectedRow();	                      
	                      if(row<0) return; // ������ �ȵ� ���¸� -1����
	                      String query = "delete from EMG where e_number=?";	                 
	                      try{
	                          Class.forName(driver);  // ����̹� �ε�
	                          con = DriverManager.getConnection(url, "hr", "hr"); // DB ����
	                          pstmt = con.prepareStatement(query);  
	                           
	                          pstmt.setString(1, model2.getValueAt(row, 0).toString());	                         
	                          int cnt = pstmt.executeUpdate();
	                          if(cnt>0) {
	              				System.out.println("�����Ǿ����ϴ�.");
	              			}else {
	              				System.out.println("������ ��ҵǾ����ϴ�.");
	              			}
	                    
	                      }catch(Exception eeee){
	                    	  eeee.printStackTrace();
	                      }finally{
	                          try {
	                              pstmt.close();
	                              con.close();
	                          } catch (Exception e2) {}
	                      }
	                      model2.removeRow(row);    // ���̺� ���� ���� ����
	                  }
	              });
	              
	              jBtnDelRow.setBounds(new Rectangle(349, 400, 254, 88));
	              jBtnDelRow.setText("\uC9C1\uC6D0 \uC0AD\uC81C");
	              add(jBtnDelRow);
	          };
	          
	           

	     public static void main(String[] args) {
	           
	    	  MemberViewEmployee panel = new MemberViewEmployee();
	          JFrame win = new JFrame();
	           
	          win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	           
	          win.getContentPane().add(panel);
	         win.setSize(580,450);
	         
	          win.setVisible(true);
	      }
	     }
	  


