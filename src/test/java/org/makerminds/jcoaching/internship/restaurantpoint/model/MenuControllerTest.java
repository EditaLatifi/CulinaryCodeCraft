package org.makerminds.jcoaching.internship.restaurantpoint.model;

import org.junit.Test;
import org.makerminds.jcoaching.internship.restaurantpoint.controller.MenuManagerController;
import org.makerminds.jcoaching.internship.restaurantpoint.controller.RestaurantManagerController;
import org.makerminds.jcoaching.internship.restaurantpoint.model.product.Meal;
import org.makerminds.jcoaching.internship.restaurantpoint.model.product.Product;

/**
 * Test class for {@link MenuManagerController}
 * 
 * @author makerminds
 *
 */
public class MenuControllerTest {

	Menu menu = new Menu();
	Restaurant rest = new Restaurant();
	
	RestaurantManagerController restaurant = new RestaurantManagerController();
	MenuManagerController menuController = new MenuManagerController();
	
	
	
	// TODO: Checking if the right menu is coming to our Restaurant's
	@Test
	public void checkIfIsTheSameMenu() {
		// code here
	}
	
	@Test
	 public void controllIfItemIsAdded() {
		Menu menu = new Menu();
		String userInputProductName = "Burito";
		int userInputProductProductId = 234;
		double userInputProductPrice = 5.5;
		//menuController.addMenuItem(menu, userInputProductName, userInputProductProductId, userInputProductPrice);;
	//	Assertions.assertEquals(234, menu.getMenuItems().get(234).getProductId());
		
	}
		
	@Test 
	public void controllIfItemIsUpdated() {
	//	Menu menu = new Menu();
		Product productToUpdate = new Meal(234, "Buroto", 5.5);
		//menu.getMenuItems().put(234, productToUpdate);
		
		productToUpdate.setName("Tortilla");
		//menuController.updateMenuItems(productToUpdate);
		
		
		//Assertions.assertEquals("Tortilla" , menu.getMenuItems().get(234).getName());
	}
	
	@Test
	public void controllIfItemIsDeleted() {
		Menu menu = new Menu();
		Product productToDelete = new Meal(234, "Buroto", 5.5);
		//menu.getMenuItems().put(234, productToDelete);
		
		//Assertions.assertEquals(13, menu.getMenuItems().size());
		//menuController.deleteMenuItems(menu, productToDelete);
		
		//Assertions.assertEquals(12, menu.getMenuItems().size());
	}
}
