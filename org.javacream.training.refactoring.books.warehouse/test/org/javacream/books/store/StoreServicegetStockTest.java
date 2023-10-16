package org.javacream.books.store;

import org.javacream.books.BooksApplicationContext;
import org.javacream.books.store.api.StoreService;
import org.junit.Before;
import org.junit.Test;

public class StoreServicegetStockTest {
	private StoreService storeService;

	@Before
	public void init() {
		BooksApplicationContext.init();
		storeService = BooksApplicationContext.getStoreService();
	}

	@Test
	public void testStoreService() {
		storeService.getStock("id");
	}
}