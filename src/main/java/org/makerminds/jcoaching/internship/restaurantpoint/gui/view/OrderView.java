package org.makerminds.jcoaching.internship.restaurantpoint.gui.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import org.makerminds.jcoaching.internship.restaurantpoint.controller.LoginController;
import org.makerminds.jcoaching.internship.restaurantpoint.dataprovider.UserDataProvider;
import org.makerminds.jcoaching.internship.restaurantpoint.gui.AbstractView;
import org.makerminds.jcoaching.internship.restaurantpoint.model.Menu;
import org.makerminds.jcoaching.internship.restaurantpoint.model.Restaurant;
import org.makerminds.jcoaching.internship.restaurantpoint.model.user.User;

@SuppressWarnings("serial")
public class OrderView extends AbstractView implements IView {

	private final Font GENERAL_LABEL_FONT = new Font("Arial", Font.PLAIN, 15);
	private final Color CORPORATE_BLUE = Color.decode("#4285f4");
	private JLabel lblShowReservation = new JLabel("");

	private Object[] menuItemsColumnName = { "Product Name"};
	private Object[] rowForMenuItems = new Object[1];
	private DefaultTableModel modelForMenuItems;
	private JScrollPane scrollPaneProduct = new JScrollPane();
	private JLabel orderOverView = new JLabel();
	private JLabel totalSum = new JLabel();
	private JTable tableProduct;
	
	/**
	 * Create the panel.
	 */
	
	public OrderView() {
		super();
		prepareView();
	}

	public void prepareView() {
		createTableOrderDesign();
		createTable();
		createAddButton();
		createUpdateButton();
		createDeleteButton();
		createPrintInvoiceButton();
	}

	private void createTable() {
		modelForShowingMenuItems();
		showMenuItem();
	}

	private void createTableOrderDesign() {
		JLabel lblTableOrderDetails = new JLabel("Table Order Details");
		lblTableOrderDetails.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		lblTableOrderDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblTableOrderDetails.setBounds(0, 0, 704, 57);
		add(lblTableOrderDetails);

		JLabel lblProduct = new JLabel("Menu Selection");
		lblProduct.setHorizontalAlignment(SwingConstants.LEFT); 
		lblProduct.setFont(new Font("Arial", Font.BOLD, 15));
		lblProduct.setBounds(10, 68, 271, 30);
		add(lblProduct);

		JLabel lblPrice = new JLabel("Orderover View");
		lblPrice.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrice.setFont(new Font("Arial", Font.BOLD, 15));
		lblPrice.setBounds(532, 68, 208, 30);
		add(lblPrice);

		lblShowReservation.setHorizontalAlignment(SwingConstants.CENTER);
		lblShowReservation.setFont(new Font("Arial", Font.PLAIN, 15));
		lblShowReservation.setBounds(496, 68, 208, 30);
		add(lblShowReservation);
		setVisible(true);
	}

	public void createAddButton() {
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO fix add order feature
				orderedFood();
				orderTotalSum();
			}
		});

		btnAdd.setFont(GENERAL_LABEL_FONT);
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setBackground(CORPORATE_BLUE);
		btnAdd.setBounds(10, 474, 108, 37);

		add(btnAdd);		
	}

	public void createUpdateButton() {
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		btnUpdate.setFont(GENERAL_LABEL_FONT);
		btnUpdate.setForeground(Color.WHITE);
		btnUpdate.setBackground(CORPORATE_BLUE);
		btnUpdate.setBounds(142, 474, 108, 37);

		add(btnUpdate);
	}

	public void createDeleteButton() {
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		btnDelete.setFont(GENERAL_LABEL_FONT);
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setBackground(CORPORATE_BLUE);
		btnDelete.setBounds(444, 474, 108, 37);

		add(btnDelete);
	}

	public void createPrintInvoiceButton() {
		JButton btnPrintInvoice = new JButton("Print Invoice");
		btnPrintInvoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});

		btnPrintInvoice.setFont(GENERAL_LABEL_FONT);
		btnPrintInvoice.setForeground(Color.WHITE);
		btnPrintInvoice.setBackground(CORPORATE_BLUE);
		btnPrintInvoice.setBounds(574, 474, 130, 37);

		add(btnPrintInvoice);
		
		orderOverView.setHorizontalAlignment(SwingConstants.LEFT);
		orderOverView.setFont(new Font("Arial", Font.PLAIN, 11));
		orderOverView.setBounds(532, 102, 172, 30);
		add(orderOverView);
		
		totalSum.setHorizontalAlignment(SwingConstants.LEFT);
		totalSum.setFont(new Font("Arial", Font.PLAIN, 15));
		totalSum.setBounds(532, 362, 172, 30);
		add(totalSum);
	}
	
	private void orderedFood() {
		// TODO fix food order
		/*
		for(Product p : waiterDataProvider.getOrder().getOrderItems()) {
				orderOverView.setText(p.getName());
		}
		*/
	}

	private void orderTotalSum() {
		double orderPrice = calculateOrder();
		totalSum.setText(String.valueOf(orderPrice + " $."));
	}


	public double calculateOrder() {
		// TODO fix order calculation
		
		//return orderCalculator.calculateTotalOrderAmount(waiterDataProvider.getOrder());
		return 0.0;
	}

	public void modelForShowingMenuItems(){
		tableProduct = new JTable();
		modelForMenuItems = new DefaultTableModel();
		modelForMenuItems.setColumnIdentifiers(menuItemsColumnName);

		tableProduct.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				int rowIndex = tableProduct.getSelectedRow();
				String productName = (String) tableProduct.getValueAt(rowIndex,0);
	
				if(productName != null) {
					// TODO fix model for showing menu items
					//waiterDataProvider.createProductDataWaiter(productName, getUser());
					refresh();
				}
			}
		});
		tableProduct.setModel(modelForMenuItems);
		tableProduct.setShowGrid(false);

		scrollPaneProduct.setBackground(Color.WHITE);
		scrollPaneProduct.setViewportView(tableProduct);
		scrollPaneProduct.setBounds(10, 109, 271, 156);

		add(scrollPaneProduct);
	}

	public void showMenuItem() {
		for(Restaurant restaurant : getUser().getRestaurantList()) {
			for(Menu menu : restaurant.getMenuList()) {
				if(menu.getMenuItems()!= null) {
					menu.getMenuItems().forEach((key,value)-> {
						rowForMenuItems[0] =value.getName();
						modelForMenuItems.addRow(rowForMenuItems);
					});
				}
			}
		}
	}

	public User getUser() {
		User loggedInUser = LoginController.getLoggedInUser();
		if(loggedInUser == null) {
			// FIXME user is null, so get user from data provider (mock)
			UserDataProvider userDataProvider = new UserDataProvider();
			loggedInUser = userDataProvider.getUsers().get(0);
		} 
		return loggedInUser;
	}

	private void refresh() {
		invalidate();
		validate();
		repaint();
	}

	@Override
	public void refreshViewData() {
		// TODO Auto-generated method stub
		
	}
}
