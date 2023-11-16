package org.makerminds.jcoaching.internship.restaurantpoint.model;


import java.util.ArrayList;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.makerminds.jcoaching.internship.restaurantpoint.controller.order.OrderCalculator;
import org.makerminds.jcoaching.internship.restaurantpoint.model.product.Product;

/**
 * Test class for {@link OrderCalculator}
 * 
 * @author makerminds
 *
 */
public class OrderCalcalutorTest {
	
	OrderCalculator orderCalculator = new OrderCalculator();
	
	// TODO Check this test method and correct it
	@Test
	public void calculateTotalOrderAmount() {
		//FIXME correct this test, as it doesn't work anymore after refactorings
		ArrayList<Product> orderProductList = new ArrayList<Product>();
		Order order = new Order(1234, orderProductList);
	//	ArrayList<Product> orderItems = order.getOrderItems();
	//	Menu menu = new Menu();
		//orderItems.add(menu.getMenuItems().get(100));
		int quantity = 2;
	
		double totalOrderAmount = orderCalculator.calculateTotalOrderAmount(order);
		
		Assertions.assertEquals(0, totalOrderAmount);
	}

}
