package org.makerminds.jcoaching.internship.restaurantpoint.dataprovider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.makerminds.jcoaching.internship.restaurantpoint.model.Menu;
import org.makerminds.jcoaching.internship.restaurantpoint.model.Restaurant;
import org.makerminds.jcoaching.internship.restaurantpoint.model.Table;
import org.makerminds.jcoaching.internship.restaurantpoint.model.product.Drink;
import org.makerminds.jcoaching.internship.restaurantpoint.model.product.Meal;
import org.makerminds.jcoaching.internship.restaurantpoint.model.product.Product;

/**
 * data provider for restaurants 
 * 
 * @author makerminds
 *
 */
public class RestaurantDataProvider {
	private  List<Restaurant> restaurants = new ArrayList<>();
	
	public RestaurantDataProvider() {
		createRestaurants();
	}

	private void createRestaurants() {
		createRestaurant1();
		createRestaurant2();
		createRestaurant3();
		createRestaurant4();
	}

	private void createRestaurant1() {
		List<Table> tables = createTableGroup1();
		List<Menu> menus = createMenuGroup1();

		Restaurant restaurant = new Restaurant("Test Restaurant 1" , "Test Street 1" );
		restaurant.setMenuList(menus);
		restaurant.setTableList(tables);

		restaurants.add(restaurant);
	}

	private List<Menu> createMenuGroup1() {
		List<Product> breakfastMenuItems = createBreakfastMenuItems();
		List<Product> lunchMenuItems = createLunchMenuItems();
		List<Product> dinnerMenuItems = createDinnerMenuItems();
		

		Menu breakfastMenu = createMenu("Breakfast Menu", breakfastMenuItems);
		Menu lunchMenu = createMenu("Lunch Menu", lunchMenuItems);
		Menu dinnerMenu = createMenu("Dinner Menu", dinnerMenuItems);

		
	    List<Menu> menus = new ArrayList<>();
	    menus.add(breakfastMenu);
	    menus.add(lunchMenu);
	    menus.add(dinnerMenu);
		
		return menus;
	}
	
	private List<Product> createBreakfastMenuItems() {
		Product product1 = new Meal(100, "Egg Sandwich",3.5, "Sandwich with eggs");
		Product product2 = new Meal(101, "Cheese Sandwich",3.3, "Sandwich with cheese");
		Product product3 = new Meal(102, "Tuna sandwich",3.7, "Sandwich with tuna");
		Product product4 = new Meal(103, "Vegetarian sandwich", 3.2, "Sandwich with tomato and cucumbre");


		List<Product> productMenu = Arrays.asList(product1, product2, product3, product4);
		return productMenu;
	}

	private List<Product> createDinnerMenuItems() {
		Product product1 = new Meal(200, "Hamburger",9, "Angus beef patty, tomato, red onion");
		Product product2 = new Meal(201, "Cheeseburger",10, "Angus beef patty, cheese, tomato, red onion");
		Product product3 = new Meal(202, "Sandwich",8, "Chicken, mayonnaise, peppers");
		Product product4 = new Meal(203, "Hotdog", 8, "Beef, mustard, ketchup, onion, cucumber");

		List<Product> productMenu = Arrays.asList(product1, product2, product3, product4);
		return productMenu;
	}

	private List<Product> createLunchMenuItems() {
		Product product1 = new Meal(300, "Salmon",19, "Salmon filet with potatoes");
		Product product2 = new Meal(301, "Steak ",25, "Steak with butter and fries");
		Product product3 = new Meal(302, "Roastbeef",23, "Roastbeef with vegetables");
		Product product4 = new Meal(303, "Spageti Bolognese", 16, "Spageti with homemade bolognese sauce");
		List<Product> productMenu = Arrays.asList(product1, product2, product3, product4);
		return productMenu;
	}

	private Menu createMenu(String menuName, List<Product> menuProducts) {
		Menu menu1 = new Menu();
		menu1.setMenuName(menuName);
		
		menuProducts.forEach(product->{
			menu1.getMenuItems().put(product.getProductId(), product);
		});
		return menu1;
	}

