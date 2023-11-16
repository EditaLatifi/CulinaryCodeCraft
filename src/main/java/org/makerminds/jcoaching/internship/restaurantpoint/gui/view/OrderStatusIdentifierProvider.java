package org.makerminds.jcoaching.internship.restaurantpoint.gui.view;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.makerminds.jcoaching.internship.restaurantpoint.model.OrderStatus;
import org.makerminds.jcoaching.internship.restaurantpoint.model.user.UserRole;

public class OrderStatusIdentifierProvider {
	private static Map<String, OrderStatus> orderIdentifierName;
	public static List<OrderStatus> getOrderStatusForUserRole(UserRole userRole) {
		if(UserRole.WAITER.equals(userRole)) {
			return Arrays.asList(OrderStatus.IN_PROGRESS, OrderStatus.READY, OrderStatus.DELIVERED);
		}
		else if(UserRole.COOK.equals(userRole)) {
				return Arrays.asList(OrderStatus.QUEUE, OrderStatus.IN_PROGRESS);
		} else {
			throw new IllegalArgumentException("Provided user role is not supported: " + userRole);
		}
	}
	
	public static boolean isUserAllowedToChangeOrderStatus(String selectedStatusName, OrderStatus orderStatus)
	{
		if(getOrderIdentifierNameMap().containsKey(selectedStatusName))
		{
			return getOrderIdentifierNameMap().get(selectedStatusName).equals(orderStatus);
		}
		return false;
	}
	
	private static Map<String, OrderStatus> getOrderIdentifierNameMap() {
		if (orderIdentifierName == null) {
			orderIdentifierName = new HashMap<>();
			orderIdentifierName.put("DELIVERED", OrderStatus.READY );
			orderIdentifierName.put("PAID", OrderStatus.DELIVERED );
			orderIdentifierName.put("READY", OrderStatus.IN_PROGRESS );
			orderIdentifierName.put("IN PROGRESS", OrderStatus.QUEUE );
		}

		return orderIdentifierName;
	}
}
