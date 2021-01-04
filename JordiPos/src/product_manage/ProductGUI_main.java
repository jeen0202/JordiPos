package product_manage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JPanel;

public class ProductGUI_main {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductGUI_main window = new ProductGUI_main();
					window.frame.setBounds(100, 100, 284, 572);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ProductGUI_main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ProductDAO dao = new ProductDAO();
		frame = new JFrame("ªÛ«∞∞¸∏Æ");
		frame.setBounds(100, 100, 869, 571);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JPanel m_panel = new JPanel();
		m_panel.setBounds(0, 0, 284, 551);
		frame.getContentPane().add(m_panel);
		m_panel.setLayout(null);
		m_panel.setVisible(true);
		
		JPanel p_show = new ViewProductGUI();
		p_show.setBounds(12, 10, 789, 526);
		frame.getContentPane().add(p_show);
		p_show.setLayout(null);
		
		JPanel p_add = new JPanel();
		p_add.setBounds(0, 0, 272, 536);
		frame.getContentPane().add(p_add);
		p_add.setLayout(null);
		
	
		JButton m_show = new JButton("\uC7AC\uACE0\uD604\uD669\uC870\uD68C");
		m_show.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 20));
		m_show.setBounds(28, 141, 219, 82);
		m_panel.add(m_show);
		m_show.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {	
				frame.setBounds(100, 100, 850, 603);				
				m_panel.setVisible(false);
				p_add.setVisible(false);
				p_show.setVisible(true);
				dao.select_exp();
			}
		});
		
		JButton m_add = new JButton("\uC0C1\uD488 \uCD94\uAC00");
		m_add.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				m_panel.setVisible(false);
				p_show.setVisible(false);
				p_add.setVisible(true);
			}
		});
		m_add.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 20));
		m_add.setBounds(28, 255, 219, 82);
		m_panel.add(m_add);
		
		JButton m_exit = new JButton("\uC885\uB8CC");
		m_exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		m_exit.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 20));
		m_exit.setBounds(28, 381, 219, 82);
		m_panel.add(m_exit);
		
		JLabel m_name = new JLabel("\uC0C1\uD488 \uAD00\uB9AC");
		m_name.setHorizontalAlignment(SwingConstants.CENTER);
		m_name.setFont(new Font("»ﬁ∏’µ’±Ÿ«ÏµÂ∂Û¿Œ", Font.PLAIN, 25));
		m_name.setBounds(28, 32, 219, 57);
		m_panel.add(m_name);
		
		
		
		JButton pBtn_exit = new JButton("\uC885\uB8CC");
		pBtn_exit.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
			frame.setBounds(100, 100, 284, 572);
			p_show.setVisible(false);
			m_panel.setVisible(true);
			}
		});
		pBtn_exit.setBounds(607, 427, 168, 62);
		p_show.add(pBtn_exit);
		
		
		p_show.setVisible(false);	
		
		
		
		JButton a_exit = new JButton("\uC885\uB8CC");
		a_exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				m_panel.setVisible(true);
				p_add.setVisible(false);
			}
		});
		a_exit.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 20));
		a_exit.setBounds(66, 485, 147, 33);
		p_add.add(a_exit);
	}

}
