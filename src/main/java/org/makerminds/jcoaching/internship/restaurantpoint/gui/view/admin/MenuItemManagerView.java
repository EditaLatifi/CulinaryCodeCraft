package org.makerminds.jcoaching.internship.restaurantpoint.gui.view.admin;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.junit.platform.commons.util.StringUtils;
import org.makerminds.jcoaching.internship.restaurantpoint.controller.LoginController;
import org.makerminds.jcoaching.internship.restaurantpoint.controller.MenuItemManagerController;
import org.makerminds.jcoaching.internship.restaurantpoint.gui.AbstractView;
import org.makerminds.jcoaching.internship.restaurantpoint.gui.utils.ViewIdentifierNameResolver;
import org.makerminds.jcoaching.internship.restaurantpoint.gui.view.IView;
import org.makerminds.jcoaching.internship.restaurantpoint.gui.view.IViewCreator;
import org.makerminds.jcoaching.internship.restaurantpoint.gui.view.ViewIdentifier;
import org.makerminds.jcoaching.internship.restaurantpoint.model.Menu;
import org.makerminds.jcoaching.internship.restaurantpoint.model.Restaurant;
import org.makerminds.jcoaching.internship.restaurantpoint.model.user.User;

@SuppressWarnings("serial")
public class MenuItemManagerView extends AbstractView implements IView {

	private MenuItemManagerController menuItemManagerController = new MenuItemManagerController();

	private DefaultTableModel modelForMenuItems;
	private Object[] rowForMenuItems;

	private JTextField menuItemIdTextfield;
	private JTextField menuItemNameTextfield;
	private JTextField menuItemPriceTextfield;

	private JComboBox<Restaurant> restaurantComboBox;
	private JComboBox<Menu> menuComboBox;

	private JTable menuItemsTable;

	public void prepareView() {
		createHeadlineLabel();
		createMenuItemManagementPanel();
		prepareRestaurantSelectionPanel();
		prepareMenuSelectionPanel();
		prepareItemsDataTableManagement();
	}

	public void createHeadlineLabel() {
		String viewHeadlineLabel = ViewIdentifierNameResolver.getViewIdentifierName(ViewIdentifier.MENU_ITEM_MANAGER);
		prepareViewHeadline(viewHeadlineLabel);
	}

	private void createMenuItemManagementPanel() {
		JPanel menuItemManagementPanel = new JPanel();
		TitledBorder titledBorder = createTitledBorder("Item Management Panel");
		menuItemManagementPanel.setBackground(Color.WHITE);
		menuItemManagementPanel.setBorder(titledBorder);
		menuItemManagementPanel.setBounds(0, 29, 316, 380);
		menuItemManagementPanel.setLayout(null);

		createMenuItemManagementInputComponents(menuItemManagementPanel);
		prepareMenuItemManagementButtons(menuItemManagementPanel);
		add(menuItemManagementPanel);
	}

	private void createMenuItemManagementInputComponents(JPanel menuItemManagementPanel) {
		JLabel menuItemIdLabel = createMenuItemIdLabel();
		JLabel menuItemNameLabel = createMenuItemNameLabel();
		JLabel menuItemPriceLabel = createMenuItemPriceLabel();

		createMenuItemIdTextfield();
		createMenuItemNameTextfield();
		createMenuItemPriceTextfield();
		createProductCategoryRadioButtonGroupPanel(menuItemManagementPanel);

		menuItemManagementPanel.add(menuItemIdLabel);
		menuItemManagementPanel.add(menuItemIdTextfield);
		menuItemManagementPanel.add(menuItemNameLabel);
		menuItemManagementPanel.add(menuItemNameTextfield);
		menuItemManagementPanel.add(menuItemPriceLabel);
		menuItemManagementPanel.add(menuItemPriceTextfield);
	}

