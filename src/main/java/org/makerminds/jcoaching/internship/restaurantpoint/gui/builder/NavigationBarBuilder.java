package org.makerminds.jcoaching.internship.restaurantpoint.gui.builder;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import org.makerminds.jcoaching.internship.restaurantpoint.controller.LoginController;
import org.makerminds.jcoaching.internship.restaurantpoint.gui.utils.ViewIdentifierNameResolver;
import org.makerminds.jcoaching.internship.restaurantpoint.gui.view.IView;
import org.makerminds.jcoaching.internship.restaurantpoint.gui.view.UserRoleViewIdentifierProvider;
import org.makerminds.jcoaching.internship.restaurantpoint.gui.view.ViewFactory;
import org.makerminds.jcoaching.internship.restaurantpoint.gui.view.ViewIdentifier;
import org.makerminds.jcoaching.internship.restaurantpoint.model.user.User;
import org.makerminds.jcoaching.internship.restaurantpoint.model.user.UserRole;

/**
 * the view for the navigation bar
 * 
 * @author makerminds
 *
 */
public class NavigationBarBuilder {

	// General GUI settings
	private final Font GENERAL_LABEL_FONT = new Font("Arial", Font.PLAIN, 15);
	private final Color CORPORATE_BLUE = Color.decode("#4285f4");

	private JLayeredPane contentPaneReference;

	public NavigationBarBuilder(JLayeredPane contentPaneReference) {
		this.contentPaneReference = contentPaneReference;
	}

	public JPanel createNavigationBarComponents() {
		final JPanel navigationBarPanel = new JPanel();
		navigationBarPanel.setBackground(CORPORATE_BLUE);
		navigationBarPanel.setBorder(null);
		navigationBarPanel.setBounds(0, 0, 266, 582);
		navigationBarPanel.setLayout(null);

		List<JPanel> navigationBarItems = createNavigationBarItems();

		for (JPanel navigationBarItem : navigationBarItems) {
			navigationBarPanel.add(navigationBarItem);
		}

		return navigationBarPanel;
	}

	private List<JPanel> createNavigationBarItems() {
		List<ViewIdentifier> viewIdentifiersForNavigationBarItems = getNavigationBarItemsForUserRole();
		return createNavigationBarItems(viewIdentifiersForNavigationBarItems);
	}

	private List<ViewIdentifier> getNavigationBarItemsForUserRole() {
		User loggedInUser = LoginController.getLoggedInUser();
		UserRole userRole = loggedInUser.getUserRole();

		List<ViewIdentifier> viewsForUserRole = UserRoleViewIdentifierProvider.getViewsForUserRole(userRole);
		return viewsForUserRole;
	}

	private List<JPanel> createNavigationBarItems(List<ViewIdentifier> viewIdentifiersForNavigationBarItems) {
		List<JPanel> navigationBarItemPanels = new ArrayList<>();
		int navigationItemVerticalPosition = 0;
		int navigationItemSpacing = 48;
		prepareNavigationBarItems(viewIdentifiersForNavigationBarItems, navigationBarItemPanels,
				navigationItemVerticalPosition, navigationItemSpacing);

		return navigationBarItemPanels;
	}

	private void prepareNavigationBarItems(List<ViewIdentifier> viewIdentifiersForNavigationBarItems,
			List<JPanel> navigationBarItemPanels, int navigationItemVerticalPosition, int navigationItemSpacing) {
		for (ViewIdentifier viewIdentifier : viewIdentifiersForNavigationBarItems) {
			JPanel navigationBarItemPanel = new JPanel();
			navigationBarItemPanel.setBackground(CORPORATE_BLUE);
			navigationItemVerticalPosition += navigationItemSpacing;
			navigationBarItemPanel.setBounds(0, navigationItemVerticalPosition, 270, 48);

			// get the name from view identifier to use for label text
			String viewIdentifierName = ViewIdentifierNameResolver.getViewIdentifierName(viewIdentifier);
			JLabel navigationBarLabel = prepareNavigationBarItemLabel(viewIdentifierName);

			prepareNavigationBarItemMouseListener(viewIdentifier, navigationBarItemPanel);

			navigationBarItemPanel.add(navigationBarLabel);
			navigationBarItemPanels.add(navigationBarItemPanel);
		}
	}

	private void prepareNavigationBarItemMouseListener(ViewIdentifier viewIdentifier, JPanel navigationBarItemPanel) {
		navigationBarItemPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				IView navigationBarItemView = ViewFactory.getInstance().getViewByViewIdentifier(viewIdentifier);
				clickNavigationBarItem(navigationBarItemView);
			}
		});
	}

	public void clickNavigationBarItem(IView view) {
		JPanel panel = (JPanel) view;
		view.refreshViewData();
		contentPaneReference.removeAll();
		contentPaneReference.add(panel);
		contentPaneReference.repaint();
		contentPaneReference.revalidate();
	}

	private JLabel prepareNavigationBarItemLabel(String viewIdentifierName) {
		JLabel navigationBarLabel = new JLabel(viewIdentifierName);
		navigationBarLabel.setForeground(Color.WHITE);
		navigationBarLabel.setBackground(UIManager.getColor("List.selectionForeground"));
		navigationBarLabel.setFont(GENERAL_LABEL_FONT);
		return navigationBarLabel;
	}
}
