package org.makerminds.jcoaching.internship.restaurantpoint.model;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.makerminds.jcoaching.internship.restaurantpoint.model.product.Meal;
import org.makerminds.jcoaching.internship.restaurantpoint.model.product.Product;

public class RestaurantTest {
	@Test
	public void numberOfRestaurants() {
		
		Restaurant restaurant = new Restaurant();
		Product product = new Meal(1,"Vanilla", 1.2);
		
		Menu menu = new Menu();
		menu.getMenuItems().put(product.getProductId(), product);
		
		restaurant.getMenuList().add(menu);
		Assertions.assertSame(1, restaurant.getMenuList().size());
	}

}
