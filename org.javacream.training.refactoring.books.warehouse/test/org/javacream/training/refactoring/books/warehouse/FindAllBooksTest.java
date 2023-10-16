package org.javacream.training.refactoring.books.warehouse;

import org.javacream.books.warehouse.BooksService;
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
		booksService = new BooksService();

	}

	@Test
	public void findBookAllBooks() {
		Assert.assertNotNull(booksService.findAllBooks());
	}

}
