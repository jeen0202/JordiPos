package Manager;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;



import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ManagerGUI_main extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frmg;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerGUI_main window = new ManagerGUI_main();
					window.frmg.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ManagerGUI_main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frmg.
	 */
	private void initialize() {
		ManagerDAO dao = new ManagerDAO();
		frmg = new JFrame();
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
		
		JPanel ntc_view = new ViewNoticeGUI();
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
		

		
		
		

	}
}
