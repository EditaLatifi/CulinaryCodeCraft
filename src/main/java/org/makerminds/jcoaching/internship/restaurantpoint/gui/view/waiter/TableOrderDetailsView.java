package org.makerminds.jcoaching.internship.restaurantpoint.gui.view.waiter;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.makerminds.jcoaching.internship.restaurantpoint.controller.LoginController;
import org.makerminds.jcoaching.internship.restaurantpoint.controller.TableOrdersManagerController;
import org.makerminds.jcoaching.internship.restaurantpoint.controller.order.OrderNumberGenerator;
import org.makerminds.jcoaching.internship.restaurantpoint.gui.AbstractView;
import org.makerminds.jcoaching.internship.restaurantpoint.gui.utils.ViewIdentifierNameResolver;
import org.makerminds.jcoaching.internship.restaurantpoint.gui.view.IView;
import org.makerminds.jcoaching.internship.restaurantpoint.gui.view.ViewIdentifier;
import org.makerminds.jcoaching.internship.restaurantpoint.model.Menu;
import org.makerminds.jcoaching.internship.restaurantpoint.model.Order;
import org.makerminds.jcoaching.internship.restaurantpoint.model.OrderStatus;
import org.makerminds.jcoaching.internship.restaurantpoint.model.Restaurant;
import org.makerminds.jcoaching.internship.restaurantpoint.model.Table;
import org.makerminds.jcoaching.internship.restaurantpoint.model.TableOrder;
import org.makerminds.jcoaching.internship.restaurantpoint.model.product.Product;
import org.makerminds.jcoaching.internship.restaurantpoint.model.user.User;
import org.makerminds.jcoaching.internship.restaurantpoint.model.user.WaiterUser;

@SuppressWarnings("serial")
public class TableOrderDetailsView extends AbstractView implements IView {
	private TableOrdersManagerController tableOrdersController = new TableOrdersManagerController();
	private Table selectedTable;
	private JLabel invoiceLabel;

	public TableOrderDetailsView(Table table) {
		selectedTable = table;
		prepareOrderSelectionPanel();
		prepareOrderOverviewManagement();
	}

	private JComboBox<Menu> menuComboBox;
	private JComboBox<Order> orderComboBox;
	private DefaultTableModel modelForMenuItems;
	private JTable menuItemsTable;
	private JList<Product> orderItemsList;
	private DefaultListModel<Product> modelForOrderItemsList;
	private List<Product> productList = new ArrayList<>();

	@Override
	public void prepareView() {
		createHeadlineLabel();
		prepareMenuSelectionPanel();
		prepareMenuItemsTable();
		prepareOrderItemManagementButtons();
		add(createInvoiceTablePanel());
	}

	private void createHeadlineLabel() {
		String viewHeadlineLabelText = ViewIdentifierNameResolver
				.getViewIdentifierName(ViewIdentifier.TABLE_ORDER_DETAILS);
		prepareViewHeadline(viewHeadlineLabelText);
	}

	private void prepareMenuSelectionPanel() {
		JPanel menuSelectionPanel = new JPanel();
		TitledBorder titledBorder = createTitledBorder("Menu Selection");
		menuSelectionPanel.setBackground(Color.WHITE);
		menuSelectionPanel.setBorder(titledBorder);
		menuSelectionPanel.setBounds(10, 76, 270, 50);
		menuSelectionPanel.setLayout(new BoxLayout(menuSelectionPanel, BoxLayout.Y_AXIS));

		createMenuComboBox();

		menuSelectionPanel.add(menuComboBox);
		add(menuSelectionPanel);
	}

