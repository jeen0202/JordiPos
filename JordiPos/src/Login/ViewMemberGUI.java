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
	          private JButton jBtnAddRow = null;    // ���̺� ���� �߰� ��ư
	          private JButton jBtnAddROw;
	          private JButton jBtnSaveRow = null;    // ���̺� ���� ���� ��ư
	          private JButton jBtnEditRow = null;    // ���̺� ���� ���� ��ư
	          private JButton jBtnDelRow = null;        // ���̺� ���� ���� ��ư
	          private JTable table;   
	          private JScrollPane scrollPane;        // ���̺� ��ũ�ѹ� �ڵ����� �����ǰ� �ϱ�
	          
	          private String driver = "oracle.jdbc.driver.OracleDriver";       
	          private String url = "jdbc:oracle:thin:@localhost:1521:xe";        
	          private String colNames[] = {"ȸ����ȣ","���̵�","��й�ȣ","������ȣ","1����","�Ǹŷ�","2����","�Ǹŷ�","3����","�Ǹŷ�"};  // ���̺� �÷� ����
	          private DefaultTableModel model = new DefaultTableModel(colNames, 0); //  ���̺� ������ �� ��ü ����      
	          private Connection con = null;
	          private PreparedStatement pstmt = null;
	          private ResultSet rs = null;   // ���Ϲ޾� ����� ��ü ���� ( select���� ������ �� �ʿ� )
	   
	          public ViewMemberGUI() {
	              setLayout(null);        // ���̾ƿ� ��ġ������ ����
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
	             
	              table.setFont(new Font("���� ���", Font.PLAIN,18));
	              table.addMouseListener(new JTableMouseListener());        // ���̺� ���콺������ ����
	              scrollPane = new JScrollPane(table);            // ���̺� ��ũ�� ����� �ϱ�
	              scrollPane.setLocation(19, 0);
	              scrollPane.setSize(1101, 158);
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
	        	   ManagerDAO dao = new ManagerDAO();
	              String query = "select m.m_number,m.m_id,m.m_pw,m.m_loc,t.tp_n1,t.tp_q1,t.tp_n2,t.tp_q2,t.tp_n3,t.tp_q3 from MMG m,TPM t where m.m_number = t.m_number(+)"; 
	              		   
	              try{
	                  Class.forName(driver);
	                  con = DriverManager.getConnection(url, "hr", "hr");
	                  pstmt = con.prepareStatement(query);
	                  rs = pstmt.executeQuery(); // ���Ϲ޾ƿͼ� �����͸� ����� ��ü����
	                   
	                  while(rs.next()){            // ���� ���� �����ͼ� ���̺����� �߰�
	                      model.addRow(new Object[]{rs.getInt("m_number"),rs.getString("m_id"),rs.getString("m_pw"),rs.getInt("m_loc"),
	                    		  rs.getString("tp_n1"),rs.getInt("tp_q1"),rs.getString("tp_n2"),rs.getInt("tp_q2"),rs.getString("tp_n3"),rs.getInt("tp_q3")});
	                   
	                  }
	              }catch(Exception e){
	            	  e.printStackTrace();
	              }finally{
	                  	dao.close();
	              }
	          }
	       
	          private void initialize() {            // �׼��̺�Ʈ�� ��ư ������Ʈ ����
	               
	          // ���̺� ���� ���� �߰��ϴ� �κ�
	              jBtnAddROw = new JButton();
	              jBtnAddROw.addActionListener(new ActionListener() {   
	                  public void actionPerformed(ActionEvent e) {
	                      System.out.println(e.getActionCommand());        // ���õ� ��ư�� �ؽ�Ʈ�� ���
	                      DefaultTableModel model2 = (DefaultTableModel)table.getModel();
	                      model2.addRow(new String[]{"","","","","","","","","",""});            // �����̺��� �ʱⰪ
	                  }
	              });
	              jBtnAddROw.setBounds(19,188,228, 61);
	              jBtnAddROw.setText("\uD68C\uC6D0 \uCD94\uAC00");
	              add(jBtnAddROw);
	               
	   
	          // ���̺� ���� �����ϴ� �κ�
	              jBtnSaveRow = new JButton();
	              jBtnSaveRow.addActionListener(new ActionListener() {
	                  public void actionPerformed(ActionEvent e) {
	                      System.out.println(e.getActionCommand());        // ���õ� ��ư�� �ؽ�Ʈ�� ���
	                      DefaultTableModel model2 = (DefaultTableModel)table.getModel();
	                      int row = table.getSelectedRow();
	                      if(row<0) return;     // ������ �ȵ� ���¸� -1����
	                      String query = "insert into MMG values(m_number_seq.nextval,?,?,?)";
	                       
	   
	                      try{	                    	 
	                          Class.forName(driver);  // ����̹� �ε�
	                          con = DriverManager.getConnection(url, "hr", "hr"); // DB ����
	                          pstmt = con.prepareStatement(query);  
	                           
	                          // ����ǥ�� 4�� �̹Ƿ� 4�� ���� �Է�������Ѵ�.	                        
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
	                      model2.setRowCount(0);         // ��ü ���̺� ȭ���� ������
	                      select();          // ���� �� �ٽ� ��ü ������ �޾ƿ�.
	                  }
	              });
	              jBtnSaveRow.setBounds(343,188,222, 61);
	              jBtnSaveRow.setText("\uD68C\uC6D0 \uC815\uBCF4 \uC800\uC7A5");
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
	                       
	                      String query = "UPDATE MMG SET m_id=?, m_pw=?, m_loc=? where m_number = ?";
	                      try{
	                          Class.forName(driver);  // ����̹� �ε�
	                          con = DriverManager.getConnection(url, "hr", "hr"); // DB ����
	                         	
	                          pstmt = con.prepareStatement(query.toString());  	                          
	                    //      int m_loc=Integer.parseInt(model2.getValueAt(row,3)+"");
	                       //   int m_number=Integer.parseInt(model2.getValueAt(row,4)+"");
	                         
	                        
	                          pstmt.setString(1, model2.getValueAt(row,1).toString());
	                          pstmt.setString(2, model2.getValueAt(row, 2).toString());
	                          pstmt.setString(3, model2.getValueAt(row, 3).toString());	                       
	                          pstmt.setString(4, model2.getValueAt(row, 0).toString());
	   
	                         int cnt = pstmt.executeUpdate();
	                         if (cnt == 1) {
	                             System.out.println("ȸ������ ������ �����Ͽ����ϴ�.");
	              
	                         } else {
	                             System.out.println("ȸ������ ������ �����Ͽ����ϴ�.");
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
	          
	               
	          // ���õ� ���̺� ���� �����ϴ� �κ�
	              jBtnDelRow = new JButton();
	              
	              jBtnDelRow.addActionListener(new ActionListener() {
	                  public void actionPerformed(java.awt.event.ActionEvent e) {
	                      System.out.println(e.getActionCommand());        // ���õ� ��ư�� �ؽ�Ʈ�� ���
	                      DefaultTableModel model2 = (DefaultTableModel)table.getModel();
	                      int row = table.getSelectedRow();	                      
	                      if(row<0) return; // ������ �ȵ� ���¸� -1����
	                      String query = "delete from MMG where m_number=?";	                 
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
	              
	              jBtnDelRow.setBounds(new Rectangle(343, 296, 222, 56));
	              jBtnDelRow.setText("����");
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
	  


