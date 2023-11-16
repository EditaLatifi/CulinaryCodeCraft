package org.makerminds.jcoaching.internship.restaurantpoint.model;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {

	private String name;
	private String address;

	private List<Menu> menuList = new ArrayList<>();
	private List<Table> tableList = new ArrayList<>();

	public Restaurant() {
	}

	public Restaurant(String name, String address) {
		this.name = name;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}

	public List<Table> getTableList() {
		return tableList;
	}

	public void setTableList(List<Table> tableList) {
		this.tableList = tableList;
	}

	@Override
	public String toString() {
		return this.getName();
	}
}
