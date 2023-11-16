package org.makerminds.jcoaching.internship.restaurantpoint.gui.view.waiter;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.makerminds.jcoaching.internship.restaurantpoint.controller.LoginController;
import org.makerminds.jcoaching.internship.restaurantpoint.gui.AbstractView;
import org.makerminds.jcoaching.internship.restaurantpoint.gui.utils.OrderStatusIdentifierResolver;
import org.makerminds.jcoaching.internship.restaurantpoint.gui.utils.ViewIdentifierNameResolver;
import org.makerminds.jcoaching.internship.restaurantpoint.gui.view.IView;
import org.makerminds.jcoaching.internship.restaurantpoint.gui.view.IViewCreator;
import org.makerminds.jcoaching.internship.restaurantpoint.gui.view.OrderStatusIdentifierProvider;
import org.makerminds.jcoaching.internship.restaurantpoint.gui.view.ViewIdentifier;
import org.makerminds.jcoaching.internship.restaurantpoint.model.Order;
import org.makerminds.jcoaching.internship.restaurantpoint.model.OrderStatus;
import org.makerminds.jcoaching.internship.restaurantpoint.model.TableOrder;
import org.makerminds.jcoaching.internship.restaurantpoint.model.user.CookUser;
import org.makerminds.jcoaching.internship.restaurantpoint.model.user.UserRole;
import org.makerminds.jcoaching.internship.restaurantpoint.model.user.WaiterUser;

@SuppressWarnings("serial")
public class OrderStatusManagerView extends AbstractView implements IView {
	public OrderStatusManagerView() {
	}

	private JTable orderViewTable;
	private DefaultTableModel modelForOrderTable;
	private Map<Long, Order> allAvailableRestaurantOrders;

	@Override
	public void prepareView() {
		createHeadlineLabel();
		prepareOrderOverviewManagement();
		prepareButtons();
	}

	private void createHeadlineLabel() {
		String viewHeadlineLabelText = ViewIdentifierNameResolver
				.getViewIdentifierName(ViewIdentifier.ORDER_STATUS_MANAGER);
		prepareViewHeadline(viewHeadlineLabelText);
	}

	private void prepareOrderOverviewManagement() {
		prepareallAvailableRestaurantOrders();
		prepareOrderItemsTable();

		JScrollPane scrollPaneOrderOverview = new JScrollPane(orderViewTable);
		scrollPaneOrderOverview.setBackground(Color.WHITE);
		TitledBorder titledBorder = createTitledBorder("Order Overview");
		scrollPaneOrderOverview.setBorder(titledBorder);
		scrollPaneOrderOverview.setBounds(28, 50, 649, 260);
		add(scrollPaneOrderOverview);
	}

	private void prepareallAvailableRestaurantOrders() {
		allAvailableRestaurantOrders = new HashMap<Long, Order>();
		UserRole userRole = LoginController.getLoggedInUser().getUserRole();
		if (userRole.equals(UserRole.WAITER)) {
			WaiterUser waiterUser = (WaiterUser) LoginController.getLoggedInUser();
			Map<Integer, TableOrder> tableOrders = waiterUser.getTableOrders();
			for (Entry<Integer, TableOrder> tableOrderEntry : tableOrders.entrySet()) {
				TableOrder tableOrder = tableOrderEntry.getValue();
				addAllRelevantRestaurantOrdersToMap(tableOrder.getOrders());
			}
		} else {
			CookUser cookUser = (CookUser) LoginController.getLoggedInUser();
			Map<Integer, TableOrder> tableOrders = cookUser.getTableOrders();
			for (Entry<Integer, TableOrder> tableOrderEntry : tableOrders.entrySet()) {
				TableOrder tableOrder = tableOrderEntry.getValue();
				addAllRelevantRestaurantOrdersToMap(tableOrder.getOrders());
			}
		}
	}

	private void addAllRelevantRestaurantOrdersToMap(List<Order> allRelevantRestaurantOrdersList) {
		for (Order order : allRelevantRestaurantOrdersList) {
			allAvailableRestaurantOrders.put(order.getOrderNumber(), order);
		}
	}

	private void prepareOrderItemsTable() {
		orderViewTable = new JTable();
		modelForOrderTable = new DefaultTableModel();
		modelForOrderTable.setColumnIdentifiers(new Object[] { "Order", "Status" });
		prepareOrderItemsDataModel();
		orderViewTable.setShowGrid(false);
	}

	private void prepareOrderItemsDataModel() {
		// Remove everything
		// modelForOrderTable.setRowCount(0);

		// Populate JTable
		// getRestaurantOrders(LoginController.getInstance().getLoggedInUser().getRestaurantList(),
		// modelForOrderTable);

		// Set Model
		// Remove everything
		modelForOrderTable.setRowCount(0);

		// Populate JTable
		getRestaurantOrders(modelForOrderTable);

		orderViewTable.setModel(modelForOrderTable);
	}

