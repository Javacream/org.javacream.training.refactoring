package org.javacream.store.api;

import org.javacream.books.warehouse.BooksWarehouseApplicationContext;
import org.junit.Before;
import org.junit.Test;

public class SimpleStoreServicegetStockTest {
	private StoreService storeService;

	@Before
	public void init() {
		storeService = BooksWarehouseApplicationContext.storeService();
	}

	@Test
	public void testStoreService() {
		storeService.getStock("book", "id");
	}
}
