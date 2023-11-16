package org.makerminds.jcoaching.internship.restaurantpoint.model;

import org.makerminds.jcoaching.internship.restaurantpoint.controller.LoginController;
import org.makerminds.jcoaching.internship.restaurantpoint.model.user.UserRole;

//import java.util.Optional;

public enum OrderStatus {
	QUEUE, IN_PROGRESS(QUEUE), READYCOOK(IN_PROGRESS), READY, DELIVERED(READY), PAID(DELIVERED);

	private OrderStatus prevPlanet = null;
	private OrderStatus nextPlanet = null;

	OrderStatus() {
	}

	OrderStatus(OrderStatus prev) {
		this.prevPlanet = prev;
		prev.nextPlanet = this;
	}

	public OrderStatus prev() {
		if(prevPlanet != null) {
		return prevPlanet;
		}
		else {
			UserRole userRole = LoginController.getInstance().getLoggedInUser().getUserRole();
			if(userRole.name().equals("Cook"))
			throw new IllegalArgumentException("There is no other order status before \"Queued\".");
			else
				throw new IllegalArgumentException("There is no other order status before \"Ready|\"");		}
	}

	public OrderStatus next() {
		if(nextPlanet != null) {
		return nextPlanet;
		}
		else {
			UserRole userRole = LoginController.getInstance().getLoggedInUser().getUserRole();
			if(userRole.name().equals("Cook"))
			throw new IllegalArgumentException("There is no other order status after \"Ready\".");
			else
				throw new IllegalArgumentException("There is no other order status after \"Paid|\"");	
		}
	}
}