	private void createProductCategoryRadioButtonGroupPanel(JPanel menuItemManagementPanel) {
		JPanel productCategoryRadioButtonGroupPanel = new JPanel();
		productCategoryRadioButtonGroupPanel.setBackground(Color.WHITE);
		productCategoryRadioButtonGroupPanel.setBounds(10, 250, 133, 55);
		TitledBorder titledBorder = createTitledBorder("Product Category");
		productCategoryRadioButtonGroupPanel.setBorder(titledBorder);

		JRadioButton productCategoryMealRadioButton = new JRadioButton("Meal");
		productCategoryMealRadioButton.setBackground(Color.WHITE);
		JRadioButton productCategoryDrinkRadioButton = new JRadioButton("Drink");
		productCategoryDrinkRadioButton.setBackground(Color.WHITE);

		ButtonGroup createProductCategoryRadioButtonGroup = new ButtonGroup();
		createProductCategoryRadioButtonGroup.add(productCategoryMealRadioButton);
		createProductCategoryRadioButtonGroup.add(productCategoryDrinkRadioButton);

		productCategoryRadioButtonGroupPanel.add(productCategoryMealRadioButton);
		productCategoryRadioButtonGroupPanel.add(productCategoryDrinkRadioButton);
		menuItemManagementPanel.add(productCategoryRadioButtonGroupPanel);
	}

