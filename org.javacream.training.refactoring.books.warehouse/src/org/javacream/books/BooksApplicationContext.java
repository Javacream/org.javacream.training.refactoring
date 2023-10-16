package org.javacream.books;

import java.util.HashMap;
import java.util.Map;

import org.javacream.books.isbngenerator.api.IsbnGenerator;
import org.javacream.books.isbngenerator.impl.RandomIsbnGenerator;
import org.javacream.books.store.api.StoreService;
import org.javacream.books.store.impl.SimpleStoreService;
import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BooksService;
import org.javacream.books.warehouse.impl.MapBooksService;

public abstract class BooksApplicationContext {
	public static final String ISBN = "ISBN-Test";
	public static final String TITLE = "Test-Title";
	public static final Double PRICE = 19.99;
	private static MapBooksService booksService;
	public static BooksService getBooksService() {
		return booksService;
	}

	public static IsbnGenerator getIsbnGenerator() {
		return isbnGenerator;
	}

	public static StoreService getStoreService() {
		return storeService;
	}

	private static RandomIsbnGenerator isbnGenerator;
	private static StoreService storeService;
	public static String ISBN_GENERATOR_PREFIX = "ISBN-";
	
	public static void init(){
		isbnGenerator = new RandomIsbnGenerator();
		storeService = new SimpleStoreService();
		booksService = new MapBooksService();
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
