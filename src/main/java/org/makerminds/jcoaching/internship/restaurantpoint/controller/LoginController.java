package org.makerminds.jcoaching.internship.restaurantpoint.controller;

import org.makerminds.jcoaching.internship.restaurantpoint.dataprovider.UserDataProvider;
import org.makerminds.jcoaching.internship.restaurantpoint.model.user.User;

/**
 * manages authentication and authorization processes.
 * 
 * @author Arbrim Thaci
 *
 */
public class LoginController {

	// singleton variable
	private static LoginController loginController;

	private static User loggedInUser = null;

	private UserDataProvider userDataProvider = new UserDataProvider();

	public void loginUser(String userName, String password) {
		for (User user : userDataProvider.getUsers()) {
			if (user.getUsername().equals(userName) && user.getPassword().equals(password)) {
				// login successfully as username and password are correct
				loggedInUser = user;
			}
		}
	}

	public static User getLoggedInUser() {
		// FIXME user is null, so get user from data provider (mock)
		// temporary until development stage is done
		if (loggedInUser == null) {
			UserDataProvider userDataProvider = new UserDataProvider();
			loggedInUser = userDataProvider.getUsers().get(0);
		}
		return loggedInUser;
	}

	// get singleton implementation
	public static LoginController getInstance() {
		if (loginController == null) {
			loginController = new LoginController();
		}
		return loginController;
	}
}
