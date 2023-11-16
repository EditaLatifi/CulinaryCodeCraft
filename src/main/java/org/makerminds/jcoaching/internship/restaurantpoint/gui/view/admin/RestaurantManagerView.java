package org.makerminds.jcoaching.internship.restaurantpoint.gui.view.admin;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.junit.platform.commons.util.StringUtils;
import org.makerminds.jcoaching.internship.restaurantpoint.controller.LoginController;
import org.makerminds.jcoaching.internship.restaurantpoint.controller.RestaurantManagerController;
import org.makerminds.jcoaching.internship.restaurantpoint.gui.AbstractView;
import org.makerminds.jcoaching.internship.restaurantpoint.gui.utils.ViewIdentifierNameResolver;
import org.makerminds.jcoaching.internship.restaurantpoint.gui.view.IView;
import org.makerminds.jcoaching.internship.restaurantpoint.gui.view.IViewCreator;
import org.makerminds.jcoaching.internship.restaurantpoint.gui.view.ViewIdentifier;
import org.makerminds.jcoaching.internship.restaurantpoint.model.Restaurant;
import org.makerminds.jcoaching.internship.restaurantpoint.model.user.User;;

@SuppressWarnings("serial")
public class RestaurantManagerView extends AbstractView implements IView {

	private RestaurantManagerController restaurantManagerController = new RestaurantManagerController();

	private DefaultTableModel restaurantDataTableModel;

	private JTextField restaurantNameTextfield;
	private JTextField restaurantAddressTextfield;

	private JTable restaurantDataTable;

	private List<Restaurant> restaurantList;

	@Override
	public void prepareView() {
		createHeadlineLabel();
		createRestaurantManagementPanel();
		createRestaurantListPanel();
	}

	private void createHeadlineLabel() {
		String viewHeadlineLabel = ViewIdentifierNameResolver.getViewIdentifierName(ViewIdentifier.RESTAURANT_MANAGER);
		prepareViewHeadline(viewHeadlineLabel);
	}

	private void createRestaurantManagementPanel() {
		JPanel restaurantManagementPanel = new JPanel();
		restaurantManagementPanel.setBackground(Color.WHITE);
		TitledBorder titledBorder = createTitledBorder("Management Panel");
		restaurantManagementPanel.setBorder(titledBorder);
		restaurantManagementPanel.setBounds(20, 173, 307, 272);
		restaurantManagementPanel.setLayout(null);

		createRestaurantManagerInputComponents(restaurantManagementPanel);
		createRestaurantManagementButtons(restaurantManagementPanel);

		add(restaurantManagementPanel);
	}

	private void createRestaurantManagerInputComponents(JPanel restaurantManagementPanel) {
		JLabel restaurantNameLabel = createRestaurantNameLabel();
		JLabel restaurantAddressLabel = createRestaurantAddressLabel();

		createRestaurantNameTextfield();
		createRestaurantAddressTextfield();

		// add input components to restaurant management panel
		restaurantManagementPanel.add(restaurantNameLabel);
		restaurantManagementPanel.add(restaurantNameTextfield);
		restaurantManagementPanel.add(restaurantAddressLabel);
		restaurantManagementPanel.add(restaurantAddressTextfield);
	}

	private JLabel createRestaurantNameLabel() {
		JLabel restaurantNameLabel = new JLabel("Restaurant name");
		restaurantNameLabel.setFont(new Font("Arial", Font.BOLD, 13));
		restaurantNameLabel.setBounds(10, 37, 119, 36);
		return restaurantNameLabel;
	}

	private JLabel createRestaurantAddressLabel() {
		JLabel restaurantAddressLabel = new JLabel("Address:");
		restaurantAddressLabel.setFont(new Font("Arial", Font.BOLD, 13));
		restaurantAddressLabel.setBounds(10, 110, 119, 36);
		return restaurantAddressLabel;
	}

	private void createRestaurantNameTextfield() {
		restaurantNameTextfield = new JTextField();
		restaurantNameTextfield.setFont(new Font("Arial", Font.PLAIN, 13));
		restaurantNameTextfield.setBounds(10, 65, 119, 35);
		restaurantNameTextfield.setColumns(10);
	}

	private void createRestaurantAddressTextfield() {
		restaurantAddressTextfield = new JTextField();
		restaurantAddressTextfield.setFont(new Font("Arial", Font.PLAIN, 13));
		restaurantAddressTextfield.setColumns(10);
		restaurantAddressTextfield.setBounds(10, 136, 119, 35);
	}

	private void createRestaurantManagementButtons(JPanel restaurantManagementPanel) {
		JButton addRestaurantButton = createAddRestaurantButton();
		JButton updateRestaurantButton = createUpdateRestaurantButton();
		JButton removeRestaurantButton = createRemoveRestaurantButton();

		restaurantManagementPanel.add(addRestaurantButton);
		restaurantManagementPanel.add(updateRestaurantButton);
		restaurantManagementPanel.add(removeRestaurantButton);
	}

