package org.javacream.store.impl;

import org.javacream.store.api.StoreService;

public class SimpleStoreService implements StoreService {
	private int stock;

	public void setStock(int stock) {
		this.stock = stock;
	}
	@Override
	public int getStock(String category, String id) {
		// retrieve stock from external service, e.g. web service call...
		return stock;
	}
}
