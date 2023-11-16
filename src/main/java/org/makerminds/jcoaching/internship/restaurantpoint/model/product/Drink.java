package org.makerminds.jcoaching.internship.restaurantpoint.model.product;

public class Drink extends Product {
	
	private boolean sugarFree;

	public Drink(int productId, String name, double price) {
		super(productId, name, price);
	}
	
	public Drink(String name, int productId, double price, boolean sugarFree) {
		super(productId, name, price);
		this.sugarFree = sugarFree;
	}

	public boolean isSugarFree() {
		return sugarFree;
	}

	public void setSugarFree(boolean sugarFree) {
		this.sugarFree = sugarFree;
	}
}
