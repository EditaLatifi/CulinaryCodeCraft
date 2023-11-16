package org.makerminds.jcoaching.internship.restaurantpoint.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.makerminds.jcoaching.internship.restaurantpoint.model.Menu;
import org.makerminds.jcoaching.internship.restaurantpoint.model.Restaurant;
import org.makerminds.jcoaching.internship.restaurantpoint.model.product.Meal;
import org.makerminds.jcoaching.internship.restaurantpoint.model.product.Product;

public class MenuItemManagerController {

	/**
	 * updates a menu item (product) from the specified menu
	 * 
	 * @param oldMenuName
	 * @param newMenuName
	 * @param restaurant
	 */
	public void updateNameMenuInRestaurant(Product updatedMenuItem, Menu menu) {
		if (updatedMenuItem != null) {
			menu.getMenuItems().put(updatedMenuItem.getProductId(), updatedMenuItem);
		}
	}

	/**
	 * removes a menu from the restaurant menu list
	 * 
	 * @param menuName
	 * @param restaurant
	 */
	public void removeMenuFromRestaurant(Menu menuToRemove, Restaurant restaurant) {
		List<Menu> menuList = restaurant.getMenuList();
		Iterator<Menu> iterator = menuList.iterator();
		while (iterator.hasNext()) {
			Menu menu = iterator.next();
			if (menuToRemove.equals(menu)) {
				iterator.remove();
			}
		}
	}

	public void addMenuItemArrayToMenu(String[] menuItemArray, Menu selectedMenu) {
		Product createNewMenuItem = createNewMenuItem(menuItemArray);
		if (createNewMenuItem != null) {
			selectedMenu.getMenuItems().put(createNewMenuItem.getProductId(), createNewMenuItem);
		}
	}
	
	private Product createNewMenuItem(String[] menuItemArray) {
		// FIXME differentiate between Meal and Drink
		int productId = Integer.valueOf(menuItemArray[0]);
		String productName = menuItemArray[1];
		double productPrice = Double.valueOf(menuItemArray[2]);
		Product newMenuItem = new Meal(productId, productName, productPrice);
		return newMenuItem;
	}
	
	public void updateMenuItem(String[] menuItemAsArray, Menu menu, int selectedProductId) {
		HashMap<Integer, Product> menuItems = menu.getMenuItems();
		int productId = Integer.parseInt(menuItemAsArray[0]);
		String productName = menuItemAsArray[1];
		double productPrice = Double.parseDouble(menuItemAsArray[2]);
		
		menuItems.forEach((key, value) -> {
			if(key == selectedProductId) {
				value.setProductId(productId);
				value.setName(productName);
				value.setPrice(productPrice);
			}
		});
		
		menu.getMenuItems();
	}


	public void removeMenuItem(Menu menu, int oldProductID) {
		if (menu.getMenuItems().containsKey(oldProductID)) {
			menu.getMenuItems().remove(oldProductID);
		}
	}
}
