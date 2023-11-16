package org.makerminds.jcoaching.internship.restaurantpoint.model.user;

import java.util.HashMap;
import java.util.Map;

import org.makerminds.jcoaching.internship.restaurantpoint.model.TableOrder;

public class WaiterUser extends User {
	
	public WaiterUser(String username, String password, UserRole userRole) {
		super(username, password, userRole);
	}
	
	private Map<Integer, TableOrder> tableOrders = new HashMap<>();
	
	public TableOrder getTableOrder(int tableId) {
		return tableOrders.get(tableId);
	}
	public Map<Integer, TableOrder> getTableOrders()
	{
		return tableOrders;
	}
}
