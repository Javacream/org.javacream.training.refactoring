package org.javacream.books.context;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.javacream.books.isbngenerator.IsbnGenerator;
import org.javacream.books.isbngenerator.decorators.SimpleIsbnGeneratorDecorator;
import org.javacream.books.isbngenerator.ws.FetchingIsbnGeneratorWebServiceBusinessDelegate;
import org.javacream.books.isbngenerator.ws.IsbnGeneratorWebServiceBusinessDelegate;
import org.javacream.books.isbngenerator.ws.IsbnGeneratorWebServiceService;
import org.javacream.books.isbngenerator.ws.fetching.FetchingIsbnGeneratorWebServiceService;
import org.javacream.books.order.OrderService;
import org.javacream.books.order.business.OrderServiceImpl;
import org.javacream.books.warehouse.Book;
import org.javacream.books.warehouse.BooksCreator;
import org.javacream.books.warehouse.BooksService;
import org.javacream.books.warehouse.SchoolBook;
import org.javacream.books.warehouse.SpecialistBook;
import org.javacream.books.warehouse.business.MapBooksService;
import org.javacream.books.warehouse.decorators.NotifyingBooksService;
import org.javacream.books.warehouse.decorators.SerializingBooksService;
import org.javacream.books.warehouse.decorators.ValidatingBooksService;
import org.javacream.books.warehouse.notification.BookNotificationListener;
import org.javacream.books.warehouse.notification.BookNotificationSupport;
import org.javacream.books.warehouse.notification.impl.SimpleBookNotificationListener;
import org.javacream.store.StoreService;
import org.javacream.store.business.CustomStoreServiceAdapter;
import org.javacream.store.decorators.AuditingStoreServiceDecorator;
import org.javacream.util.IdGenerator;
import org.javacream.util.aspects.Aspect;
import org.javacream.util.aspects.impl.ProfilingAspectListener;
import org.javacream.util.aspects.impl.TracingAspectListener;

public class ApplicationContext {

	private static IsbnGenerator isbnGenerator;
	private static IdGenerator idGenerator;

	public static IsbnGenerator getIsbnGenerator() {
		return isbnGenerator;
	}

	public static StoreService getStoreService() {
		return storeService;
	}

	public static BooksService getBooksService() {
		return booksService;
	}
	public static IdGenerator getIdGenerator() {
		return idGenerator;
	}

	private static StoreService storeService;
	private static BooksService booksService;
	private static OrderService orderService;
	static {
		// Create Business Objects
		//RandomIsbnGenerator randomIsbnGenerator = new RandomIsbnGenerator();
		IsbnGeneratorWebServiceBusinessDelegate isbnGeneratorWebService = new IsbnGeneratorWebServiceBusinessDelegate();
		isbnGeneratorWebService.setDelegate(new IsbnGeneratorWebServiceService().getIsbnGeneratorWebServicePort());

		FetchingIsbnGeneratorWebServiceBusinessDelegate fetchingIsbnGeneratorWebServiceBusinessDelegate = new FetchingIsbnGeneratorWebServiceBusinessDelegate();
		fetchingIsbnGeneratorWebServiceBusinessDelegate.setDelegate(new FetchingIsbnGeneratorWebServiceService().getFetchingIsbnGeneratorWebServicePort());
		StoreService simpleStoreService = new CustomStoreServiceAdapter();//new StoreServiceImpl();
		MapBooksService mapBooksService = new MapBooksService();
		IdGenerator randomIdGenerator = new IdGenerator();
		OrderServiceImpl simpleOrderService = new OrderServiceImpl();
		ArrayList<BookNotificationListener> listeners = new ArrayList<BookNotificationListener>();
		listeners.add(new SimpleBookNotificationListener());
		
		BooksCreator booksCreator = new BooksCreator();
		Set<String> bookParameterSet = new HashSet<String>();
		bookParameterSet.add("isbn");
		bookParameterSet.add("title");
		booksCreator.addBookCreator(bookParameterSet, (options -> {Book book = new Book(); book.setIsbn((String)options.get("isbn")); book.setTitle((String)options.get("title")); return book;}));
		
		Set<String> specialistBookParameterSet = new HashSet<String>();
		specialistBookParameterSet.addAll(bookParameterSet);
		specialistBookParameterSet.add("topic");
		booksCreator.addBookCreator(specialistBookParameterSet, (options -> {SpecialistBook book = new SpecialistBook(); book.setIsbn((String)options.get("isbn")); book.setTitle((String)options.get("title")); book.setTopic((String)options.get("topic")); return book;}));
		
		Set<String> schoolBookParameterSet = new HashSet<String>();
		schoolBookParameterSet.addAll(bookParameterSet);
		schoolBookParameterSet.add("subject");
		schoolBookParameterSet.add("year");
		booksCreator.addBookCreator(schoolBookParameterSet, (options -> {SchoolBook book = new SchoolBook(); book.setIsbn((String)options.get("isbn")); book.setTitle((String)options.get("title")); book.setSubject((String)options.get("subject")); book.setYear((Integer)options.get("year"));return book;}));
		
		//listeners.add(new SimpleBookNotificationListener());
		
		//Create Decorators
		SimpleIsbnGeneratorDecorator simpleIsbnGeneratorDecorator = new SimpleIsbnGeneratorDecorator();
		AuditingStoreServiceDecorator auditingStoreServiceDecoratorForBooksService = new AuditingStoreServiceDecorator();
		ValidatingBooksService  validatingBooksService = new ValidatingBooksService();
		SerializingBooksService serializingBooksService = new SerializingBooksService();
			
		NotifyingBooksService notifyingBooksService = new NotifyingBooksService();
		BookNotificationSupport bookNotificationSupport = new BookNotificationSupport();

		
		
		//Create Decorators List for Actors
		simpleIsbnGeneratorDecorator.setIsbnGenerator(isbnGeneratorWebService);
		auditingStoreServiceDecoratorForBooksService.setStoreService(simpleStoreService);
		serializingBooksService.setDelegate(validatingBooksService);
		validatingBooksService.setDelegate(mapBooksService);
		notifyingBooksService.setDelegate(serializingBooksService);
		notifyingBooksService.setBookNotificationSupport(bookNotificationSupport);
		
		
		// Set Dependencies
		mapBooksService.setIsbnGenerator(simpleIsbnGeneratorDecorator);
		mapBooksService.setStoreService(auditingStoreServiceDecoratorForBooksService);
		//randomIsbnGenerator.setIdGenerator(randomIdGenerator);
		simpleOrderService.setBooksService(mapBooksService);
		simpleOrderService.setIdGenerator(randomIdGenerator);
		simpleOrderService.setStoreService(simpleStoreService);
		bookNotificationSupport.setListeners(listeners);
		mapBooksService.setBooksCreator(booksCreator);
		
		//Aspects
		TracingAspectListener tracingAspectListener = new TracingAspectListener();
		StoreService aspectedStoreService = Aspect.decorate(simpleStoreService, tracingAspectListener, new ProfilingAspectListener());
		IsbnGenerator aspectedIsbnGenerator = Aspect.decorate(fetchingIsbnGeneratorWebServiceBusinessDelegate, tracingAspectListener);
		//Set Business Objects
		
		
		isbnGenerator = aspectedIsbnGenerator;
		storeService = aspectedStoreService;
		booksService = notifyingBooksService;
		idGenerator = randomIdGenerator;
		orderService = simpleOrderService;
	}
	public static OrderService getOrderService() {
		return orderService;
	}
}
