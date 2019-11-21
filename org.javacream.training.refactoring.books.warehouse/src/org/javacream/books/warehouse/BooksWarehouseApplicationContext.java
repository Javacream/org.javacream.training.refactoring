package org.javacream.books.warehouse;

public abstract class BooksWarehouseApplicationContext {

	private static BooksService booksService;
	private static RandomIsbnGenerator randomIsbnGenerator;
	private static SimpleStoreService simpleStoreService;
	
	
	static {
		booksService = new BooksService();
		randomIsbnGenerator = new RandomIsbnGenerator();
		simpleStoreService = new SimpleStoreService();

		randomIsbnGenerator.setPrefix("ISBN:");
		simpleStoreService.setStock(42);
		booksService.setIsbnGenerator(randomIsbnGenerator);
		booksService.setSimpleStoreService(simpleStoreService);
	}
	
	public static BooksService booksService() {
		return booksService;
	}

	public static RandomIsbnGenerator randomIsbnGenerator() {
		return randomIsbnGenerator;
	}
	public static SimpleStoreService simpleStoreService() {
		return simpleStoreService;
	}
}
