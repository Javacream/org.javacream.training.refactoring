package org.javacream.books.order;

import org.javacream.books.context.ApplicationContext;
import org.javacream.books.order.Order.OrderStatus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class OrderServiceTest {

	private OrderService orderService;

	@Before public void init(){
		orderService =ApplicationContext.getOrderService();
	}
	
	
	@Test public void createOrderOk(){
		Order order = orderService.order("ISBN2", 20);
		Assert.assertTrue(order.getStatus() == OrderStatus.OK);
	}
	@Test public void createOrderPending(){
		Order order = orderService.order("ISBN2", 100);
		Assert.assertTrue(order.getStatus() == OrderStatus.PENDING);
	}
	
	@Test public void createOrderUnavailable(){
		Order order = orderService.order("##ISBN2##", 20);
		Assert.assertTrue(order.getStatus() == OrderStatus.UNAVAILABLE);
	}

}
