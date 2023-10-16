package org.javacream.books.warehouse;

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
		booksService = BooksApplicationContext.getBooksService();
	}

	@Test
	public void findBookAllBooks() {
		Assert.assertNotNull(booksService.findAllBooks());
	}

}
