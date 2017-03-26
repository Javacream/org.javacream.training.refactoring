package org.javacream.store.decorators;

import java.util.Date;

import org.javacream.store.StoreService;

public class AuditingStoreServiceDecorator implements StoreService{

	private StoreService storeService;

	public int getStock(String category, String id) {
		System.out.println("Retrieving stock for category=" + category + ", id=" + id + " at " + new Date());
		return storeService.getStock(category, id);
	}

	public void setStoreService(StoreService storeService) {
		this.storeService = storeService;
	}
}
