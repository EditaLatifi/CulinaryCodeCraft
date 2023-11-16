package org.makerminds.jcoaching.internship.restaurantpoint.gui.utils;

import java.util.HashMap;
import java.util.Map;

import org.makerminds.jcoaching.internship.restaurantpoint.model.OrderStatus;

public class OrderStatusIdentifierResolver {
	private static Map<OrderStatus, String> orderIdentifierName;

	public static String getOrderIdentifierName(OrderStatus viewIdentifier) {
		return getOrderIdentifierNameMap().get(viewIdentifier);
	}
	
	public static OrderStatus getOrderIdentifier(String orderIdentifierName) {
		return getOrderIdentifierNameMap()
				.entrySet()
				.stream()
				.filter(entry -> entry.getValue().equals(orderIdentifierName))
				.map(Map.Entry::getKey)
				.findFirst()
				.get();
	}

	public static Map<OrderStatus, String> getOrderIdentifierNameMap() {
		if (orderIdentifierName == null) {
			orderIdentifierName = new HashMap<>();
			orderIdentifierName.put(OrderStatus.READY, "Ready");
			orderIdentifierName.put(OrderStatus.READYCOOK, "Ready");
			orderIdentifierName.put(OrderStatus.IN_PROGRESS, "In progress");
			orderIdentifierName.put(OrderStatus.PAID, "Paid");
			orderIdentifierName.put(OrderStatus.QUEUE, "Queue");
			orderIdentifierName.put(OrderStatus.DELIVERED, "Delivered");
		}

		return orderIdentifierName;
	}
}
