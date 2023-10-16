package org.javacream.books.warehouse;

import java.util.HashMap;
import java.util.Map;

import org.javacream.books.isbngenerator.impl.RandomIsbnGenerator;
import org.javacream.books.isbngennerator.api.IsbnGenerator;

public abstract class BooksApplicationContext {
	public static final String ISBN = "ISBN-Test";
	public static final String TITLE = "Test-Title";
	public static final Double PRICE = 19.99;
	private static BooksService booksService;
	public static BooksService getBooksService() {
		return booksService;
	}

	public static IsbnGenerator getIsbnGenerator() {
		return isbnGenerator;
	}

	public static SimpleStoreService getStoreService() {
		return storeService;
	}

	private static RandomIsbnGenerator isbnGenerator;
	private static SimpleStoreService storeService;
	public static String ISBN_GENERATOR_PREFIX = "ISBN-";
	
	public static void init(){
		isbnGenerator = new RandomIsbnGenerator();
		storeService = new SimpleStoreService();
		booksService = new BooksService();
		Map<String, Book> books = new HashMap<>();
		Book book = new Book();
		book.setIsbn(ISBN);
		book.setTitle(TITLE);
		book.setPrice(PRICE);
		books.put(book.getIsbn(), book);
		booksService.setRandomIsbnGenerator(isbnGenerator);
		booksService.setStoreService(storeService);
		booksService.setBooks(books);
		isbnGenerator.setPrefix(ISBN_GENERATOR_PREFIX );
	}
	
}
