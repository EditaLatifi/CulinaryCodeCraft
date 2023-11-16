package org.makerminds.jcoaching.internship.restaurantpoint.model.user;

import java.util.ArrayList;
import java.util.List;

import org.makerminds.jcoaching.internship.restaurantpoint.model.Restaurant;

/**
 * A class to store user data.
 * 
 * @author Arbrim Thaci
 *
 */
public class User {
	private String username;
	private String password;
	private UserRole userRole;

	private List<Restaurant> restaurantList = new ArrayList<>();

	public User(String username, String password, UserRole userRole) {
		this.username = username;
		this.password = password;
		this.userRole = userRole;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Restaurant> getRestaurantList() {
		return restaurantList;
	}

	public void setRestaurantList(List<Restaurant> restaurantList) {
		this.restaurantList = restaurantList;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
}
