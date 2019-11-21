package org.javacream.books.warehouse;

public class SimpleStoreService {
	private int stock;
	
	
	public int getStock() {
		return stock;
	}


	public void setStock(int stock) {
		this.stock = stock;
	}


	public int getStock(String category, String id) {
		// retrieve stock from external service, e.g. web service call...
		return 42;
	}
}
