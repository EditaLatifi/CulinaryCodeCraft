package org.makerminds.jcoaching.internship.restaurantpoint.model;

import java.util.HashMap;

import org.makerminds.jcoaching.internship.restaurantpoint.model.product.Product;

// new class Menu created
public class Menu {
	private String menuName;
	private HashMap<Integer, Product> menuItems = new HashMap<>();

	public Menu() {
	}

	public Menu(String menuName) {
		this.menuName = menuName;
	}

	public String getName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public HashMap<Integer, Product> getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(HashMap<Integer, Product> menuItems) {
		this.menuItems = menuItems;
	}

	@Override
	public String toString() {
		return this.getName();
	}
}
