package org.javacream.books;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.javacream.books.isbngenerator.api.IsbnGenerator;
import org.javacream.books.isbngenerator.impl.RandomIsbnGenerator;
import org.javacream.books.store.api.StoreService;
import org.javacream.books.store.impl.SimpleStoreService;
import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BooksService;
import org.javacream.books.warehouse.api.PoetryBook;
import org.javacream.books.warehouse.api.SchoolBook;
import org.javacream.books.warehouse.api.SpecialistBook;
import org.javacream.books.warehouse.impl.BookCreator;
import org.javacream.books.warehouse.impl.MapBooksService;
import org.javacream.books.warehouse.impl.decorators.DeepCopyBooksService;
import org.javacream.util.aspects.AuditAspect;

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

	public static StoreService getStoreService() {
		return storeService;
	}

	private static RandomIsbnGenerator isbnGenerator;
	private static StoreService storeService;
	public static String ISBN_GENERATOR_PREFIX = "ISBN-";

	public static void init() {
		isbnGenerator = new RandomIsbnGenerator();
		SimpleStoreService simpleStoreService = new SimpleStoreService();
		MapBooksService mapBooksService = new MapBooksService();
		Map<String, Book> books = new HashMap<>();
		Map<Set<String>, BookCreator> creators = new HashMap<>();
		Set<String> booksOptions = new HashSet<>();
		BookCreator bookCreator = (isbn, title, options) -> {
			Book book = new Book();
			book.setIsbn(isbn);
			book.setTitle(title);
			return book;
		};
		Set<String> specialistBooksOptions = new HashSet<>();
		specialistBooksOptions.add("topic");
		BookCreator specialistBookCreator = (isbn, title, options) -> {
			SpecialistBook book = new SpecialistBook();
			book.setIsbn(isbn);
			book.setTitle(title);
			book.setTopic(options.get("topic").toString());
			return book;
		};
		Set<String> poetryBooksOptions = new HashSet<>();
		poetryBooksOptions.add("epoch");
		BookCreator poetryBookCreator = (isbn, title, options) -> {
			PoetryBook book = new PoetryBook();
			book.setIsbn(isbn);
			book.setTitle(title);
			book.setEpoch(options.get("epoch").toString());
			return book;
		};
		Set<String> schoolBooksOptions = new HashSet<>();
		schoolBooksOptions.add("year");
		schoolBooksOptions.add("subject");
		BookCreator schoolBookCreator = (isbn, title, options) -> {
			SchoolBook book = new SchoolBook();
			book.setIsbn(isbn);
			book.setTitle(title);
			book.setSubject(options.get("subject").toString());
			book.setYear(Integer.parseInt(options.get("year").toString()));
			return book;
		};

		creators.put(booksOptions, bookCreator);
		creators.put(specialistBooksOptions, specialistBookCreator);
		creators.put(schoolBooksOptions, schoolBookCreator);
		creators.put(poetryBooksOptions, poetryBookCreator);

		Book book = new Book();
		book.setIsbn(ISBN);
		book.setTitle(TITLE);
		book.setPrice(PRICE);
		books.put(book.getIsbn(), book);
		mapBooksService.setRandomIsbnGenerator(isbnGenerator);
		mapBooksService.setStoreService(simpleStoreService);
		mapBooksService.setBooks(books);
		mapBooksService.setBooksCreators(creators);
		isbnGenerator.setPrefix(ISBN_GENERATOR_PREFIX);
		storeService = AuditAspect.aspect(simpleStoreService);
		DeepCopyBooksService deepCopyBooksService = new DeepCopyBooksService();
		BooksService auditBooksService = AuditAspect.aspect(mapBooksService);
		deepCopyBooksService.setDelegate(auditBooksService);
		booksService = deepCopyBooksService;
	}

}
