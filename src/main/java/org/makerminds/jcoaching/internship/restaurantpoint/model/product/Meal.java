package org.makerminds.jcoaching.internship.restaurantpoint.model.product;

public class Meal extends Product {
	private String description;

	public Meal(int productId, String name, double price) {
		super(productId, name, price);
	}

	public Meal(int productId, String name, double price, String description) {
		super(productId, name, price);
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
