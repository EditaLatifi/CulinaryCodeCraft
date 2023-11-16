package org.makerminds.jcoaching.internship.restaurantpoint.controller.order;

import java.util.ArrayList;

import org.makerminds.jcoaching.internship.restaurantpoint.model.Order;

public class OrderController {
	// switched from array to ArrayList
	private ArrayList<Order> orders = new ArrayList<>();

	// Getter for order ArrayList
	public ArrayList<Order> getOrders() {
		return orders;
	}
}
