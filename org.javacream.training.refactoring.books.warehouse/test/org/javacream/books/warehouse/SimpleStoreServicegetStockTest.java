package org.javacream.books.warehouse;

import org.junit.Before;
import org.junit.Test;

public class SimpleStoreServicegetStockTest {
	private SimpleStoreService storeService;

	@Before
	public void init() {
		storeService = BooksWarehouseApplicationContext.simpleStoreService();
	}

	@Test
	public void testStoreService() {
		storeService.getStock("book", "id");
	}
}