	private List<Table> createTableGroup1() {
		Table table1 = new Table(11,2);
		Table table2 = new Table(12,3);
		Table table3 = new Table(13,4);
		Table table4 = new Table(14,6);

		List<Table> tables = new ArrayList<Table>(Arrays.asList(table1, table2, table3, table4));

		return tables;
	}

	private void createRestaurant2() {
		List<Menu> menus = createMenuGroup2();
		List<Table> tables = createTableGroup2();

		Restaurant restaurant = new Restaurant("Test Restaurant 2" , "Test Street 2");
		restaurant.setMenuList(menus);
		restaurant.setTableList(tables);

		restaurants.add(restaurant);
	}

	private List<Table> createTableGroup2() {
		Table table1 = new Table(21,2);
		Table table2 = new Table(22,4);
		Table table3 = new Table(23,6);

		List<Table> tables = new ArrayList<Table>(Arrays.asList(table1, table2, table3));

		return tables;
	}

	private List<Menu> createMenuGroup2() {
		Product product1 = new Drink(100, "Vodka Martini",80);
		Product product2 = new Drink(200, "Cosmopolitan",50);
		List<Product> productMenu = Arrays.asList(product1, product2);

		Menu menu = new Menu();
		menu.setMenuName("Cocktail Menu");
		for(Product p: productMenu) {
			menu.getMenuItems().put(p.getProductId(), p);
		}
		
		List<Menu> menus = new ArrayList<Menu>();
		return menus;
	}

	private void createRestaurant3() {
		List<Menu> menus = createMenuGroup3();
		List<Table> tables = createTableGroup3();

		Restaurant restaurant = new Restaurant("Test Restaurant 3" , "Test Street 3");
		restaurant.setMenuList(menus);
		restaurant.setTableList(tables);

		restaurants.add(restaurant);
	}

	private List<Table> createTableGroup3() {
		Table table1 = new Table(31, 2);
		Table table2 = new Table(32, 4);

		List<Table> tables = new ArrayList<Table>(Arrays.asList(table1, table2));

		return tables;
	}

	private List<Menu> createMenuGroup3() {
		Product product1 = new Meal(100, "Ice Cream Vanila",3);
		Product product2 = new Meal(200, "Ice Cream Pinneaple",3);
		List<Product> productMenu = Arrays.asList(product1, product2);

		Menu menu = new Menu();
		menu.setMenuName("Ice-Cream Menu");
		for(Product p: productMenu) {
			menu.getMenuItems().put(p.getProductId(), p);
		}
		
		List<Menu> menus = Arrays.asList(menu);
		
		return menus;
	}

	private void createRestaurant4() {
		List<Menu> menus = createMenuGroup4();
		List<Table> table = createTableGroup4();

		Restaurant pizzaRestaurant = new Restaurant("Test Restaurant 4", "Test Street 4");
		pizzaRestaurant.setMenuList(menus);
		pizzaRestaurant.setTableList(table);

		restaurants.add(pizzaRestaurant);
	}

	private List<Table> createTableGroup4() {
		Table table = new Table(41, 2);
		List<Table> tables = new ArrayList<Table>(Arrays.asList(table));

		
		return tables;
	}

	private List<Menu> createMenuGroup4() {
		Product pizzaMargarita = new Meal(100, "Pizza Margarita", 6, "lots of cheese");
		Product pizzaVegetarian = new Meal(101, "Pizza Vegetarian", 6.5, "cheese, green pepper, tomator, onion");
		Product pizzaMushroom = new Meal(102, "Pizza Mushroom", 6.5, "cheese, mushroom, garlic, chilli");
		Product pizzaMexican = new Meal(103, "Pizza Mexican", 7, "cheese, chilli, sweet corn, tomato, olives");
		List<Product> productList = Arrays.asList(pizzaMargarita, pizzaVegetarian, pizzaMushroom, pizzaMexican);

		Menu pizzaMenu = new Menu();
		pizzaMenu.setMenuName("Pizza Menu");

		for (Product p : productList) {
			pizzaMenu.getMenuItems().put(p.getProductId(), p);
		}
		
		List<Menu> menus = Arrays.asList(pizzaMenu);
		
		return menus;
	}

	public List<Restaurant> getRestaurants() {
		return restaurants;
	}

	public void setRestaurants(List<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}
}