	private void createMenuComboBox() {
		menuComboBox = new JComboBox<>();
		menuComboBox.setBackground(Color.WHITE);
		prepareMenuComboBoxData();
		menuComboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					modelForMenuItems.setRowCount(0);
					Menu selectedMenu = (Menu) menuComboBox.getSelectedItem();
					prepareMenuItemsDataModel(selectedMenu);
				}
			}
		});
	}

	private void prepareMenuComboBoxData() {
		menuComboBox.removeAllItems();
		User loggedInUser = LoginController.getLoggedInUser();
		Restaurant selectedRestaurant = loggedInUser.getRestaurantList().get(0);
		selectedRestaurant.getMenuList().forEach(menuComboBox::addItem);
	}

	public void prepareMenuItemsTable() {
		prepareMenuItemsDataTable();
		JScrollPane scrollPaneMenuItems = new JScrollPane(menuItemsTable);
		scrollPaneMenuItems.setBackground(Color.WHITE);
		TitledBorder titledBorder = createTitledBorder("Item List");
		scrollPaneMenuItems.setBorder(titledBorder);
		scrollPaneMenuItems.setBounds(10, 136, 270, 260);
		add(scrollPaneMenuItems);
	}

	private void prepareMenuItemsDataTable() {
		menuItemsTable = new JTable();
		modelForMenuItems = new DefaultTableModel();
		modelForMenuItems.setColumnIdentifiers(new Object[] { "Product" });
		prepareMenuItemsDataModel((Menu) menuComboBox.getSelectedItem());
		menuItemsTable.setShowGrid(false);
	}

	public void prepareMenuItemsDataModel(Menu menu) {
		menu.getMenuItems().forEach((key, value) -> {
			modelForMenuItems.addRow(new Object[] { value.getName() });
		});
		menuItemsTable.setModel(modelForMenuItems);
	}

	private void prepareOrderSelectionPanel() {
		JPanel orderSelectionPanel = new JPanel();
		TitledBorder titledBorder = createTitledBorder("Order Selection");
		orderSelectionPanel.setBackground(Color.WHITE);
		orderSelectionPanel.setBorder(titledBorder);
		orderSelectionPanel.setBounds(300, 76, 270, 50);
		orderSelectionPanel.setLayout(new BoxLayout(orderSelectionPanel, BoxLayout.Y_AXIS));

		createOrderCombobox();
		orderSelectionPanel.add(orderComboBox);
		add(orderSelectionPanel);
	}

	private void createOrderCombobox() {
		orderComboBox = new JComboBox<>();
		orderComboBox.setBackground(Color.WHITE);
		prepareOrderComboBoxData();
		orderComboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					Order order = (Order) orderComboBox.getSelectedItem();
					prepareOrderSelectionDataModel(order);
				}
			}
		});
	}

	private void prepareOrderComboBoxData() {
		orderComboBox.removeAll();
		WaiterUser waiterUser = (WaiterUser) LoginController.getLoggedInUser();
		TableOrder tableOrder = waiterUser.getTableOrder(selectedTable.getTableNumber());
		List<Order> orders = tableOrder.getOrders();
		for (Order o : orders) {
			if (o.getOrderStatus() != OrderStatus.PAID) {
				orderComboBox.addItem(o);
			}
		}
	}

	private void prepareOrderSelectionDataModel(Order order) {
		modelForOrderItemsList.removeAllElements();
		if (order.getOrderStatus() != OrderStatus.PAID) {
			order.getOrderItems().forEach(modelForOrderItemsList::addElement);
		}
	}

	private void prepareOrderOverviewManagement() {
		prepareOrderItemsList();

		JScrollPane scrollPaneOrderItems = new JScrollPane(orderItemsList);
		scrollPaneOrderItems.setBackground(Color.WHITE);
		TitledBorder titledBorder = createTitledBorder("Order Items");
		scrollPaneOrderItems.setBorder(titledBorder);
		scrollPaneOrderItems.setBounds(300, 136, 270, 260);
		add(scrollPaneOrderItems);
	}

	private void prepareOrderItemsList() {
		modelForOrderItemsList = new DefaultListModel<>();
		orderItemsList = new JList<>(modelForOrderItemsList);
		prepareOrderItemsDataModel();
	}

	private void prepareOrderItemsDataModel() {
		WaiterUser waiterUser = (WaiterUser) LoginController.getLoggedInUser();
		TableOrder tableOrder = waiterUser.getTableOrder(selectedTable.getTableNumber());
		if (tableOrder != null) {
			for (Order order : tableOrder.getOrders())
				if (order.getOrderStatus() != OrderStatus.PAID) {
					setProductList(order.getOrderItems());
					order.getOrderItems().forEach(modelForOrderItemsList::addElement);
				}
		}
	}

	private void prepareOrderItemManagementButtons() {
		JButton addButton = createAddItemToOrderButton();
		JButton deleteButton = createDeleteItemFromOrderButton();
		JButton orderButton = createOrderButton();
		JButton printButton = createPrintInvoiceButton();
		add(printButton);
		add(deleteButton);
		add(orderButton);
		add(addButton);
	}

	private JButton createPrintInvoiceButton() {
		JButton printButton = new JButton("Print Invoice");
		printButton.setForeground(Color.WHITE);
		printButton.setFont(GENERAL_LABEL_FONT);
		printButton.setBackground(CORPORATE_BLUE);
		printButton.setBounds(670, 516, 140, 36);
		printButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WaiterUser waiterUser = (WaiterUser) LoginController.getLoggedInUser();
				TableOrder tableOrder = waiterUser.getTableOrder(selectedTable.getTableNumber());
				productList = tableOrdersController.proccessOrderToPayment(tableOrder.getOrders());
				double subTotalValue = 0.0;
				DecimalFormat decimalFormat = new DecimalFormat("#.00");
				String invoiceText = "<html><br>";
				invoiceText = invoiceText +"-----------------------------------------<br>";
				for (Product product : productList) {
					invoiceText = invoiceText + product.getName() + "&nbsp;&nbsp;|&nbsp;&nbsp;" + decimalFormat.format(product.getPrice()) + "€<br>";
					subTotalValue += product.getPrice();
				}
				invoiceText = invoiceText +"----------------------------------------<br>";
				invoiceText = invoiceText + "SubTotalValue: " + decimalFormat.format(subTotalValue) + "€<br>";
				double vatValue = subTotalValue * 0.18;
				invoiceText = invoiceText + "VatValue: " + decimalFormat.format(vatValue) + "€<br>";
				double totalAmount = subTotalValue + vatValue;
				invoiceText = invoiceText + "totalAmount: " + decimalFormat.format(totalAmount) + "€<br>";

				invoiceLabel.setText(invoiceText);
			}

		});
		return printButton;
	}

	private JButton createOrderButton() {
		JButton orderButton = new JButton("Order");
		orderButton.setForeground(Color.WHITE);
		orderButton.setFont(GENERAL_LABEL_FONT);
		orderButton.setBackground(CORPORATE_BLUE);
		orderButton.setBounds(460, 516, 140, 36);
		orderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object order = orderComboBox.getSelectedItem();
				if (getProductList().isEmpty()) {
					JOptionPane.showMessageDialog(null, "You didn't add any product to your new order!", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				long orderNumber = OrderNumberGenerator.getOrderNumber();
				if (order != null) {
					((Order) order).setOrderItems(getProductList());
				} else {
					order = new Order(orderNumber, getProductList());
					((Order) order).setOrderStatus(OrderStatus.READY);

					// Add Order to TableOrder
					WaiterUser waiterUser = (WaiterUser) LoginController.getLoggedInUser();
					TableOrder tableOrder = waiterUser.getTableOrder(selectedTable.getTableNumber());
					tableOrder.getOrders().add((Order) order);

					// reset orderCombobox
					orderComboBox.addItem((Order) order);
				}

				// clear OrderItemsList
				// modelForOrderItemsList.removeAllElements();

				// FIX
				productList = new ArrayList<>();
				JOptionPane.showMessageDialog(null, "Your order has been recieved");
			}
		});
		return orderButton;
	}

	private JButton createAddItemToOrderButton() {
		JButton addButton = new JButton("Add");
		addButton.setBounds(37, 516, 140, 36);
		addButton.setFont(GENERAL_LABEL_FONT);
		addButton.setBackground(CORPORATE_BLUE);
		addButton.setForeground(Color.WHITE);
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (menuItemsTable.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "You should select a product before adding.");
				} else {
					// Get product from hashmap
					Product product = tableOrdersController.prepareItemToAdd((Menu) menuComboBox.getSelectedItem(),
							(String) menuItemsTable.getValueAt(menuItemsTable.getSelectedRow(), 0));

					// Add product to ProductList
					getProductList().add(product);

					// Finally, add product to orderItemsList
					modelForOrderItemsList.addElement(product);
				}
			}
		});
		return addButton;
	}

	private JButton createDeleteItemFromOrderButton() {
		JButton deleteButton = new JButton("Remove");
		deleteButton.setBounds(255, 516, 140, 36);
		deleteButton.setFont(GENERAL_LABEL_FONT);
		deleteButton.setBackground(CORPORATE_BLUE);
		deleteButton.setForeground(Color.WHITE);
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = orderItemsList.getSelectedIndex();
				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(null, "You should select a product before removing.");
				} else {
					// Remove product from productList
					getProductList().remove(selectedRow);

					// then delete the affected row
					modelForOrderItemsList.remove(selectedRow);
				}
			}
		});
		return deleteButton;
	}

	private JPanel createInvoiceTablePanel() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(600, 125, 260, 270);
		panel.setBorder(BorderFactory.createTitledBorder("Invoice"));
		panel.add(createInvoiceLableScrollPane());
		return panel;
	}

	private JScrollPane createInvoiceLableScrollPane() {
		invoiceLabel = new JLabel("The invoice will be printed here.");
		JScrollPane labelScrollPane = new JScrollPane(invoiceLabel);
		invoiceLabel.setBounds(100, 125, 250, 280);
		labelScrollPane.setBounds(10, 20, 220, 250);
		return labelScrollPane;

	}

	@Override
	public void refreshViewData() {
	}

	private List<Product> getProductList() {
		return productList;
	}

	private void setProductList(List<Product> productList) {
		this.productList = productList;
	}

}
