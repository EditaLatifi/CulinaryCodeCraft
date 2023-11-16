package org.makerminds.jcoaching.internship.restaurantpoint.gui.view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.makerminds.jcoaching.internship.restaurantpoint.gui.builder.NavigationBarBuilder;

/**
 * Generic user role view to prepare the view based on the user role of the logged in user.
 * @author makerminds
 *
 */
public class UserRoleView implements IView {
	
	protected final Color CORPORATE_BLUE = Color.decode("#4285f4");
	
	private JFrame mainFrame;
	
	private void initializeMainFrame() {
		mainFrame = new JFrame();
		mainFrame.getContentPane().setBackground(Color.WHITE);
		mainFrame.getContentPane().setLayout(null);
		mainFrame.setBackground(Color.WHITE);
		mainFrame.setBounds(100, 100, 1200, 619);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void prepareView() {
		initializeMainFrame();
		JLayeredPane contentPane = prepareContentPane();
		NavigationBarBuilder navigationBarView = new NavigationBarBuilder(contentPane);
		JPanel navigationBarComponents = navigationBarView.createNavigationBarComponents();
		JPanel welcomePanel = prepareWelcomePanel();
		
		contentPane.add(welcomePanel);

		mainFrame.getContentPane().add(navigationBarComponents);
		mainFrame.getContentPane().add(contentPane);
	}

	private JPanel prepareWelcomePanel() {
		JPanel welcomePanel = new JPanel();
		welcomePanel.setBackground(Color.WHITE);
		welcomePanel.setLayout(null);

		JLabel lblWelcome = new JLabel("WELCOME!");
		lblWelcome.setFont(new Font("Arial", Font.PLAIN, 80));
		lblWelcome.setForeground(CORPORATE_BLUE);
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setBounds(78, 242, 561, 64);
		welcomePanel.add(lblWelcome);
		return welcomePanel;
	}
	
	private JLayeredPane prepareContentPane() {
		JLayeredPane contentPane = new JLayeredPane();
		contentPane.setBounds(266, 0, 1000, 582);
		contentPane.setLayout(new CardLayout(0, 0));
		return contentPane;
	}

	@Override
	public void refreshViewData() {
		// no data to refresh for this view
	}
}
