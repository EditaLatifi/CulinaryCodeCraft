package org.makerminds.jcoaching.internship.restaurantpoint.gui.view.waiter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import org.makerminds.jcoaching.internship.restaurantpoint.controller.LoginController;
import org.makerminds.jcoaching.internship.restaurantpoint.gui.AbstractView;
import org.makerminds.jcoaching.internship.restaurantpoint.gui.utils.ViewIdentifierNameResolver;
import org.makerminds.jcoaching.internship.restaurantpoint.gui.view.IView;
import org.makerminds.jcoaching.internship.restaurantpoint.gui.view.IViewCreator;
import org.makerminds.jcoaching.internship.restaurantpoint.gui.view.ViewIdentifier;
import org.makerminds.jcoaching.internship.restaurantpoint.model.Restaurant;
import org.makerminds.jcoaching.internship.restaurantpoint.model.Table;
import org.makerminds.jcoaching.internship.restaurantpoint.model.TableOrder;
import org.makerminds.jcoaching.internship.restaurantpoint.model.user.User;
import org.makerminds.jcoaching.internship.restaurantpoint.model.user.WaiterUser;

@SuppressWarnings("serial")
public class TableOrdersManagerView extends AbstractView implements IView {

	@Override
	public void prepareView() {
		createHeadlineLabel();
		createRestaurantTablePanel();
	}

	private void createHeadlineLabel() {
		String viewHeadlineLabelText = ViewIdentifierNameResolver
				.getViewIdentifierName(ViewIdentifier.TABLE_ORDER_DETAILS);
		prepareViewHeadline(viewHeadlineLabelText);
	}

	private void createRestaurantTablePanel() {
		int navigationItemVerticalPosition = 0;
		int navigationItemSpacing = 40;

		JScrollPane tableListScrollPane = new JScrollPane();
		tableListScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		tableListScrollPane.setBounds(10, 10, 684, 562);
		tableListScrollPane.getVerticalScrollBar().setUnitIncrement(16);
		add(tableListScrollPane);

		JPanel tableListPanel = new JPanel();
		tableListPanel.setBackground(Color.WHITE);
		tableListScrollPane.setViewportView(tableListPanel);
		tableListPanel.setPreferredSize(new Dimension(684, 900));
		tableListPanel.setLayout(null);

		User loggedInUser = LoginController.getLoggedInUser();
		Restaurant restaurant = loggedInUser.getRestaurantList().get(0);
		createRestaurantTableButtons(restaurant, navigationItemVerticalPosition, navigationItemSpacing,
				tableListScrollPane);

	}

	private void createRestaurantTableButtons(Restaurant restaurant, int navigationItemVerticalPosition,
			int navigationItemSpacing, JScrollPane buttonsPane) {
		for (Table table : restaurant.getTableList()) {
			// get table id for the jbutton
			String buttonName = "Table #" + table.getTableNumber();
			JButton tableButton = new JButton(buttonName);
			prepareTableButtonMouseListener(tableButton, table);
			navigationItemVerticalPosition += navigationItemSpacing;
			tableButton.setBounds(0, navigationItemVerticalPosition, 700, 35);
			JPanel view = ((JPanel) buttonsPane.getViewport().getView());
			view.add(tableButton);
			view.validate();
			
			// Add table to Table Order
			WaiterUser waiterUser = (WaiterUser) LoginController.getLoggedInUser();
			TableOrder tableOrder = waiterUser.getTableOrder(table.getTableNumber());
			if(tableOrder == null)
				tableOrder = new TableOrder();
			tableOrder.setTable(table);
			waiterUser.getTableOrders().put(table.getTableNumber(), tableOrder);
		}
	}
	

	private void prepareTableButtonMouseListener(JButton tableButton, Table table) {
		tableButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Switch to the tables order detail view
				JLayeredPane contentPaneReference = getContentPane();
				JPanel panel = (JPanel) new TableOrderDetailsView(table);
				contentPaneReference.removeAll();
				contentPaneReference.add(panel);
				contentPaneReference.repaint();
				contentPaneReference.revalidate();
			}
		});
	}

	@Override
	public void refreshViewData() {
		// TODO Auto-generated method stub
	}
	
	public static class Creator implements IViewCreator {
		
		@Override
		public IView createView() {
			return new TableOrdersManagerView();
		}
		
		@Override
		public ViewIdentifier getViewIdentifier() {
			return ViewIdentifier.TABLE_ORDERS_MANAGER;
		}
	}
}
