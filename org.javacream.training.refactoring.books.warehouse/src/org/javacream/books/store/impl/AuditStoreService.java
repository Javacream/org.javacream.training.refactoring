package org.javacream.books.store.impl;

import org.javacream.books.store.api.StoreService;

public class AuditStoreService implements StoreService {
	private StoreService delegate;
	public void setDelegate(StoreService delegate) {
		this.delegate = delegate;
	}
	@Override
	public int getStock(String id) {
		System.out.println("calling getStock");
		return delegate.getStock(id);
	}

}
