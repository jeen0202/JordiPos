package product_manage;
import java.awt.Rectangle;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;


public class ViewProductGUI extends JPanel{
	  private static final long serialVersionUID = 1L;
	          private JButton jBtnAddRow = null;    // ���̺� ���� �߰� ��ư
	          private JButton jBtnSaveRow = null;    // ���̺� ���� ���� ��ư
	          private JButton jBtnEditRow = null;    // ���̺� ���� ���� ��ư
	          private JButton jBtnDelRow = null;        // ���̺� ���� ���� ��ư
	          private JTable table;   
	          private JScrollPane scrollPane;        // ���̺� ��ũ�ѹ� �ڵ����� �����ǰ� �ϱ�
	          
	          private String driver = "oracle.jdbc.driver.OracleDriver";       
	          private String url = "jdbc:oracle:thin:@localhost:1521:xe";        
	          private String colNames[] = {"��ǰ��ȣ","��ǰ��","����","�Ǹŷ�","�ܿ���","�������"};  // ���̺� �÷� ����
	          private DefaultTableModel model = new DefaultTableModel(colNames, 0); //  ���̺� ������ �� ��ü ����      
	          private Connection con = null;
	          private PreparedStatement pstmt = null;
	          private ResultSet rs = null;   // ���Ϲ޾� ����� ��ü ���� ( select���� ������ �� �ʿ� )
	   
	          public ViewProductGUI() {
	              setLayout(null);        // ���̾ƿ� ��ġ������ ����
	              table = new JTable(model);
	              table.getColumnModel().getColumn(0).setPreferredWidth(45);  
	              table.getColumnModel().getColumn(1).setPreferredWidth(160);
	              table.getColumnModel().getColumn(2).setPreferredWidth(50);
	              table.getColumnModel().getColumn(3).setPreferredWidth(30);
	              table.getColumnModel().getColumn(4).setPreferredWidth(30);
	              table.getColumnModel().getColumn(5).setPreferredWidth(50);	  
	              table.setRowHeight(20);              
	       

	              DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();
	              tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);         
	              TableColumnModel tcmSchedule = table.getColumnModel();
	              tcmSchedule.getColumn(0).setCellRenderer(tScheduleCellRenderer);
	              tcmSchedule.getColumn(2).setCellRenderer(tScheduleCellRenderer);
	              tcmSchedule.getColumn(3).setCellRenderer(tScheduleCellRenderer);
	              tcmSchedule.getColumn(4).setCellRenderer(tScheduleCellRenderer);
	              tcmSchedule.getColumn(5).setCellRenderer(tScheduleCellRenderer);
	             
