package org.javacream.books.warehouse;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.javaceream.training.refactoring.notification.NotificationBus;
import org.javacream.books.isbngenerator.api.IsbnGeneratorService;
import org.javacream.books.isbngenerator.impl.RandomIsbnGeneratorService;
import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BookCreator;
import org.javacream.books.warehouse.api.BooksService;
import org.javacream.books.warehouse.api.SchoolBook;
import org.javacream.books.warehouse.api.SpecialistBook;
import org.javacream.books.warehouse.decorators.NotifyingBooksService;
import org.javacream.books.warehouse.decorators.SerializingBooksService;
import org.javacream.books.warehouse.impl.MapBooksService;
import org.javacream.store.api.StoreService;
import org.javacream.store.impl.SimpleStoreService;
import org.javacream.util.decorators.TracingDecorator;

public abstract class BooksWarehouseApplicationContext {

	private static BooksService booksService;
	private static IsbnGeneratorService isbnGeneratorService;
	private static StoreService storeService;
	private static NotificationBus notificationBus;

	public static NotificationBus notificationBus() {
		return notificationBus;
	}

	static {
		MapBooksService mapBooksService = new MapBooksService();
		RandomIsbnGeneratorService randomIsbnGeneratorService = new RandomIsbnGeneratorService();
		SimpleStoreService simpleStoreService = new SimpleStoreService();
		Map<Set<String>, BookCreator> bookCreators = createBookCreators();
		SerializingBooksService serializingBooksService = new SerializingBooksService();
		notificationBus = new NotificationBus(); 
		NotifyingBooksService notifyingBooksService = new NotifyingBooksService();
		IsbnGeneratorService tracingIsbnGeneratorService = TracingDecorator.decorate(IsbnGeneratorService.class, randomIsbnGeneratorService);
		BooksService tracingBooksService =TracingDecorator.decorate(BooksService.class, serializingBooksService); 
		notifyingBooksService.setDelegate(mapBooksService);
		notifyingBooksService.setNotificationBus(notificationBus);
		serializingBooksService.setDelegate(notifyingBooksService);

		randomIsbnGeneratorService.setPrefix("ISBN:");
		simpleStoreService.setStock(42);
		simpleStoreService.setNotificationBus(notificationBus);
		mapBooksService.setIsbnGeneratorService(tracingIsbnGeneratorService);
		mapBooksService.setStoreService(simpleStoreService);
		mapBooksService.setBookCreators(bookCreators);

		booksService = tracingBooksService; 
		isbnGeneratorService = randomIsbnGeneratorService;
		storeService = simpleStoreService;
	}

	public static BooksService booksService() {
		return booksService;
	}

	private static Map<Set<String>, BookCreator> createBookCreators() {
		Map<Set<String>, BookCreator> creators = new HashMap<>();
		Set<String> bookSet = Set.of();
		creators.put(bookSet, (isbn, title, options) -> {
			Book book = new Book();
			book.setTitle(title);
			book.setIsbn(isbn);
			return book;
		});

		Set<String> schoolBookSet = Set.of("subject", "year");
		creators.put(schoolBookSet, (isbn, title, options) -> {
			SchoolBook book = new SchoolBook();
			book.setTitle(title);
			book.setIsbn(isbn);
			book.setSubject(options.get("subject").toString());
			book.setYear(Integer.parseInt(options.get("year").toString()));
			return book;
		});

		Set<String> specialistBookSet = Set.of("topic");
		creators.put(specialistBookSet, (isbn, title, options) -> {
			SpecialistBook book = new SpecialistBook();
			book.setTitle(title);
			book.setIsbn(isbn);
			book.setTopic(options.get("topic").toString());
			return book;
		});

		return creators;
	}

	public static IsbnGeneratorService isbnGeneratorService() {
		return isbnGeneratorService;
	}

	public static StoreService storeService() {
		return storeService;
	}
}
