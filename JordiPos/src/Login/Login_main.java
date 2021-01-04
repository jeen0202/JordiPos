package Login;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Manager.*;
import Manager.ManagerDAO;
import Manager.ViewMemberGUI;
import Manager.ViewNoticeGUI;
import Member.*;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login_main {
	
	String id = null;
	String pw = null;
	String admin_id = null;
	String admin_pw = null;
	String ckPw = null;
	int Logincheck =0;
	//private String driver = "oracle.jdbc.driver.OracleDriver";       
    private String url = "jdbc:oracle:thin:@localhost:1521:xe";
    private Connection con = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;
    
	JFrame frmd;
	JFrame frmg;
	JFrame frMem;
	private JTextField tfId;
	private JTextField tfPw;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login_main window = new Login_main();
					window.frmd.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login_main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {		
		ProductDAO dao = new ProductDAO();
		frmd = new JFrame();
		frmg = new JFrame();
		frMem = new JFrame();
		frmd.setTitle("\uC870\uB974D \uD3EC\uC2A4");
		frmd.setBounds(100, 100, 626, 462);
		frmd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmd.getContentPane().setLayout(null);
		frmd.setLocationRelativeTo(null);
		
		JPanel login_menu = new JPanel();
		login_menu.setBounds(0, 0, 608, 405);
		frmd.getContentPane().add(login_menu);
		login_menu.setLayout(null);
		
		JLabel lbId = new JLabel("\uC544\uC774\uB514 :");
		lbId.setHorizontalAlignment(SwingConstants.TRAILING);
		lbId.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 24));
		lbId.setBounds(74, 107, 125, 57);
		login_menu.add(lbId);
		
		JLabel lbPw = new JLabel("\uBE44\uBC00\uBC88\uD638 :");
		lbPw.setHorizontalAlignment(SwingConstants.TRAILING);
		lbPw.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 24));
		lbPw.setBounds(74, 207, 125, 57);
		login_menu.add(lbPw);
		
		tfId = new JTextField();
		tfId.setFont(new Font("³ª´®°íµñ", Font.PLAIN, 20));
		tfId.setBounds(213, 117, 201, 44);
		login_menu.add(tfId);
		tfId.setColumns(10);
		
		tfPw = new JTextField();
		tfPw.setFont(new Font("³ª´®°íµñ", Font.PLAIN, 20));
		tfPw.setColumns(10);
		tfPw.setBounds(213, 220, 201, 44);
		login_menu.add(tfPw);
		frmg.setTitle("\uAD00\uB9AC\uC790 \uBAA8\uB4DC");
		frmg.setBounds(100, 100, 328, 476);
		frmg.setLocationRelativeTo(null);
		frmg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmg.getContentPane().setLayout(null);
		
		JPanel m_view = new JPanel();
		m_view.setBounds(0, 0, 310, 429);
		frmg.getContentPane().add(m_view);
		m_view.setLayout(null);
		m_view.setVisible(true);
		
		JPanel mmg_view = new ViewMemberGUI();
		mmg_view.setBounds(12, 10, 1150, 400);
		frmg.getContentPane().add(mmg_view);
		mmg_view.setLayout(null);
		
		mmg_view.setVisible(false);
		
		
		
		JButton btnMmgExit = new JButton(" \uC885\uB8CC");
		btnMmgExit.addMouseListener(new MouseAdapter() {			
			public void mouseClicked(MouseEvent e) {
				frmg.setBounds(100, 100, 328, 476);
				frmg.setLocationRelativeTo(null);
				m_view.setVisible(true);			
				mmg_view.setVisible(false);
				
			}
		});
		btnMmgExit.setBounds(655, 300, 231, 51);
		mmg_view.add(btnMmgExit);
		
		JPanel ntc_view = new ManagerViewNoticeGUI();
		ntc_view.setBounds(0, 0, 524, 369);
		frmg.getContentPane().add(ntc_view);
		
		JButton btnNoticeExit = new JButton("\uC885\uB8CC");
		btnNoticeExit.setBounds(337, 296, 142, 50);
		btnNoticeExit.addMouseListener(new MouseAdapter() {			
			public void mouseClicked(MouseEvent e) {
				frmg.setBounds(100, 100, 328, 476);
				frmg.setLocationRelativeTo(null);
				m_view.setVisible(true);			
				ntc_view.setVisible(false);
			}
		});
		ntc_view.add(btnNoticeExit);
		ntc_view.setVisible(false);
		
		JButton btn_mmg = new JButton("\uC9C0\uC810 \uAD00\uB9AC");
		btn_mmg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmg.setBounds(100, 100, 1200,506);
				frmg.setLocationRelativeTo(null);				
				m_view.setVisible(false);		
				ntc_view.setVisible(false);
				mmg_view.setVisible(true);
			}
		});
		

		btn_mmg.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		btn_mmg.setBounds(25, 42, 255, 76);
		m_view.add(btn_mmg);
		
		JButton btnNotice = new JButton("\uACF5\uC9C0 \uAD00\uB9AC");
		btnNotice.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frmg.setBounds(100, 100, 532, 450);
				frmg.setLocationRelativeTo(null);
				m_view.setVisible(false);
				mmg_view.setVisible(false);
				ntc_view.setVisible(true);
				
			}
		});
		btnNotice.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		btnNotice.setBounds(25, 165, 255, 76);
		m_view.add(btnNotice);
		
		JButton btnExit = new JButton("\uC885\uB8CC");
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		btnExit.setBounds(25, 287, 255, 76);
		m_view.add(btnExit);
		
		frMem = new JFrame();
		frMem.setTitle("\uD68C\uC6D0 \uBAA8\uB4DC");
		frMem.setBounds(100, 100, 494, 605);
		frMem.setLocationRelativeTo(null);
		frMem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frMem.getContentPane().setLayout(null);
		
		JPanel MemberMenu = new JPanel();
		MemberMenu.setBounds(0, 0, 485, 565);
		frMem.getContentPane().add(MemberMenu);
		MemberMenu.setLayout(null);
		
		JPanel Member_Sell = new MemberSalesSell();
		Member_Sell.setBounds(0, 0, 1097, 577);
		frMem.getContentPane().add(Member_Sell);
		
		JButton btnSellExit = new JButton("\uC885\uB8CC");
		btnSellExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frMem.setBounds(100, 100, 488, 596);
				frMem.setLocationRelativeTo(null);
				MemberMenu.setVisible(true);
				Member_Sell.setVisible(false);
			}
		});
		btnSellExit.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 18));
		btnSellExit.setBounds(878, 368, 194, 78);
		Member_Sell.add(btnSellExit);
		Member_Sell.setVisible(false);
		
		JPanel Member_Product = new MemberViewProduct();
		Member_Product.setBounds(0, 0, 781, 600);
		frMem.getContentPane().add(Member_Product);
		Member_Product.setVisible(false);
		
		JButton btnProductExit = new JButton("\uC885\uB8CC");
		btnProductExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frMem.setBounds(100, 100, 488, 596);
				frMem.setLocationRelativeTo(null);
				MemberMenu.setVisible(true);
				Member_Product.setVisible(false);
			}
		});
		btnProductExit.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 18));
		btnProductExit.setBounds(592, 459, 189, 92);
		Member_Product.add(btnProductExit);
		
		JPanel Member_Notice = new MemberViewNotice();
		Member_Notice.setBounds(0, 0, 801, 537);
		frMem.getContentPane().add(Member_Notice);
		Member_Notice.setVisible(false);
		
		JPanel Member_Employee = new MemberViewEmployee();
		Member_Employee.setBounds(0, 0, 624,614);
		frMem.getContentPane().add(Member_Employee);
		Member_Employee.setVisible(false);
		JButton Menu_Employee = new JButton("\uC9C1\uC6D0 \uAD00\uB9AC");
		Menu_Employee.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frMem.setSize(700,750);				
				MemberMenu.setVisible(false);
				Member_Notice.setVisible(false);
				Member_Employee.setVisible(true);
				
			}
		});
		Menu_Employee.setBounds(14, 31, 189, 116);
		Menu_Employee.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		MemberMenu.add(Menu_Employee);
		
		JButton Menu_Product = new JButton("\uC0C1\uD488 \uAD00\uB9AC");
		Menu_Product.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frMem.setSize(820,650);
				MemberMenu.setVisible(false);
				Member_Notice.setVisible(false);
				Member_Employee.setVisible(false);
				Member_Product.setVisible(true);
				dao.select_exp();
				
			}
		});
		Menu_Product.setBounds(273, 31, 189, 105);
		Menu_Product.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		MemberMenu.add(Menu_Product);
		
		JButton Menu_Sales = new JButton("\uD310\uB9E4");
		Menu_Sales.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frMem.setSize(1100,600);
				MemberMenu.setVisible(false);
				Member_Sell.setVisible(true);
			}
		});
		Menu_Sales.setBounds(14, 219, 189, 116);
		Menu_Sales.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		MemberMenu.add(Menu_Sales);
		
		JButton Menu_Notice = new JButton("\uACF5\uC9C0\uC0AC\uD56D");
		Menu_Notice.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frMem.setSize(800,580);
				MemberMenu.setVisible(false);
				Member_Notice.setVisible(true);
			}
		});
		Menu_Notice.setBounds(273, 219, 189, 121);
		Menu_Notice.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		MemberMenu.add(Menu_Notice);
		
		JButton Menu_Exit = new JButton("\uC885\uB8CC");
		Menu_Exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		Menu_Exit.setBounds(273, 435, 189, 105);
		Menu_Exit.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		MemberMenu.add(Menu_Exit);
		
		JButton button = new JButton("\uB9E4\uCD9C \uD655\uC778");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				try{
                    Class.forName("oracle.jdbc.driver.OracleDriver");  // µå¶óÀÌ¹ö ·Îµù
                    con = DriverManager.getConnection(url, "hr", "hr"); // DB ¿¬°á
                   	
				String sql = "Select d_sale,m_sale from SMG where m_number = 101";						
				pstmt = con.prepareStatement(sql);
						
				rs = pstmt.executeQuery(); // table¿¡ ¸í·ÉÀ» Àü´ÞÇÒ¶§´Â executeUpdate();				
				while(rs.next()) { //rs.next()ÀÇ ¹ÝÈ¯°ª => boolean
				 JOptionPane.showMessageDialog(null, "¸ÅÃâ Á¶È¸\n"
				 		+ "ÀÏ¸ÅÃâ : "+rs.getInt(1)+"\n"+ "¿ù¸ÅÃâ : "+rs.getInt(2)+"\n");
				
				
				}
			}catch (Exception ee) {				
				ee.printStackTrace();	 		
			}
				
			}
		});
		button.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		button.setBounds(14, 422, 189, 116);
		MemberMenu.add(button);
		
		
		
		
		JButton btnNotice_Exit = new JButton("\uC885\uB8CC");
		btnNotice_Exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frMem.setBounds(100, 100, 488, 596);
				frMem.setLocationRelativeTo(null);
				MemberMenu.setVisible(true);
				Member_Notice.setVisible(false);
			}
		});
		btnNotice_Exit.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 18));
		btnNotice_Exit.setBounds(520, 444, 227, 93);
		Member_Notice.add(btnNotice_Exit);
		
		
		
		JButton btnEmployeeExit = new JButton("\uC885\uB8CC");
		btnEmployeeExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {	
				frMem.setBounds(100, 100, 488, 596);
				frMem.setLocationRelativeTo(null);
				MemberMenu.setVisible(true);
				Member_Notice.setVisible(false);
				Member_Employee.setVisible(false);
			}
		});
		btnEmployeeExit.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 18));
		btnEmployeeExit.setBounds(351, 527, 248, 75);
		Member_Employee.add(btnEmployeeExit);
		
		JButton btnLogin = new JButton("\uD655\uC778");
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				id = tfId.getText();				
				pw = tfPw.getText();
				//System.out.println(id +" "+ pw);
				
				try{
                    Class.forName("oracle.jdbc.driver.OracleDriver");  // µå¶óÀÌ¹ö ·Îµù
                    con = DriverManager.getConnection(url, "hr", "hr"); // DB ¿¬°á
                   	
				String sql = "Select m_id,m_pw from MMG where m_number = 100";						
				pstmt = con.prepareStatement(sql);
										
				rs = pstmt.executeQuery(); // table¿¡ ¸í·ÉÀ» Àü´ÞÇÒ¶§´Â executeUpdate();				
				while(rs.next()) {
					admin_id = rs.getString(1);
					admin_pw = rs.getString(2);	
					//System.out.println(admin_id + " " + admin_pw);
				}
				sql = "select m_pw from MMG where m_id = ?";
						pstmt = con.prepareStatement(sql);
						pstmt.setString(1,id);
						rs = pstmt.executeQuery();
						if(rs.next()){
							ckPw = rs.getString("m_pw");
						}
						if(ckPw.equals(pw)) {
							if(id.equals(admin_id)&& pw.equals(admin_pw)) { //°ü¸®ÀÚ °èÁ¤ÀÏ¶§
								 Logincheck = 1;								
							}else // »ç¿ëÀÚ °èÁ¤ÀÏ¶§
								Logincheck = 2;								
						}else
							Logincheck = -1;
						
						switch(Logincheck) {
						case 1:
							JOptionPane.showMessageDialog(null,"°ü¸®ÀÚ ¸ðµå ·Î±×ÀÎ");						
							frMem.setVisible(false);
							frmd.setVisible(false);
							frmg.setVisible(true);
							break;
						case 2:
							JOptionPane.showMessageDialog(null,"»ç¿ëÀÚ ¸ðµå ·Î±×ÀÎ");;
							frmd.setVisible(false);
							frmg.setVisible(false);
							frMem.setVisible(true);
							break;
							default :
							JOptionPane.showMessageDialog(null,"·Î±×ÀÎ ½ÇÆÐ");	
						}
							
				
					
				
			}catch (Exception ee) {				
				ee.printStackTrace();	 		
			}
			}
		});
		btnLogin.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		btnLogin.setBounds(213, 298, 201, 57);
		login_menu.add(btnLogin);
		
		JLabel lblNewLabel = new JLabel("\uC8E0\uB974D POS \uC2DC\uC2A4\uD15C");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("³ª´®°íµñ", Font.BOLD, 25));
		lblNewLabel.setBounds(74, 27, 464, 49);
		login_menu.add(lblNewLabel);
	}

}