	              table.setFont(new Font("���� ���", Font.PLAIN,18));
	              table.addMouseListener(new JTableMouseListener());        // ���̺� ���콺������ ����
	              scrollPane = new JScrollPane(table);            // ���̺� ��ũ�� ����� �ϱ�
	              scrollPane.setLocation(0, 0);
	              scrollPane.setSize(781, 300);
	              scrollPane.setPreferredSize(new Dimension(1030,506));
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
	        	   ProductDAO dao = new ProductDAO();
	              String query = "select * from PMG";    
	              try{
	                  Class.forName(driver);
	                  con = DriverManager.getConnection(url, "hr", "hr");
	                  pstmt = con.prepareStatement(query);
	                  rs = pstmt.executeQuery(); // ���Ϲ޾ƿͼ� �����͸� ����� ��ü����
	                   
	                  while(rs.next()){            // ���� ���� �����ͼ� ���̺����� �߰�
	                      model.addRow(new Object[]{rs.getString("p_code"),rs.getString("p_name"),
	                                                              rs.getString("p_price"),rs.getString("r_quantity"),
	                                                              rs.getString("d_sale"),rs.getDate("e_date")});
	                   
	                  }
	              }catch(Exception e){
	                  System.out.println(e.getMessage());
	              }finally{
	                  	dao.close();
	              }
	          }
	       
	          private void initialize() {            // �׼��̺�Ʈ�� ��ư ������Ʈ ����
	               
	          // ���̺� ���� ���� �߰��ϴ� �κ�
	              jBtnAddRow = new JButton();
	              jBtnAddRow.addActionListener(new ActionListener() {   
	                  public void actionPerformed(ActionEvent e) {
	                      System.out.println(e.getActionCommand());        // ���õ� ��ư�� �ؽ�Ʈ�� ���
	                      DefaultTableModel model2 = (DefaultTableModel)table.getModel();
	                      model2.addRow(new String[]{"","","","","",""});            // �����̺��� �ʱⰪ
	                  }
	              });
	              jBtnAddRow.setBounds(14,334,164, 61);
	              jBtnAddRow.setText("\uC0C1\uD488 \uCD94\uAC00");
	              add(jBtnAddRow);
	               
	   
	          // ���̺� ���� �����ϴ� �κ�
	              jBtnSaveRow = new JButton();
	              jBtnSaveRow.addActionListener(new ActionListener() {
	                  public void actionPerformed(ActionEvent e) {
	                      System.out.println(e.getActionCommand());        // ���õ� ��ư�� �ؽ�Ʈ�� ���
	                      DefaultTableModel model2 = (DefaultTableModel)table.getModel();
	                      int row = table.getSelectedRow();
	                      if(row<0) return;     // ������ �ȵ� ���¸� -1����
	                      String query = "insert into PMG values(?,?,?,?,?,?)";
	                       
	   
	                      try{	                    	 
	                          Class.forName(driver);  // ����̹� �ε�
	                          con = DriverManager.getConnection(url, "hr", "hr"); // DB ����
	                          pstmt = con.prepareStatement(query);  
	                           
	                          // ����ǥ�� 4�� �̹Ƿ� 4�� ���� �Է�������Ѵ�.
	                          pstmt.setString(1, (String) model2.getValueAt(row, 0));
	                          pstmt.setString(2, (String) model2.getValueAt(row, 1));
	                          pstmt.setString(3, (String) model2.getValueAt(row, 2));
	                          pstmt.setString(4, (String) model2.getValueAt(row, 3));
	                          pstmt.setString(5, (String) model2.getValueAt(row, 4));
	                          pstmt.setString(6, (String) model2.getValueAt(row, 5));
	   
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
	              jBtnSaveRow.setBounds(192,334,153, 61);
	              jBtnSaveRow.setText("\uC0C1\uD488 \uCD94\uAC00 \uD655\uC778");
	              add(jBtnSaveRow);
	               
	               
	              // ���õ� ���̺� ���� �����ϴ� �κ�
	              jBtnEditRow = new JButton();
	              
	              jBtnEditRow.addActionListener(new ActionListener() {
	                  public void actionPerformed(ActionEvent e) {       
	                               
	                      System.out.println(e.getActionCommand());   // ���õ� ��ư�� �ؽ�Ʈ�� ���
	                      DefaultTableModel model2 = (DefaultTableModel)table.getModel();
	                      int row = table.getSelectedRow();
	                      if(row<0) return;     // ������ �ȵ� ���¸� -1����
	    
	                      String query = "UPDATE PMG SET p_code=?, p_name=?, p_price=?,r_quantity=?,d_sale=?,e_date=? where p_code = ? ";
	                       
	               	   
	                      try{	                    	 
	                          Class.forName(driver);  // ����̹� �ε�
	                          con = DriverManager.getConnection(url, "hr", "hr"); // DB ����
	                          pstmt = con.prepareStatement(query);  
	                           
	                          // ����ǥ�� 4�� �̹Ƿ� 4�� ���� �Է�������Ѵ�.	                        
	                          pstmt.setString(1, model2.getValueAt(row, 0).toString());
	                          pstmt.setString(2, model2.getValueAt(row, 1).toString());
	                          pstmt.setString(3, model2.getValueAt(row, 2).toString());
	                          pstmt.setString(4, model2.getValueAt(row, 3).toString());
	                          pstmt.setString(5, model2.getValueAt(row, 4).toString());
	                          pstmt.setString(6, model2.getValueAt(row, 5).toString());
	                          pstmt.setString(7, model2.getValueAt(row, 0).toString());
	   
	   
	                          int cnt = pstmt.executeUpdate();
	                          //pstmt.executeUpdate(); create insert update delete
	                          //pstmt.executeQuery(); select
	                      }catch(Exception eeee){
	                          System.out.println(eeee.getMessage());
	                      }finally{
	                          try {
	                              pstmt.close();
	                              con.close();
	                          } catch (Exception e2) {}
	                      }
	                      model2.setRowCount(0);         // ��ü ���̺� ȭ���� ������
	                      select();          // ���� �Ĵٽ� ��ü ������ �޾ƿ�.
	                  }
	              });
	              
	              jBtnEditRow.setBounds(14,429,164, 61);
	              jBtnEditRow.setText("\uC0C1\uD488 \uC815\uBCF4 \uC218\uC815");
	              add(jBtnEditRow);
	          
	               
	          // ���õ� ���̺� ���� �����ϴ� �κ�
	              jBtnDelRow = new JButton();
	              
	              jBtnDelRow.addActionListener(new ActionListener() {
	                  public void actionPerformed(java.awt.event.ActionEvent e) {
	                      System.out.println(e.getActionCommand());        // ���õ� ��ư�� �ؽ�Ʈ�� ���
	                      DefaultTableModel model2 = (DefaultTableModel)table.getModel();
	                      int row = table.getSelectedRow();
	                      if(row<0) return; // ������ �ȵ� ���¸� -1����
	                      String query = "delete from PMG where p_code= ?";
	       
	                      try{
	                          Class.forName(driver);  // ����̹� �ε�
	                          con = DriverManager.getConnection(url, "hr", "hr"); // DB ����
	                          pstmt = con.prepareStatement(query);  
	                           
	                          // ����ǥ�� 1�� �̹Ƿ� 4�� ���� �Է�������Ѵ�.
	                          pstmt.setString(1, model2.getValueAt(row, 0).toString());
	                          int cnt = pstmt.executeUpdate();
	              
	                      }catch(Exception eeee){
	                         eeee.printStackTrace();
	                      }finally{
	                          try {
	                              pstmt.close();con.close();
	                          } catch (Exception e2) {}
	                      }
	                      model2.removeRow(row);    // ���̺� ���� ���� ����
	                  }
	              });
	              jBtnDelRow.setBounds(new Rectangle(192, 429, 154, 61));
	              jBtnDelRow.setText("\uC0C1\uD488 \uC0AD\uC81C");
	              add(jBtnDelRow);
	          }
	           
	          
	      public static void main(String[] args) {
	           
	    	  ViewProductGUI panel = new ViewProductGUI();
	          JFrame win = new JFrame();
	           
	          win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	           
	          win.getContentPane().add(panel);
	         win.setSize(820,550);
	         
	          win.setVisible(true);
	 
	     } 
	  }


