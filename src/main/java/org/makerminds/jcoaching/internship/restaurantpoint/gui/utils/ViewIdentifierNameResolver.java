package org.makerminds.jcoaching.internship.restaurantpoint.gui.utils;

import java.util.HashMap;
import java.util.Map;

import org.makerminds.jcoaching.internship.restaurantpoint.gui.view.ViewIdentifier;

public class ViewIdentifierNameResolver {

	private static Map<ViewIdentifier, String> viewIdentifierNameMap;

	public static String getViewIdentifierName(ViewIdentifier viewIdentifier) {
		return getViewIdentifierNameMap().get(viewIdentifier);
	}

	private static Map<ViewIdentifier, String> getViewIdentifierNameMap() {
		if (viewIdentifierNameMap == null) {
			viewIdentifierNameMap = new HashMap<>();
			viewIdentifierNameMap.put(ViewIdentifier.MENU_MANAGER, "Menu Manager");
			viewIdentifierNameMap.put(ViewIdentifier.MENU_ITEM_MANAGER, "Menu Item Manager");
			viewIdentifierNameMap.put(ViewIdentifier.ORDER_STATUS_MANAGER, "Order Status Manager");
			viewIdentifierNameMap.put(ViewIdentifier.RESTAURANT_MANAGER, "Restaurant Manager");
			viewIdentifierNameMap.put(ViewIdentifier.TABLE_MANAGER, "Table Manager");
			viewIdentifierNameMap.put(ViewIdentifier.TABLE_ORDER_DETAILS, "Table Order Details");
			viewIdentifierNameMap.put(ViewIdentifier.TABLE_ORDERS_MANAGER, "Table Orders Manager");
		}

		return viewIdentifierNameMap;
	}
}
