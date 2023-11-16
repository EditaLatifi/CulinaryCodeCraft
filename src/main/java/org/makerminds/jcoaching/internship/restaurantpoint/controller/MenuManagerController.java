package org.makerminds.jcoaching.internship.restaurantpoint.controller;

import java.util.Iterator;
import java.util.List;

import org.junit.platform.commons.util.StringUtils;
import org.makerminds.jcoaching.internship.restaurantpoint.model.Menu;
import org.makerminds.jcoaching.internship.restaurantpoint.model.Restaurant;

/**
 * Controller class for operations on {@link Menu}
 * 
 * @author makerminds
 *
 */
public class MenuManagerController {

	/**
	 * adds a menu to the restaurant menu list
	 * 
	 * @param menuName
	 * @param restaurant
	 */
	public void addMenuToRestaurant(String menuName, Restaurant restaurant) {
		if (StringUtils.isNotBlank(menuName)) {
			Menu menu = new Menu(menuName);
			restaurant.getMenuList().add(menu);
		}
	}

	/**
	 * updates a menu from the restaurant menu list
	 * 
	 * @param oldMenuName
	 * @param newMenuName
	 * @param restaurant
	 */
	public void updateNameMenuInRestaurant(String oldMenuName, String newMenuName, Restaurant restaurant) {
		Menu menuToUpdateName = getMenuByName(oldMenuName, restaurant.getMenuList());
		menuToUpdateName.setMenuName(newMenuName);
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

	private Menu getMenuByName(String menuName, List<Menu> menuList) {
		return menuList.stream().filter(menu -> menuName.equals(menu.getName())).findAny().orElse(null);
	}
}