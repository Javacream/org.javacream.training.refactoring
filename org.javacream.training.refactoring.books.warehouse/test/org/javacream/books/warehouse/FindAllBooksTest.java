package org.javacream.books.warehouse;

import org.javacream.books.BooksApplicationContext;
import org.javacream.books.warehouse.api.BooksService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Dr. Rainer Sawitzki
 * @company Javacream
 * @mailto training@rainer-sawitzki.de
 * 
 */
public class FindAllBooksTest {

	private BooksService booksService;

	@Before
	public void init() {
		BooksApplicationContext.init();
		booksService = BooksApplicationContext.getBooksService();
	}

	@Test
	public void findBookAllBooks() {
		Assert.assertNotNull(booksService.findAllBooks());
	}

}
