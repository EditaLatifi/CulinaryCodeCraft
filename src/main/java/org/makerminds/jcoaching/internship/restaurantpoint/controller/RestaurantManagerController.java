package org.makerminds.jcoaching.internship.restaurantpoint.controller;

import java.util.Iterator;
import java.util.List;

import org.makerminds.jcoaching.internship.restaurantpoint.model.Restaurant;

public class RestaurantManagerController {

	/**
	 * adds a restaurant to the restaurant list
	 * 
	 * @param restaurantName
	 * @param restaurantAddress
	 * @param restaurantList
	 */
	public void addRestaurantToRestaurantList(String restaurantName, String restaurantAddress,
			List<Restaurant> restaurantList) {
		Restaurant newRestaurant = new Restaurant(restaurantName, restaurantAddress);
		restaurantList.add(newRestaurant);
	}

	/**
	 * updates a restaurant in restaurant list
	 * 
	 * @param oldRestaurantName
	 * @param newRestaurantName
	 * @param newRestaurantAddress
	 * @param restaurantList
	 */
	public void updateRestaurantInRestaurantList(String oldRestaurantName, String newRestaurantName,
			String newRestaurantAddress, List<Restaurant> restaurantList) {
		Restaurant restaurantToUpdate = getRestaurantByName(oldRestaurantName, restaurantList);
		restaurantToUpdate.setName(newRestaurantName);
		restaurantToUpdate.setAddress(newRestaurantAddress);
	}

	/**
	 * removes a restaurant from restaurant list
	 * 
	 * @param restaurantName
	 * @param restaurantList
	 * @return
	 */
	public void removeRestaurantFromRestaurantList(String restaurantNameToRemove, List<Restaurant> restaurantList) {
		Iterator<Restaurant> iterator = restaurantList.iterator();
		while (iterator.hasNext()) {
			Restaurant restaurant = iterator.next();
			if (restaurantNameToRemove.equals(restaurant.getName())) {
				iterator.remove();
			}
		}
	}

	private Restaurant getRestaurantByName(String restaurantName, List<Restaurant> restaurantList) {
		return restaurantList.stream().filter(restaurant -> restaurantName.equals(restaurant.getName())).findAny()
				.orElse(null);
	}
}
