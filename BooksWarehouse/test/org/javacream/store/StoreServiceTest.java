package org.javacream.store;

import org.javacream.books.context.ApplicationContext;
import org.junit.Before;
import org.junit.Test;

public class StoreServiceTest {

	private StoreService storeService;
	@Before public void init(){
		storeService = ApplicationContext.getStoreService();
	}
	@Test public void testStoreService(){
		storeService.getStock("book", "id");
	}
}
