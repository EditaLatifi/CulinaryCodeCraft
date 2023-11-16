package org.makerminds.jcoaching.internship.restaurantpoint.controller.order;

import java.util.concurrent.atomic.AtomicLong;

/**
 * generates unique numbers for orders.
 * @author makerminds
 *
 */
public class OrderNumberGenerator {

	    private static final AtomicLong sequence = new AtomicLong(System.currentTimeMillis() / 1000000000);
	    
	    public static long getOrderNumber() {
	        return sequence.incrementAndGet();
	    }
}
