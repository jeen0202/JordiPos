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
	          private JScrollPane scrollPane;        // ���̺� ��ũ�ѹ� �ڵ����� �����ǰ� �ϱ�
	          
	          private String driver = "oracle.jdbc.driver.OracleDriver";       
	          private String url = "jdbc:oracle:thin:@localhost:1521:xe";        
	          private String colNames[] = {"������ȣ","����","����","�Խó�¥"};  // ���̺� �÷� ����
	          private DefaultTableModel model = new DefaultTableModel(colNames, 0); //  ���̺� ������ �� ��ü ����      
	          private Connection con = null;
	          private PreparedStatement pstmt = null;
	          private ResultSet rs = null;   // ���Ϲ޾� ����� ��ü ���� ( select���� ������ �� �ʿ� )
	   
	          public MemberViewNotice() {
	              setLayout(null);        // ���̾ƿ� ��ġ������ ����
	              table = new JTable(model);
	              table.getColumnModel().getColumn(0).setPreferredWidth(30);  
	              table.getColumnModel().getColumn(1).setPreferredWidth(30);
	              table.getColumnModel().getColumn(2).setPreferredWidth(30);
	              table.getColumnModel().getColumn(3).setPreferredWidth(30);
	              table.setRowHeight(30);
	             
	              table.setFont(new Font("���� ���", Font.PLAIN,20));
	              table.addMouseListener(new JTableMouseListener());        // ���̺� ���콺������ ����
	              scrollPane = new JScrollPane(table);            // ���̺� ��ũ�� ����� �ϱ�
	              scrollPane.setLocation(14, 0);
	              scrollPane.setSize(767, 318);
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
	        	  
	              String query = "select* from notice order by n_date";    
	              try{
	                  Class.forName(driver);
	                  con = DriverManager.getConnection(url, "hr", "hr");
	                  pstmt = con.prepareStatement(query);
	                  rs = pstmt.executeQuery(); // ���Ϲ޾ƿͼ� �����͸� ����� ��ü����
	                   
	                  while(rs.next()){            // ���� ���� �����ͼ� ���̺����� �߰�
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
	  


