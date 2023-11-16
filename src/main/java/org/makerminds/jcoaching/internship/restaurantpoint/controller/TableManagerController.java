package org.makerminds.jcoaching.internship.restaurantpoint.controller;

import java.util.ArrayList;
import java.util.List;

import org.makerminds.jcoaching.internship.restaurantpoint.dataprovider.UserDataProvider;
import org.makerminds.jcoaching.internship.restaurantpoint.model.Restaurant;
import org.makerminds.jcoaching.internship.restaurantpoint.model.Table;
import org.makerminds.jcoaching.internship.restaurantpoint.model.user.User;

public class TableManagerController {
	List<Table> savedTable = new ArrayList<>();

	public void createTables(int tableNumber) {
		for (Restaurant restaurant : getUser().getRestaurantList()) {
			for (int i = 0; i <= tableNumber; i++) {
				savedTable.add(new Table(i, i));
				for (int j = 0; j < savedTable.size(); j++) {
					restaurant.getTableList().add(savedTable.get(j).getTableNumber(), savedTable.get(j));
				}
			}
		}
	}

	public void addTableToDataList(String tableId, String tableSeats, List<Table> tableList) {
		tableList.add(new Table(Integer.parseInt(tableId), Integer.parseInt(tableSeats)));
	}

	public void updateDataToUserList(String tableId, List<Table> tableList, Object[] tableListRows) {
		Table table = getTableById(Integer.parseInt(tableId), tableList);
		table.setTableNumber(Integer.valueOf((String) tableListRows[0]));
		table.setSeats(Integer.valueOf((String) tableListRows[1]));
	}

	public void removeTableFromDataList(String tableId, List<Table> tableList) {
		Table table = getTableById(Integer.parseInt(tableId), tableList);
		tableList.remove(table);
	}

	private Table getTableById(int tableId, List<Table> tableList) {
		return tableList.stream().filter(table -> table.getTableNumber() == tableId).findAny().orElse(null);
	}

	public User getUser() {
		User loggedInUser = LoginController.getLoggedInUser();
		if (loggedInUser == null) {
			// FIXME user is null, so get user from data provider (mock)
			UserDataProvider userDataProvider = new UserDataProvider();
			loggedInUser = userDataProvider.getUsers().get(0);
		}
		return loggedInUser;
	}
}
