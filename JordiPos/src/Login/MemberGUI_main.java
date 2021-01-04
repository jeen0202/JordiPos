package Login;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JPanel;
public class MemberGUI_main {

	private JFrame frMem;
	private String driver = "oracle.jdbc.driver.OracleDriver";       
    private String url = "jdbc:oracle:thin:@localhost:1521:xe";
    private Connection con = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MemberGUI_main window = new MemberGUI_main();
					window.frMem.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MemberGUI_main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ProductDAO dao = new ProductDAO();
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
		btnSellExit.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 18));
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
		btnProductExit.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 18));
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
		Menu_Employee.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 20));
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
		Menu_Product.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 20));
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
		Menu_Sales.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 20));
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
		Menu_Notice.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 20));
		MemberMenu.add(Menu_Notice);
		
		JButton Menu_Exit = new JButton("\uC885\uB8CC");
		Menu_Exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		Menu_Exit.setBounds(273, 435, 189, 105);
		Menu_Exit.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 20));
		MemberMenu.add(Menu_Exit);
		
		JButton button = new JButton("\uB9E4\uCD9C \uD655\uC778");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				try{
                    Class.forName("oracle.jdbc.driver.OracleDriver");  // µÂ∂Û¿Ãπˆ ∑Œµ˘
                    con = DriverManager.getConnection(url, "hr", "hr"); // DB ø¨∞·
                   	
				String sql = "Select d_sale,m_sale from SMG where m_number = 101";						
				pstmt = con.prepareStatement(sql);
						
				rs = pstmt.executeQuery(); // tableø° ∏Ì∑…¿ª ¿¸¥ﬁ«“∂ß¥¬ executeUpdate();				
				while(rs.next()) { //rs.next()¿« π›»Ø∞™ => boolean
				 JOptionPane.showMessageDialog(null, "∏≈√‚ ¡∂»∏\n"
				 		+ "¿œ∏≈√‚ : "+rs.getInt(1)+"\n"+ "ø˘∏≈√‚ : "+rs.getInt(2)+"\n");
				
				
				}
			}catch (Exception ee) {				
				ee.printStackTrace();	 		
			}
				
			}
		});
		button.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 20));
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
		btnNotice_Exit.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 18));
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
		btnEmployeeExit.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 18));
		btnEmployeeExit.setBounds(351, 527, 248, 75);
		Member_Employee.add(btnEmployeeExit);
		

		

		

		

	}
}
