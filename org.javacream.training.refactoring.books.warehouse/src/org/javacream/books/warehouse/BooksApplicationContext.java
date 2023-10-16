package org.javacream.books.warehouse;

public abstract class BooksApplicationContext {

	private static BooksService booksService;
	public static BooksService getBooksService() {
		return booksService;
	}

	public static RandomIsbnGenerator getIsbnGenerator() {
		return isbnGenerator;
	}

	public static SimpleStoreService getStoreService() {
		return storeService;
	}

	private static RandomIsbnGenerator isbnGenerator;
	private static SimpleStoreService storeService;
	
	static {
		isbnGenerator = new RandomIsbnGenerator();
		storeService = new SimpleStoreService();
		booksService = new BooksService();
		booksService.setRandomIsbnGenerator(isbnGenerator);
		booksService.setStoreService(storeService);
	}
	
}
