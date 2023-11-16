package org.makerminds.jcoaching.internship.restaurantpoint.controller;

import java.util.ArrayList;
import java.util.List;

import org.makerminds.jcoaching.internship.restaurantpoint.model.Menu;
import org.makerminds.jcoaching.internship.restaurantpoint.model.Order;
import org.makerminds.jcoaching.internship.restaurantpoint.model.product.Product;

public class TableOrdersManagerController {
	
//	private static TableOrdersManagerController table;
	/**
	 * adds a product to the order items list
	 * 
	 * @param menu
	 * @param order
	 * @param productName
	 */
	
	public Product prepareItemToAdd(Menu menu, String productName) {
		Product product = (Product) menu.getMenuItems().values().stream().filter(p -> p.getName().equals(productName)).findFirst().orElse(null);
		return product;
	}
	
	public List<Product> proccessOrderToPayment(List<Order> orderList)
	{
		List<Product> productList = new ArrayList<>();
		for(Order order : orderList)
		{
			order.getOrderItems().forEach(product -> productList.add(product));
		}
		return productList;
	}
}
