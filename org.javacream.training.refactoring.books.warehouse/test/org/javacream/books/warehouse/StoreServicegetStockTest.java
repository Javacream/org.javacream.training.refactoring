package org.javacream.books.warehouse;

import org.junit.Before;
import org.junit.Test;

public class StoreServicegetStockTest {
	private SimpleStoreService storeService;

	@Before
	public void init() {
		storeService = new SimpleStoreService();
	}

	@Test
	public void testStoreService() {
		storeService.getStock("id");
	}
}