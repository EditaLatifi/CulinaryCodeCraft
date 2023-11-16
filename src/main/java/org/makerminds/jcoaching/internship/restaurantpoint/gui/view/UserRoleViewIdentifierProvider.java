package org.makerminds.jcoaching.internship.restaurantpoint.gui.view;

import static org.makerminds.jcoaching.internship.restaurantpoint.gui.view.ViewIdentifier.*;

import java.util.Arrays;
import java.util.List;

import org.makerminds.jcoaching.internship.restaurantpoint.model.user.UserRole;

/**
 * manages the views related to a user role.
 * 
 * @author makerminds
 *
 */
public class UserRoleViewIdentifierProvider {

	public static List<ViewIdentifier> getViewsForUserRole(UserRole userRole) {
		if(UserRole.RESTAURANT_ADMIN.equals(userRole)) {
			return Arrays.asList(RESTAURANT_MANAGER, MENU_MANAGER, MENU_ITEM_MANAGER, TABLE_MANAGER);
		} else if(UserRole.WAITER.equals(userRole)) {
			return Arrays.asList(TABLE_ORDERS_MANAGER, ORDER_STATUS_MANAGER);
		}
		else if(UserRole.COOK.equals(userRole)) {
				return Arrays.asList(ORDER_STATUS_MANAGER);
		} else {
			throw new IllegalArgumentException("Provided user role is not supported: " + userRole);
		}
	}
}
