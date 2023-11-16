package org.makerminds.jcoaching.internship.restaurantpoint.model.user;

import java.util.HashMap;
import java.util.Map;

import org.makerminds.jcoaching.internship.restaurantpoint.model.TableOrder;

public class CookUser extends User {
	
	public CookUser(String username, String password, UserRole userRole) {
		super(username, password, userRole);
	}
	
	private Map<Integer, TableOrder> tableOrders = new HashMap<>();
	
	public Map<Integer, TableOrder> getTableOrders()
	{
		return tableOrders;
	}
	public void setTableOrder(Map<Integer, TableOrder> tableOrders) {
		this.tableOrders = tableOrders;
		
	}
}