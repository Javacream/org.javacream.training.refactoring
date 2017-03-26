package org.javacream.store.aspects;

import org.javacream.store.StoreService;

public class TracingStoreServiceAspect implements StoreService{
	private StoreService delegate;

	public void setDelegate(StoreService delegate) {
		this.delegate = delegate;
	}

	@Override
	public int getStock(String category, String id) {
		System.out.println("Entering method getStock");
		try {
			int stock = delegate.getStock(category, id);
			System.out.println("returning from getStock");
			return stock;
		} catch (RuntimeException e) {
			System.out.println("throwing from getStock");
			throw e;
		}
	}
}