	public void getRestaurantOrders(DefaultTableModel ordersTableModel) {
		UserRole userRole = LoginController.getLoggedInUser().getUserRole();
		if (userRole.equals(UserRole.WAITER)) {
			WaiterUser waiterUser = (WaiterUser) LoginController.getLoggedInUser();
			for (TableOrder tableOrder : waiterUser.getTableOrders().values()) {
				for (Order order : tableOrder.getOrders()) {
					if (prepareOrderForUserRole(waiterUser.getUserRole(), order.getOrderStatus())) {
						Object[] rowData = new Object[] { order.getOrderNumber(),
								OrderStatusIdentifierResolver.getOrderIdentifierName(order.getOrderStatus()) };
						ordersTableModel.addRow(rowData);
					}
				}
			}
		} else {
			CookUser cookUser = (CookUser) LoginController.getLoggedInUser();
			for (TableOrder tableOrder : cookUser.getTableOrders().values()) {
				for (Order order : tableOrder.getOrders()) {
					if (prepareOrderForUserRole(cookUser.getUserRole(), order.getOrderStatus())) {
						Object[] rowData = new Object[] { order.getOrderNumber(),
								OrderStatusIdentifierResolver.getOrderIdentifierName(order.getOrderStatus()) };
						ordersTableModel.addRow(rowData);
					}
				}
			}
		}
	}

	private void prepareNewOrderStatus(Order order) {
		OrderStatus currentOrderStatus = order.getOrderStatus();
		OrderStatus newOrderStatus = currentOrderStatus.next();
		order.setOrderStatus(newOrderStatus);
	}

	private void preparePrevOrderStatus(Order order) {
		OrderStatus currentOrderStatus = order.getOrderStatus();
		OrderStatus prevOrderStatus = currentOrderStatus.prev();
		order.setOrderStatus(prevOrderStatus);
	}

	private boolean prepareOrderForUserRole(UserRole userRole, OrderStatus orderStatus) {
		return OrderStatusIdentifierProvider.getOrderStatusForUserRole(userRole).stream()
				.anyMatch(t -> t.equals(orderStatus));
	}

	private void prepareButtons() {
		prepareUpdateStatusButton();
		prepareRevertStatusButton();
		prepareRefreshButton(modelForOrderTable);

	}

	private void prepareRefreshButton(DefaultTableModel modelForOrderTable) {
		JButton refreshButton = new JButton("Refresh");
		refreshButton.setForeground(Color.WHITE);
		refreshButton.setFont(GENERAL_LABEL_FONT);
		refreshButton.setBackground(CORPORATE_BLUE);
		refreshButton.setBounds(460, 500, 200, 40);
		refreshButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prepareOrderItemsDataModel();
				modelForOrderTable.fireTableDataChanged();
			}
		});
		add(refreshButton);
	}

	private void prepareUpdateStatusButton() {
		JButton updateStatusButton = new JButton("Update Status");
		updateStatusButton.setForeground(Color.WHITE);
		updateStatusButton.setFont(GENERAL_LABEL_FONT);
		updateStatusButton.setBackground(CORPORATE_BLUE);
		updateStatusButton.setBounds(40, 500, 200, 40);
		updateStatusButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = orderViewTable.getSelectedRow();
				Order order = allAvailableRestaurantOrders.get(orderViewTable.getValueAt(selectedRow, 0));
				prepareNewOrderStatus(order);
				orderViewTable.setValueAt(OrderStatusIdentifierResolver.getOrderIdentifierName(order.getOrderStatus()),
						selectedRow, 1);
			}
		});
		add(updateStatusButton);
	}

	private void prepareRevertStatusButton() {
		JButton revertStatusButton = new JButton("Revert Status");
		revertStatusButton.setForeground(Color.WHITE);
		revertStatusButton.setFont(GENERAL_LABEL_FONT);
		revertStatusButton.setBackground(CORPORATE_BLUE);
		revertStatusButton.setBounds(250, 500, 200, 40);
		revertStatusButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = orderViewTable.getSelectedRow();
				Order order = allAvailableRestaurantOrders.get(orderViewTable.getValueAt(selectedRow, 0));
				preparePrevOrderStatus(order);
				orderViewTable.setValueAt(OrderStatusIdentifierResolver.getOrderIdentifierName(order.getOrderStatus()),
						selectedRow, 1);
			}
		});
		add(revertStatusButton);
	}

	@Override
	public void refreshViewData() {
	}

	public static class Creator implements IViewCreator {

		@Override
		public IView createView() {
			return new OrderStatusManagerView();
		}

		@Override
		public ViewIdentifier getViewIdentifier() {
			return ViewIdentifier.ORDER_STATUS_MANAGER;
		}
	}
}