	private void createMenuItemIdTextfield() {
		menuItemIdTextfield = new JTextField();
		menuItemIdTextfield.setFont(new Font("Arial", Font.PLAIN, 13));
		menuItemIdTextfield.setBounds(10, 66, 133, 35);
		menuItemIdTextfield.setColumns(10);
		menuItemIdTextfield.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume();
				}
			}

		});

	}

	private void createMenuItemNameTextfield() {
		menuItemNameTextfield = new JTextField();
		menuItemNameTextfield.setFont(new Font("Arial", Font.PLAIN, 13));
		menuItemNameTextfield.setBounds(10, 124, 133, 35);
		menuItemNameTextfield.setColumns(10);
	}

	private void createMenuItemPriceTextfield() {
		menuItemPriceTextfield = new JTextField();
		menuItemPriceTextfield.setFont(new Font("Arial", Font.PLAIN, 13));
		menuItemPriceTextfield.setBounds(10, 186, 133, 35);
		menuItemPriceTextfield.setColumns(10);
		menuItemPriceTextfield.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c) && c != '.') {
					e.consume();
				}
			}

		});
	}

	private JLabel createMenuItemIdLabel() {
		JLabel restaurantNameLabel = new JLabel("Menu Item id");
		restaurantNameLabel.setFont(new Font("Arial", Font.BOLD, 13));
		restaurantNameLabel.setBounds(10, 47, 91, 21);
		return restaurantNameLabel;
	}

	private JLabel createMenuItemNameLabel() {
		JLabel menuItemNameLabel = new JLabel("Menu Item name");
		menuItemNameLabel.setFont(new Font("Arial", Font.BOLD, 13));
		menuItemNameLabel.setBounds(10, 99, 133, 36);
		return menuItemNameLabel;
	}

	private JLabel createMenuItemPriceLabel() {
		JLabel menuItemPriceLabel = new JLabel("Menu Item price");
		menuItemPriceLabel.setFont(new Font("Arial", Font.BOLD, 13));
		menuItemPriceLabel.setBounds(10, 169, 133, 21);
		return menuItemPriceLabel;
	}

	private void prepareRestaurantSelectionPanel() {
		JPanel restaurantSelectionPanel = new JPanel();
		TitledBorder titledBorder = createTitledBorder("Restaurant Selection");
		restaurantSelectionPanel.setBackground(Color.WHITE);
		restaurantSelectionPanel.setBorder(titledBorder);
		restaurantSelectionPanel.setBounds(358, 29, 317, 50);
		restaurantSelectionPanel.setLayout(new BoxLayout(restaurantSelectionPanel, BoxLayout.Y_AXIS));

		createRestaurantComboBox();

		restaurantSelectionPanel.add(restaurantComboBox);
		add(restaurantSelectionPanel);
	}

	private void createRestaurantComboBox() {
		restaurantComboBox = new JComboBox<>();
		restaurantComboBox.setBackground(Color.WHITE);
		prepareRestaurantComboBoxData();
		restaurantComboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					modelForMenuItems.setRowCount(0);
					prepareMenuComboBoxData();
					clearTextfieldsContent();
				}
			}
		});
	}

	private void prepareRestaurantComboBoxData() {
		restaurantComboBox.removeAllItems();
		User loggedInUser = LoginController.getLoggedInUser();
		for (Restaurant restaurant : loggedInUser.getRestaurantList()) {
			restaurantComboBox.addItem(restaurant);
		}
	}

	private void prepareMenuSelectionPanel() {
		JPanel menuSelectionPanel = new JPanel();
		TitledBorder titledBorder = createTitledBorder("Menu Selection");
		menuSelectionPanel.setBackground(Color.WHITE);
		menuSelectionPanel.setBorder(titledBorder);
		menuSelectionPanel.setBounds(358, 89, 317, 50);
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
					clearTextfieldsContent();
				}
			}
		});
	}

	private void prepareMenuComboBoxData() {
		menuComboBox.removeAllItems();
		Restaurant selectedRestaurant = (Restaurant) restaurantComboBox.getSelectedItem();
		if (selectedRestaurant == null) {
			User loggedInUser = LoginController.getLoggedInUser();
			selectedRestaurant = loggedInUser.getRestaurantList().get(0);
		}

		for (Menu menu : selectedRestaurant.getMenuList()) {
			menuComboBox.addItem(menu);
		}
	}

	private boolean isDataToAddMenuItemValid() {
		String menuItemIdTextfieldContent = menuItemIdTextfield.getText();
		String menuItemNameTextfieldContent = menuItemNameTextfield.getText();
		String menuItemPriceTextfieldContent = menuItemPriceTextfield.getText();

		return StringUtils.isNotBlank(menuItemIdTextfieldContent)
				&& StringUtils.isNotBlank(menuItemNameTextfieldContent)
				&& StringUtils.isNotBlank(menuItemPriceTextfieldContent);
	}

	public void prepareMenuItemManagementButtons(JPanel itemManagementPanel) {
		JButton addButton = createAddItemToMenuButton();
		JButton updateButton = createUpdateItemInMenuButton();
		JButton deleteButton = createDeleteItemFromMenuButton();
		itemManagementPanel.add(deleteButton);
		itemManagementPanel.add(updateButton);
		itemManagementPanel.add(addButton);
	}

	public void prepareItemsDataTableManagement() {
		prepareMenuItemsDataTable();
		JScrollPane scrollPaneMenuItems = new JScrollPane(menuItemsTable);
		scrollPaneMenuItems.setBackground(Color.WHITE);
		TitledBorder titledBorder = createTitledBorder("Item List");
		scrollPaneMenuItems.setBorder(titledBorder);
		scrollPaneMenuItems.setBounds(358, 149, 317, 260);
		add(scrollPaneMenuItems);
	}

	private void prepareMenuItemsDataTable() {
		menuItemsTable = new JTable();
		prepareItemTableDataModel();
		menuItemsTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int selectedRowIndex = menuItemsTable.getSelectedRow();
				menuItemIdTextfield.setText(menuItemsTable.getValueAt(selectedRowIndex, 0).toString());
				menuItemNameTextfield.setText(menuItemsTable.getValueAt(selectedRowIndex, 1).toString());
				menuItemPriceTextfield.setText(menuItemsTable.getValueAt(selectedRowIndex, 2).toString());
			}
		});

	}

	private void prepareItemTableDataModel() {
		modelForMenuItems = new DefaultTableModel();
		Object[] menuItemsColumnName = { "Product Key", "Product Name", "Product Price" };
		modelForMenuItems.setColumnIdentifiers(menuItemsColumnName);
		Menu selectedMenu = (Menu) menuComboBox.getSelectedItem();
		prepareMenuItemsDataModel(selectedMenu);
		menuItemsTable.setShowGrid(false);
	}

	public void prepareMenuItemsDataModel(Menu menu) {
		rowForMenuItems = new Object[3];
		menu.getMenuItems().forEach((key, value) -> {
			rowForMenuItems[0] = value.getProductId();
			rowForMenuItems[1] = value.getName();
			rowForMenuItems[2] = value.getPrice();
			modelForMenuItems.addRow(rowForMenuItems);
		});
		menuItemsTable.setModel(modelForMenuItems);
	}

	// Add / Update / Remove operations
	private JButton createAddItemToMenuButton() {
		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!isDataToAddMenuItemValid()) {
					JOptionPane.showMessageDialog(null, "Provide the mandatory data to add a new menu item.");
				} else if (menuItemsTable != null) {
					String[] menuItemAsArray = { menuItemIdTextfield.getText(), menuItemNameTextfield.getText(),
							menuItemPriceTextfield.getText() };

					// add item to menu items list
					Menu selectedMenu = (Menu) menuComboBox.getSelectedItem();
					menuItemManagerController.addMenuItemArrayToMenu(menuItemAsArray, selectedMenu);

					// then add Item to Table
					modelForMenuItems.addRow(menuItemAsArray);

					// Then clear textfields
					clearTextfieldsContent();
				}
			}
		});
		addButton.setBounds(10, 318, 91, 36);
		addButton.setFont(GENERAL_LABEL_FONT);
		addButton.setBackground(CORPORATE_BLUE);
		addButton.setForeground(Color.WHITE);
		return addButton;
	}

	private JButton createUpdateItemInMenuButton() {
		JButton updateButton = new JButton("Update");
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedMenuItem = menuItemsTable.getSelectedRow();
				if (selectedMenuItem <= -1) {
					JOptionPane.showMessageDialog(null, "Selektoni pastaj beni update");
				} else {
					String[] menuItemAsArray = { menuItemIdTextfield.getText(), menuItemNameTextfield.getText(),
							menuItemPriceTextfield.getText() };

					// Update item row values on Table
					modelForMenuItems.setValueAt(menuItemIdTextfield.getText(), selectedMenuItem, 0);
					modelForMenuItems.setValueAt(menuItemNameTextfield.getText(), selectedMenuItem, 1);
					modelForMenuItems.setValueAt(menuItemPriceTextfield.getText(), selectedMenuItem, 2);

					// Update Item in Menu List
					Menu selectedMenu = (Menu) menuComboBox.getSelectedItem();
					
					// get product id from table column (index = 0)
					Integer productId = Integer.valueOf((String) menuItemsTable.getValueAt(menuItemsTable.getSelectedRow(), 0));
					menuItemManagerController.updateMenuItem(menuItemAsArray, selectedMenu,
							productId);

					// Then clear textfields
					clearTextfieldsContent();
				}
			}
		});
		updateButton.setBounds(111, 318, 91, 36);
		updateButton.setFont(GENERAL_LABEL_FONT);
		updateButton.setBackground(CORPORATE_BLUE);
		updateButton.setForeground(Color.WHITE);
		return updateButton;
	}

	private JButton createDeleteItemFromMenuButton() {
		JButton deleteButton = new JButton("Remove");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedMenuItemRow = menuItemsTable.getSelectedRow();
				if (selectedMenuItemRow >= 0) {
					// Remove Item from Menu List first
					menuItemManagerController.removeMenuItem((Menu) menuComboBox.getSelectedItem(),
							Integer.parseInt((String) menuItemsTable.getValueAt(selectedMenuItemRow, 0)));

					// Then remove row
					modelForMenuItems.removeRow(selectedMenuItemRow);
				} else {
					JOptionPane.showMessageDialog(null, "Select any row to delete");
				}
			}
		});
		deleteButton.setBounds(215, 318, 91, 36);
		deleteButton.setFont(GENERAL_LABEL_FONT);
		deleteButton.setBackground(CORPORATE_BLUE);
		deleteButton.setForeground(Color.WHITE);
		return deleteButton;
	}

	private void clearTextfieldsContent() {
		menuItemIdTextfield.setText("");
		menuItemNameTextfield.setText("");
		menuItemPriceTextfield.setText("");
	}

	@Override
	public void refreshViewData() {
		prepareRestaurantComboBoxData();
		prepareMenuComboBoxData();
	}
	
	public static class Creator implements IViewCreator {
		
		@Override
		public IView createView() {
			return new MenuItemManagerView();
		}
		
		@Override
		public ViewIdentifier getViewIdentifier() {
			return ViewIdentifier.MENU_ITEM_MANAGER;
		}
	}
}