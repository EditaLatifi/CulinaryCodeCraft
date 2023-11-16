package org.makerminds.jcoaching.internship.restaurantpoint.controller.order;

import java.util.List;

import org.makerminds.jcoaching.internship.restaurantpoint.model.Order;
import org.makerminds.jcoaching.internship.restaurantpoint.model.product.Product;

/**
 * calculates different order amounts.
 * 
 * @author makerminds
 *
 */
public class OrderCalculator {
	/**
	 * calculates the total order amount of the given order.
	 * 
	 * @param the order the total amount should be calculated from
	 * @return total order amount
	 */
	public double calculateTotalOrderAmount(Order order) {
		List<Product> orderItems = order.getOrderItems();
		double totalOrderAmount = 0.0;

		for (Product product : orderItems) {
			totalOrderAmount += product.getPrice();
		}
		return totalOrderAmount;
	}

	/**
	 * calculates the VAT amount of the given total order amount.
	 * 
	 * @param total order amount
	 * @return total order amount with VAT
	 */
	public double calculateTotalOrderAmountVAT(double totalOrderAmount) {
		return totalOrderAmount * 18 / 100;
	}
}
