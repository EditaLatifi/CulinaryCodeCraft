package org.makerminds.jcoaching.internship.restaurantpoint.model;

import java.util.ArrayList;
import java.util.List;

public class TableOrder {

	private Table table;
	private List<Order> orders = new ArrayList<>();

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	public List<Order> getOrders() {
		return orders;
	}
}
