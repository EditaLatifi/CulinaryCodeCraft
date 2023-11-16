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
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import org.makerminds.jcoaching.internship.restaurantpoint.controller.TableManagerController;
import org.makerminds.jcoaching.internship.restaurantpoint.dataprovider.UserDataProvider;
import org.makerminds.jcoaching.internship.restaurantpoint.gui.AbstractView;
import org.makerminds.jcoaching.internship.restaurantpoint.gui.utils.ViewIdentifierNameResolver;
import org.makerminds.jcoaching.internship.restaurantpoint.gui.view.IView;
import org.makerminds.jcoaching.internship.restaurantpoint.gui.view.IViewCreator;
import org.makerminds.jcoaching.internship.restaurantpoint.gui.view.ViewIdentifier;
import org.makerminds.jcoaching.internship.restaurantpoint.model.Restaurant;
import org.makerminds.jcoaching.internship.restaurantpoint.model.Table;
import org.makerminds.jcoaching.internship.restaurantpoint.model.user.User;

@SuppressWarnings("serial")
public class TableManagerView extends AbstractView implements IView {

	private TableManagerController tableManagerController = new TableManagerController();
	private JComboBox<String> restaurantComboBox;
	private DefaultTableModel tableListDataModel;
	private JTable tableList;

	private JTextField tableIdTextField;
	private JTextField tableSeatsTextField;

	private Object[] tableListRows = new Object[2];
	private List<Restaurant> restaurantList;

	@Override
	public void prepareView() {
		createHeadlineLabel();
		createTableManagementPanel();
		prepareRestaurantSelectionPanel();
		createTableListPanel();
	}

	private void createHeadlineLabel() {
		String viewHeadlineLabel = ViewIdentifierNameResolver.getViewIdentifierName(ViewIdentifier.TABLE_MANAGER);
		prepareViewHeadline(viewHeadlineLabel);
	}

	private void createTableManagementPanel() {
		JPanel tableManagementPanel = new JPanel();
		tableManagementPanel.setBackground(Color.WHITE);
		TitledBorder titledBorder = createTitledBorder("Management Panel");
		tableManagementPanel.setBorder(titledBorder);
		tableManagementPanel.setBounds(20, 173, 307, 272);
		tableManagementPanel.setLayout(null);

		createTableManagerInputComponents(tableManagementPanel);
		createTableManagementButtons(tableManagementPanel);

		add(tableManagementPanel);
	}

	private void createTableManagerInputComponents(JPanel tableManagementPanel) {
		JLabel tableIdLabel = createTableIdLabel();
		JLabel tableSeatsLabel = createTableSeatsLabel();

		createTableIdTextfield();
		createTableSeatsTextfield();

		// add input components to table management panel
		tableManagementPanel.add(tableIdLabel);
		tableManagementPanel.add(tableIdTextField);
		tableManagementPanel.add(tableSeatsLabel);
		tableManagementPanel.add(tableSeatsTextField);
	}

	private JLabel createTableIdLabel() {
		JLabel tableIdLabel = new JLabel("ID");
		tableIdLabel.setFont(new Font("Arial", Font.BOLD, 13));
		tableIdLabel.setBounds(10, 47, 91, 21);
		return tableIdLabel;
	}

	private JLabel createTableSeatsLabel() {
		JLabel tableSeats = new JLabel("Seats");
		tableSeats.setFont(new Font("Arial", Font.BOLD, 13));
		tableSeats.setBounds(10, 108, 91, 32);
		return tableSeats;
	}

