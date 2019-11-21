package org.javacream.books.warehouse;

import org.javacream.books.isbngenerator.api.IsbnGeneratorService;
import org.javacream.books.isbngenerator.impl.RandomIsbnGeneratorService;
import org.javacream.books.warehouse.api.BooksService;
import org.javacream.books.warehouse.impl.MapBooksService;
import org.javacream.store.api.StoreService;
import org.javacream.store.impl.SimpleStoreService;

public abstract class BooksWarehouseApplicationContext {

	private static BooksService booksService;
	private static IsbnGeneratorService isbnGeneratorService;
	private static StoreService storeService;
	
	
	static {
		MapBooksService mapBooksService = new MapBooksService();
		RandomIsbnGeneratorService randomIsbnGeneratorService = new RandomIsbnGeneratorService();
		SimpleStoreService simpleStoreService = new SimpleStoreService();

		randomIsbnGeneratorService.setPrefix("ISBN:");
		simpleStoreService.setStock(42);
		mapBooksService.setIsbnGeneratorService(randomIsbnGeneratorService);
		mapBooksService.setStoreService(simpleStoreService);
		
		booksService =mapBooksService;
		isbnGeneratorService = randomIsbnGeneratorService;
		storeService = simpleStoreService;
	}
	
	public static BooksService booksService() {
		return booksService;
	}

	public static IsbnGeneratorService isbnGeneratorService() {
		return isbnGeneratorService;
	}
	public static StoreService storeService() {
		return storeService;
	}
}
