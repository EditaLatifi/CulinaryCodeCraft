package org.makerminds.jcoaching.internship.restaurantpoint.dataprovider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.makerminds.jcoaching.internship.restaurantpoint.model.Order;
import org.makerminds.jcoaching.internship.restaurantpoint.model.OrderStatus;
import org.makerminds.jcoaching.internship.restaurantpoint.model.Restaurant;
import org.makerminds.jcoaching.internship.restaurantpoint.model.TableOrder;
import org.makerminds.jcoaching.internship.restaurantpoint.model.product.Product;
import org.makerminds.jcoaching.internship.restaurantpoint.model.user.CookUser;
import org.makerminds.jcoaching.internship.restaurantpoint.model.user.User;
import org.makerminds.jcoaching.internship.restaurantpoint.model.user.UserRole;
import org.makerminds.jcoaching.internship.restaurantpoint.model.user.WaiterUser;

/**
 * data provider for application users
 * 
 * @author makerminds
 *
 */
public class UserDataProvider {

	private List<User> users = new ArrayList<>();
	private RestaurantDataProvider restaurantDataProvider = new RestaurantDataProvider();

	public UserDataProvider() {
		createUsers();
	}

	private void createUsers() {
		createRestaurantAdminUsers();
		createWaiterUsers();
		createCookUsers();
	}

	private void createRestaurantAdminUsers() {
		User restaurantAdminUser1 = new User("admin1", "admin11", UserRole.RESTAURANT_ADMIN);
		Restaurant restaurant1 = restaurantDataProvider.getRestaurants().get(0);
		Restaurant restaurant2 = restaurantDataProvider.getRestaurants().get(1);
		restaurantAdminUser1.getRestaurantList().add(restaurant1);
		restaurantAdminUser1.getRestaurantList().add(restaurant2);
		User restaurantAdminUser2 = new User("admin2", "admin22", UserRole.RESTAURANT_ADMIN);
		Restaurant restaurant3 = restaurantDataProvider.getRestaurants().get(2);
		Restaurant restaurant4 = restaurantDataProvider.getRestaurants().get(3);
		restaurantAdminUser2.getRestaurantList().add(restaurant3);
		restaurantAdminUser2.getRestaurantList().add(restaurant4);

		users.add(restaurantAdminUser1);
		users.add(restaurantAdminUser2);
	}

	private void createWaiterUsers() {
		User waiter1 = new WaiterUser("w", "w", UserRole.WAITER);
		Restaurant restaurant1 = restaurantDataProvider.getRestaurants().get(0);
		waiter1.getRestaurantList().add(restaurant1);
		User waiter2 = new WaiterUser("waiter2", "waiter22", UserRole.WAITER);
		users.add(waiter1);
		users.add(waiter2);
	}

	private void createCookUsers() {
		CookUser cook1 = new CookUser("cook1", "cook11", UserRole.COOK);
		Restaurant restaurant1 = restaurantDataProvider.getRestaurants().get(0);
		cook1.getRestaurantList().add(restaurant1);
		cook1.setTableOrder(createTableOrders());
		CookUser cook2 = new CookUser("cook2", "cook22", UserRole.COOK);
		Restaurant restaurant2 = restaurantDataProvider.getRestaurants().get(1);
		cook2.getRestaurantList().add(restaurant2);
		users.add(cook1);
		users.add(cook2);
	}

	private Map<Integer, TableOrder> createTableOrders() {
		Map<Integer, TableOrder> tableOrders = new HashMap<>();
		tableOrders.put(101, createTableOrder1());
		tableOrders.put(102, createTableOrder2());
		return tableOrders;
	}

	private TableOrder createTableOrder1() {
		Restaurant restaurant = restaurantDataProvider.getRestaurants().get(0);
		List<Product> productList = new ArrayList<>();
		productList.add(restaurant.getMenuList().get(0).getMenuItems().get(0));
		productList.add(restaurant.getMenuList().get(0).getMenuItems().get(1));
		productList.add(restaurant.getMenuList().get(0).getMenuItems().get(2));
		Order order1 = new Order(101l, productList);

		List<Product> productList1 = new ArrayList<>();
		productList1.add(restaurant.getMenuList().get(1).getMenuItems().get(0));
		productList1.add(restaurant.getMenuList().get(1).getMenuItems().get(1));
		productList1.add(restaurant.getMenuList().get(1).getMenuItems().get(2));
		Order order2 = new Order(101l, productList1);

		List<Order> orders = new ArrayList<>();
		orders.add(order1);
		orders.add(order2);
		TableOrder tableOrder = new TableOrder();
		tableOrder.setTable(restaurant.getTableList().get(0));
		tableOrder.setOrders(orders);
		return tableOrder;
	}

	private TableOrder createTableOrder2() {
		Restaurant restaurant = restaurantDataProvider.getRestaurants().get(0);
		List<Product> productList = new ArrayList<>();
		productList.add(restaurant.getMenuList().get(0).getMenuItems().get(0));
		productList.add(restaurant.getMenuList().get(0).getMenuItems().get(1));
		productList.add(restaurant.getMenuList().get(0).getMenuItems().get(2));
		Order order1 = new Order(101l, productList);
		order1.setOrderStatus(OrderStatus.QUEUE);

		List<Product> productList1 = new ArrayList<>();
		productList1.add(restaurant.getMenuList().get(1).getMenuItems().get(0));
		productList1.add(restaurant.getMenuList().get(1).getMenuItems().get(1));
		productList1.add(restaurant.getMenuList().get(1).getMenuItems().get(2));
		Order order2 = new Order(102l, productList1);
		order2.setOrderStatus(OrderStatus.IN_PROGRESS);

		List<Order> orders = new ArrayList<>();
		orders.add(order1);
		orders.add(order2);
		TableOrder tableOrder = new TableOrder();
		tableOrder.setTable(restaurant.getTableList().get(0));
		tableOrder.setOrders(orders);
		return tableOrder;
	}

	public List<User> getUsers() {
		return users;
	}
}
