package org.makerminds.jcoaching.internship.restaurantpoint.gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public abstract class AbstractView extends JPanel{
	
	protected final Font GENERAL_LABEL_FONT = new Font("Arial", Font.PLAIN, 15);
	protected final Color CORPORATE_BLUE = Color.decode("#4285f4");
	
	protected AbstractView() {
		setVisible(true);
		setBackground(Color.WHITE);
		setBounds(264, 300, 1200, 700);
		setLayout(null);
		setVisible(true);
		prepareView();
	}
	
	public abstract void prepareView();
	
	protected void prepareViewHeadline(String viewHeadlineLabelText) {
		JLabel viewHeadline = new JLabel(viewHeadlineLabelText);
		viewHeadline.setBounds(0, 0, 675, 41);
		viewHeadline.setFont(new Font("Arial", Font.PLAIN, 20));
		viewHeadline.setForeground(CORPORATE_BLUE);
		viewHeadline.setHorizontalAlignment(SwingConstants.CENTER);
		add(viewHeadline);
	}
	
	protected TitledBorder createTitledBorder(String titleText) {
		TitledBorder titledBorder = BorderFactory.createTitledBorder(titleText);
		titledBorder.setTitleColor(CORPORATE_BLUE);
		return titledBorder;
	}
	
	protected JLayeredPane getContentPane() {
		return (JLayeredPane) this.getParent();
	}
}
