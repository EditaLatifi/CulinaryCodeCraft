package org.makerminds.jcoaching.internship.restaurantpoint.model;

/**
 * Table Data Model
 * 
 * @author makerminds
 *
 */
public class Table {
	private int tableNumber;
	private int seats;

	public Table(int tableNumber, int seats) {
		this.tableNumber = tableNumber;
		this.seats = seats;
	}

	public int getTableNumber() {
		return tableNumber;
	}

	public void setTableNumber(int tableNumber) {
		this.tableNumber = tableNumber;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}
}