	private JButton createAddRestaurantButton() {
		JButton addRestaurantButton = new JButton("Add");
		addRestaurantButton.setBackground(CORPORATE_BLUE);
		addRestaurantButton.setBounds(10, 227, 96, 35);
		addRestaurantButton.setFont(GENERAL_LABEL_FONT);
		addRestaurantButton.setForeground(Color.WHITE);
		addRestaurantButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// get UI data 
				String restaurantName = restaurantNameTextfield.getText();
				String restaurantAddress = restaurantAddressTextfield.getText();
				if ((StringUtils.isBlank(restaurantName) || (StringUtils.isBlank(restaurantAddress)))) {
					JOptionPane.showMessageDialog(null, "Provide the mandatory data to add a new restaurant.");
				} else {
					// show in UI
					restaurantDataTableModel.addRow(new Object[] { restaurantName, restaurantAddress });
					restaurantManagerController.addRestaurantToRestaurantList(restaurantName, restaurantAddress,
							restaurantList);
					clearTextfieldsContent();
				}
			}

		});
		return addRestaurantButton;
	}

	private JButton createUpdateRestaurantButton() {
		JButton updateRestaurantButton = new JButton("Update");
		updateRestaurantButton.setBounds(117, 227, 85, 35);
		updateRestaurantButton.setFont(GENERAL_LABEL_FONT);
		updateRestaurantButton.setBackground(CORPORATE_BLUE);
		updateRestaurantButton.setForeground(Color.WHITE);
		updateRestaurantButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRowIndex = restaurantDataTable.getSelectedRow();
				if (selectedRowIndex == -1) {
					JOptionPane.showMessageDialog(null, "Select a restaurant first that should be updated.");
				} else {
					String selectedRestaurantName = (String) restaurantDataTable.getValueAt(selectedRowIndex, 0);
					String newRestaurantName = restaurantNameTextfield.getText();
					String newRestaurantAddress = restaurantAddressTextfield.getText();
					// update data model first
					restaurantManagerController.updateRestaurantInRestaurantList(selectedRestaurantName,
							newRestaurantName, newRestaurantAddress, restaurantList);
					// then update data list element
					restaurantDataTable.setValueAt(newRestaurantName, selectedRowIndex, 0);
					restaurantDataTable.setValueAt(newRestaurantAddress, selectedRowIndex, 1);
					// refresh the data table model after update
					restaurantDataTable.setModel(restaurantDataTableModel);
					clearTextfieldsContent();
				}
			}
		});
		return updateRestaurantButton;
	}

	private JButton createRemoveRestaurantButton() {
		JButton removeRestaurantButton = new JButton("Remove");
		removeRestaurantButton.setBounds(212, 227, 85, 35);
		removeRestaurantButton.setFont(GENERAL_LABEL_FONT);
		removeRestaurantButton.setBackground(CORPORATE_BLUE);
		removeRestaurantButton.setForeground(Color.WHITE);
		removeRestaurantButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRowIndex = restaurantDataTable.getSelectedRow();
				if (selectedRowIndex == -1) {
					JOptionPane.showMessageDialog(null, "Select a restaurant first that should be removed.");
				} else {
					String selectedRestaurantName = (String) restaurantDataTable.getValueAt(selectedRowIndex, 0);
					restaurantManagerController.removeRestaurantFromRestaurantList(selectedRestaurantName,
							restaurantList);
					restaurantDataTableModel.removeRow(selectedRowIndex);
					clearTextfieldsContent();
				}
			}
		});
		return removeRestaurantButton;
	}

	private void createRestaurantListPanel() {
		createRestaurantDataTable();

		JScrollPane scrollPane = new JScrollPane(restaurantDataTable);
		scrollPane.setBackground(Color.WHITE);
		TitledBorder titledBorder = createTitledBorder("Restaurant List");
		scrollPane.setBorder(titledBorder);
		scrollPane.setBounds(367, 173, 307, 272);
		add(scrollPane);
	}

	private void createRestaurantDataTable() {
		restaurantDataTable = new JTable();
		createRestaurantDataTableModel();
		restaurantDataTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int selectedRowIndex = restaurantDataTable.getSelectedRow();
				restaurantNameTextfield.setText(restaurantDataTableModel.getValueAt(selectedRowIndex, 0).toString());
				restaurantAddressTextfield.setText(restaurantDataTableModel.getValueAt(selectedRowIndex, 1).toString());
			}
		});
	}

	private void createRestaurantDataTableModel() {
		restaurantDataTableModel = new DefaultTableModel();
		Object[] restaurantColumnNames = { "Name", "Address" };
		restaurantDataTableModel.setColumnIdentifiers(restaurantColumnNames);
		initializeRestaurantList();
		updateRestaurantDataTableModel();
		restaurantDataTable.setShowGrid(false);
	}

	private void initializeRestaurantList() {
		User loggedInUser = LoginController.getLoggedInUser();
		restaurantList = loggedInUser.getRestaurantList();
	}

	private void updateRestaurantDataTableModel() {
		for (Restaurant restaurant : restaurantList) {
			restaurantDataTableModel.addRow(new Object[] { restaurant.getName(), restaurant.getAddress() });
		}
		restaurantDataTable.setModel(restaurantDataTableModel);
	}

	private void clearTextfieldsContent() {
		restaurantNameTextfield.setText("");
		restaurantAddressTextfield.setText("");
	}

	@Override
	public void refreshViewData() {
		createRestaurantDataTableModel();
	}
	
	public static class Creator implements IViewCreator {
		
		@Override
		public IView createView() {
			return new RestaurantManagerView();
		}
		
		@Override
		public ViewIdentifier getViewIdentifier() {
			return ViewIdentifier.RESTAURANT_MANAGER;
		}
	}
}
