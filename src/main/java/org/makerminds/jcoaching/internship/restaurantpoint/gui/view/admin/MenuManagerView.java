package org.makerminds.jcoaching.internship.restaurantpoint.gui.view.admin;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import org.junit.platform.commons.util.StringUtils;
import org.makerminds.jcoaching.internship.restaurantpoint.controller.LoginController;
import org.makerminds.jcoaching.internship.restaurantpoint.controller.MenuManagerController;
import org.makerminds.jcoaching.internship.restaurantpoint.gui.AbstractView;
import org.makerminds.jcoaching.internship.restaurantpoint.gui.utils.ViewIdentifierNameResolver;
import org.makerminds.jcoaching.internship.restaurantpoint.gui.view.IView;
import org.makerminds.jcoaching.internship.restaurantpoint.gui.view.IViewCreator;
import org.makerminds.jcoaching.internship.restaurantpoint.gui.view.ViewIdentifier;
import org.makerminds.jcoaching.internship.restaurantpoint.model.Menu;
import org.makerminds.jcoaching.internship.restaurantpoint.model.Restaurant;
import org.makerminds.jcoaching.internship.restaurantpoint.model.user.User;

@SuppressWarnings("serial")
public class MenuManagerView extends AbstractView implements IView {

	private MenuManagerController menuManagerController = new MenuManagerController();

	private JList<Menu> menuDataList;
	private DefaultListModel<Menu> menuDataListModel;

	private JTextField menuNameTextfield;

	private JComboBox<Restaurant> restaurantComboBox;

	@Override
	public void prepareView() {
		createHeadlineLabel();
		createMenuManagementPanel();
		prepareRestaurantSelectionPanel();
		createMenuListPanel();
	}

	private void createHeadlineLabel() {
		String viewHeadlineLabelText = ViewIdentifierNameResolver.getViewIdentifierName(ViewIdentifier.MENU_MANAGER);
		prepareViewHeadline(viewHeadlineLabelText);
	}

	private void createMenuManagementPanel() {
		JPanel menuManagementPanel = new JPanel();
		TitledBorder titledBorder = createTitledBorder("Management Panel");
		menuManagementPanel.setBackground(Color.WHITE);
		menuManagementPanel.setBorder(titledBorder);
		menuManagementPanel.setBounds(31, 128, 316, 264);
		menuManagementPanel.setLayout(null);

		createMenuManagementInputComponents(menuManagementPanel);
		createMenuManagementButtons(menuManagementPanel);

		menuManagementPanel.add(menuNameTextfield);
		add(menuManagementPanel);
	}

	private JLabel createMenuNameLabel() {
		JLabel menuNameLabel = new JLabel("Menu name");
		menuNameLabel.setBounds(10, 47, 91, 21);
		menuNameLabel.setFont(new Font("Arial", Font.BOLD, 13));
		menuNameLabel.setHorizontalAlignment(SwingConstants.LEFT);
		return menuNameLabel;
	}

	private void createMenuManagementInputComponents(JPanel menuManagementPanel) {
		JLabel menuNameLabel = createMenuNameLabel();
		createMenuNameTextfield();

		// add input components to menu management panel
		menuManagementPanel.add(menuNameLabel);
		menuManagementPanel.add(menuNameTextfield);
	}

	private void createMenuNameTextfield() {
		menuNameTextfield = new JTextField();
		menuNameTextfield.setBounds(10, 68, 293, 31);
		menuNameTextfield.setColumns(10);
		menuNameTextfield.setFont(new Font("Arial", Font.PLAIN, 13));
	}

	private void prepareRestaurantSelectionPanel() {
		JPanel restaurantSelectionPanel = new JPanel();
		TitledBorder titledBorder = createTitledBorder("Restaurant Selection");
		restaurantSelectionPanel.setBackground(Color.WHITE);
		restaurantSelectionPanel.setBorder(titledBorder);
		restaurantSelectionPanel.setBounds(357, 128, 317, 50);
		restaurantSelectionPanel.setLayout(new BoxLayout(restaurantSelectionPanel, BoxLayout.Y_AXIS));

		createRestaurantComboBox();

		restaurantSelectionPanel.add(restaurantComboBox);
		add(restaurantSelectionPanel);
	}

	private void createMenuListPanel() {
		createMenuDataList();
		JScrollPane menuListScrollPane = new JScrollPane(menuDataList);
		TitledBorder titledBorder = createTitledBorder("Menu List");
		menuListScrollPane.setBackground(Color.WHITE);
		menuListScrollPane.setBounds(357, 189, 317, 203);
		menuListScrollPane.setBorder(titledBorder);

		add(menuListScrollPane);
	}

	public void createMenuManagementButtons(JPanel menuManagementPanel) {
		JButton addMenuButton = createAddMenuButton();
		JButton updateMenuButton = createUpdateMenuButton();
		JButton removeMenuButton = createRemoveMenuButton();

		menuManagementPanel.add(addMenuButton);
		menuManagementPanel.add(updateMenuButton);
		menuManagementPanel.add(removeMenuButton);
	}

