package org.javacream.ws;

import org.javacream.books.isbngenerator.ws.IsbnGeneratorWebService;
import org.javacream.books.isbngenerator.ws.IsbnGeneratorWebServiceService;
import org.javacream.store.ws.StoreWebService;
import org.javacream.store.ws.StoreWebServiceService;
import org.junit.Test;

public class WebServicesTests {

	
	@Test public void testIsbnGeneratorWebService(){
		IsbnGeneratorWebService isbnGeneratorWebService = new IsbnGeneratorWebServiceService().getIsbnGeneratorWebServicePort();
		System.out.println(isbnGeneratorWebService.generateNextIsbn());
	}
	@Test public void testStoreWebService(){
		StoreWebService storeWebService = new StoreWebServiceService().getStoreWebServicePort();
		System.out.println(storeWebService.retrieveStock("category", "item"));
	}
}
