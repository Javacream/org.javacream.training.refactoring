package org.javacream.books.warehouse;

import org.junit.Before;
import org.junit.Test;

public class StoreServicegetStockTest {
	private SimpleStoreService storeService;

	@Before
	public void init() {
		storeService = BooksApplicationContext.getStoreService();
	}

	@Test
	public void testStoreService() {
		storeService.getStock("id");
	}
}