	private void createRestaurantComboBox() {
		restaurantComboBox = new JComboBox<>();
		prepareRestaurantComboBoxData();
		restaurantComboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					Restaurant selectedItem = (Restaurant) restaurantComboBox.getSelectedItem();
					updateMenuDataListModel(selectedItem);
					menuNameTextfield.setText("");
				}
			}
		});

		restaurantComboBox.setBackground(Color.WHITE);
	}

	private void prepareRestaurantComboBoxData() {
		restaurantComboBox.removeAllItems();
		User loggedInUser = LoginController.getLoggedInUser();
		for (Restaurant restaurant : loggedInUser.getRestaurantList()) {
			restaurantComboBox.addItem(restaurant);
		}
	}

	private void createMenuDataList() {
		createMenuDataListModel();
		menuDataList = new JList<>(menuDataListModel);
		menuDataList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Menu selectedMenu = menuDataList.getSelectedValue();
				menuNameTextfield.setText(selectedMenu.getName());
			}
		});
	}

	private void createMenuDataListModel() {
		menuDataListModel = new DefaultListModel<>();
		User loggedInUser = LoginController.getLoggedInUser();
		Restaurant restaurant = loggedInUser.getRestaurantList().get(0);
		updateMenuDataListModel(restaurant);
	}

	private void updateMenuDataListModel(Restaurant restaurant) {
		menuDataListModel.removeAllElements();
		List<Menu> menuList = restaurant.getMenuList();
		for (Menu menu : menuList) {
			menuDataListModel.addElement(menu);
		}
	}

	// Add / Update / Remove operations

	private JButton createAddMenuButton() {
		JButton addMenuButton = new JButton("Add");
		addMenuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((StringUtils.isBlank(menuNameTextfield.getText()))) {
					JOptionPane.showMessageDialog(null, "Provide the mandatory data to add a new menu.");
				} else {
					String[] singleMenuNameArray = { menuNameTextfield.getText() };
					Menu newMenu = new Menu(singleMenuNameArray[0]);
					menuDataListModel.addElement(newMenu);
					menuNameTextfield.setText("");
					addToMenuDataModel(singleMenuNameArray[0]);
				}
			}

		});
		addMenuButton.setBounds(10, 217, 91, 36);
		addMenuButton.setFont(GENERAL_LABEL_FONT);
		addMenuButton.setBackground(CORPORATE_BLUE);
		addMenuButton.setForeground(Color.WHITE);
		return addMenuButton;
	}

	private void addToMenuDataModel(String menuName) {
		Restaurant selectedRestaurant = (Restaurant) restaurantComboBox.getSelectedItem();
		menuManagerController.addMenuToRestaurant(menuName, selectedRestaurant);
	}

	private JButton createUpdateMenuButton() {
		JButton updateMenuButton = new JButton("Update");
		updateMenuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu selectedMenu = menuDataList.getSelectedValue();
				if (selectedMenu == null) {
					JOptionPane.showMessageDialog(null, "Select a menu first that should be updated. ");
				} else {
					String newMenuName = menuNameTextfield.getText();
					String currentMenuName = selectedMenu.getName();
					// update data model first
					updateMenuInDataModel(currentMenuName, newMenuName);
					// then update data list element
					selectedMenu.setMenuName(newMenuName);
					// refresh the data list model after update
					menuDataList.setModel(menuDataListModel);
					// clear text field content
					menuNameTextfield.setText("");
				}
			}
		});
		updateMenuButton.setBounds(111, 217, 91, 36);
		updateMenuButton.setFont(GENERAL_LABEL_FONT);
		updateMenuButton.setBackground(CORPORATE_BLUE);
		updateMenuButton.setForeground(Color.WHITE);
		return updateMenuButton;
	}

	private void updateMenuInDataModel(String oldMenuName, String newMenuName) {
		Restaurant selectedRestaurant = (Restaurant) restaurantComboBox.getSelectedItem();
		menuManagerController.updateNameMenuInRestaurant(oldMenuName, newMenuName, selectedRestaurant);
	}

	private JButton createRemoveMenuButton() {
		JButton removeMenuButton = new JButton("Remove");
		removeMenuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = menuDataList.getSelectedIndex();
				if (selectedIndex == -1) {
					JOptionPane.showMessageDialog(null, "Select a menu that should be removed.");
				} else {
					removeMenuFromDataModel(menuDataList.getSelectedValue());
					menuDataListModel.remove(selectedIndex);
					menuNameTextfield.setText("");
				}
			}
		});
		removeMenuButton.setBounds(212, 217, 91, 36);
		removeMenuButton.setFont(GENERAL_LABEL_FONT);
		removeMenuButton.setBackground(CORPORATE_BLUE);
		removeMenuButton.setForeground(Color.WHITE);
		return removeMenuButton;

	}

	private void removeMenuFromDataModel(Menu menuToRemove) {
		Restaurant selectedRestaurant = (Restaurant) restaurantComboBox.getSelectedItem();
		menuManagerController.removeMenuFromRestaurant(menuToRemove, selectedRestaurant);
	}

	@Override
	public void refreshViewData() {
		prepareRestaurantComboBoxData();
	}
	
	public static class Creator implements IViewCreator {
		
		@Override
		public IView createView() {
			return new MenuManagerView();
		}
		
		@Override
		public ViewIdentifier getViewIdentifier() {
			return ViewIdentifier.MENU_MANAGER;
		}
	}
}