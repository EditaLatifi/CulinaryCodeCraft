 package org.makerminds.jcoaching.internship.restaurantpoint.model;

import java.util.List;

import org.makerminds.jcoaching.internship.restaurantpoint.model.product.Product;

/**
 * Order Model Class
 * 
 * @author makerminds
 *
 */
public class Order {
	
	private long orderNumber;
	private List<Product> orderItems;
	private OrderStatus orderStatus;
	
	public Order(long orderNumber, List<Product> list) {
		this.orderNumber = orderNumber;
		this.orderItems = list;
	}

	public long getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(long orderNumber) {
		this.orderNumber = orderNumber;
	}

	public List<Product> getOrderItems() {
		return orderItems;
	}
	
	public OrderStatus getOrderStatus()
	{
		return orderStatus;
	}
	
	public void setOrderStatus(OrderStatus orderStatus)
	{
		this.orderStatus = orderStatus;
	}

	public void setOrderItems(List<Product> list) {
		this.orderItems = list;
	}

	@Override
	public String toString() {
		return "Order #" + orderNumber;
	}
}