	private void createTableIdTextfield() {
		tableIdTextField = new JTextField();
		tableIdTextField.setFont(new Font("Arial", Font.PLAIN, 15));
		tableIdTextField.setBounds(10, 66, 91, 32);
		tableIdTextField.setColumns(10);
		tableIdTextField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume();
				}
			}

		});
	}

	private void createTableSeatsTextfield() {
		tableSeatsTextField = new JTextField();
		tableSeatsTextField.setFont(new Font("Arial", Font.PLAIN, 15));
		tableSeatsTextField.setBounds(10, 133, 91, 32);
		tableSeatsTextField.setColumns(10);

		tableSeatsTextField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume();
				}
			}

		});
	}

	private void createTableManagementButtons(JPanel restaurantManagementPanel) {
		JButton addRestaurantButton = createAddTableButton();
		JButton updateRestaurantButton = createUpdateTableButton();
		JButton removeRestaurantButton = createDeleteTableButton();

		restaurantManagementPanel.add(addRestaurantButton);
		restaurantManagementPanel.add(updateRestaurantButton);
		restaurantManagementPanel.add(removeRestaurantButton);
	}

	private JButton createDeleteTableButton() {
		JButton deleteButton = new JButton("Delete");
		deleteButton.setFont(GENERAL_LABEL_FONT);
		deleteButton.setForeground(Color.WHITE);
		deleteButton.setBackground(CORPORATE_BLUE);
		deleteButton.setBounds(202, 195, 86, 37);
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = tableList.getSelectedRow();
				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(null, "Select any row to delete");
				} else {
					String restaurantName = restaurantComboBox.getSelectedItem().toString();
					String selectedTableId = tableList.getValueAt(selectedRow, 0).toString();
					tableManagerController.removeTableFromDataList(selectedTableId,
							getRestaurantForJTable(restaurantName).getTableList());
					tableListDataModel.removeRow(selectedRow);
					clearTextfieldsContent();
				}
			}
		});
		return deleteButton;
	}

	public void deleteTable(JTable table) {

	}

	private JButton createUpdateTableButton() {
		JButton updateButton = new JButton("Update");
		updateButton.setFont(GENERAL_LABEL_FONT);
		updateButton.setForeground(Color.WHITE);
		updateButton.setBackground(CORPORATE_BLUE);
		updateButton.setBounds(106, 195, 86, 37);
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = tableList.getSelectedRow();
				if (selectedRow <= -1) {
					JOptionPane.showMessageDialog(null, "Selektoni pastaj beni update");
				} else {
					String selectedTableId = tableList.getValueAt(selectedRow, 0).toString();
					tableListRows[0] = tableIdTextField.getText();
					tableListRows[1] = tableSeatsTextField.getText();

					// update data model first
					tableManagerController.updateDataToUserList(selectedTableId,
							getRestaurantForJTable(restaurantComboBox.getSelectedItem().toString()).getTableList(),
							tableListRows);

					// then update data list element
					tableListDataModel.setValueAt(tableListRows[0], selectedRow, 0);
					tableListDataModel.setValueAt(tableListRows[1], selectedRow, 1);

					// refresh the data table model after update
					tableList.setModel(tableListDataModel);
					clearTextfieldsContent();
				}
			}
		});
		return updateButton;
	}

	private JButton createAddTableButton() {
		JButton addButton = new JButton("Add");
		addButton.setFont(GENERAL_LABEL_FONT);
		addButton.setForeground(Color.WHITE);
		addButton.setBackground(CORPORATE_BLUE);
		addButton.setBounds(8, 195, 86, 37);
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableListRows[0] = tableIdTextField.getText();
				tableListRows[1] = tableSeatsTextField.getText();
				if ((StringUtils.isBlank((String) tableListRows[0])
						|| (StringUtils.isBlank((String) tableListRows[1])))) {
					JOptionPane.showMessageDialog(null, "Provide the mandatory data to add a new restaurant.");
				} else {
					tableListDataModel.addRow(tableListRows);
					tableManagerController.addTableToDataList((String) tableListRows[0], (String) tableListRows[1],
							restaurantList.get(0).getTableList());
					clearTextfieldsContent();
				}
			}
		});
		return addButton;
	}

	private void clearTextfieldsContent() {
		tableIdTextField.setText("");
		tableSeatsTextField.setText("");
	}

	private void createTableListPanel() {
		createJTableForTables();

		JScrollPane scrollPane = new JScrollPane(tableList);
		scrollPane.setBackground(Color.WHITE);
		TitledBorder titledBorder = createTitledBorder("Table List");
		scrollPane.setBorder(titledBorder);
		scrollPane.setBounds(354, 233, 326, 206);
		add(scrollPane);
	}

	public void createJTableForTables() {
		tableList = new JTable();
		createTableDataModel();
		tableList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				int selectedRow = tableList.getSelectedRow();
				tableIdTextField.setText(tableListDataModel.getValueAt(selectedRow, 0).toString());
				tableSeatsTextField.setText(tableListDataModel.getValueAt(selectedRow, 1).toString());
			}
		});
	}

	private void createTableDataModel() {
		tableListDataModel = new DefaultTableModel();
		Object[] tableListColumnNames = { "Key", "Number of Table" };
		tableListDataModel.setColumnIdentifiers(tableListColumnNames);
		initializeRestaurantList();
		updateRestaurantDataTableModel();
		tableList.setShowGrid(false);
	}

	private void initializeRestaurantList() {
		User loggedInUser = LoginController.getLoggedInUser();
		restaurantList = loggedInUser.getRestaurantList();
	}

	private void updateRestaurantDataTableModel() {
		restaurantList.get(0).getTableList()
				.forEach(table -> tableListDataModel.addRow(new Object[] { table.getTableNumber(), table.getSeats() }));
		tableList.setModel(tableListDataModel);
	}

	private void prepareRestaurantSelectionPanel() {
		JPanel restaurantSelectionPanel = new JPanel();
		TitledBorder titledBorder = createTitledBorder("Restaurant Selection");
		restaurantSelectionPanel.setBackground(Color.WHITE);
		restaurantSelectionPanel.setBorder(titledBorder);
		restaurantSelectionPanel.setBounds(354, 173, 326, 50);
		restaurantSelectionPanel.setLayout(new BoxLayout(restaurantSelectionPanel, BoxLayout.Y_AXIS));

		createRestaurantComboBox();

		restaurantSelectionPanel.add(restaurantComboBox);
		add(restaurantSelectionPanel);
	}

	private void createRestaurantComboBox() {
		restaurantComboBox = new JComboBox<>();
		prepareRestaurantComboboxData();
		restaurantComboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					String selectedItem = restaurantComboBox.getSelectedItem().toString();
					updateTableListData(getRestaurantForJTable(selectedItem).getTableList());
				}
			}
		});
		restaurantComboBox.setBackground(Color.WHITE);
		restaurantComboBox.setBounds(357, 201, 323, 29);
	}

	private void prepareRestaurantComboboxData() {
		restaurantComboBox.removeAllItems();
		getUser().getRestaurantList().forEach(restaurant -> restaurantComboBox.addItem(restaurant.getName()));
	}

	private void updateTableListData(List<Table> tableList) {
		tableListDataModel.setRowCount(0);
		tableList
				.forEach(table -> tableListDataModel.addRow(new Object[] { table.getTableNumber(), table.getSeats() }));
	}

	private Restaurant getRestaurantForJTable(String selectedRestaurant) {
		Restaurant selectedRestaurantForJTable = null;
		if (selectedRestaurant == null) {
			selectedRestaurantForJTable = getUser().getRestaurantList().get(0);
		} else {
			for (Restaurant restaurant : getUser().getRestaurantList()) {
				if (restaurant.getName().equals(selectedRestaurant)) {
					selectedRestaurantForJTable = restaurant;
					break;
				}
			}
		}
		return selectedRestaurantForJTable;
	}

	private User getUser() {
		User loggedInUser = LoginController.getLoggedInUser();
		if (loggedInUser == null) {
			// user is null, so get user from data provider (mock)
			UserDataProvider userDataProvider = new UserDataProvider();
			loggedInUser = userDataProvider.getUsers().get(0);
		}
		return loggedInUser;
	}

	@Override
	public void refreshViewData() {
		prepareRestaurantComboboxData();
	}
	
	public static class Creator implements IViewCreator {
		
		@Override
		public IView createView() {
			return new TableManagerView();
		}
		
		@Override
		public ViewIdentifier getViewIdentifier() {
			return ViewIdentifier.TABLE_MANAGER;
		}
	}
}
