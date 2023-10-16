package org.javacream.books.store.impl;

import org.javacream.books.store.api.StoreService;

public class SimpleStoreService implements StoreService {
	@Override
	public int getStock(String id) {
		// retrieve stock from external service, e.g. web service call...
		return 0;
	}
}
