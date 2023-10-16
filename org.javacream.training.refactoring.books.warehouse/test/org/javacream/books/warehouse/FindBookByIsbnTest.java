package org.javacream.books.warehouse;

import org.javacream.books.BooksApplicationContext;
import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BookException;
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
public class FindBookByIsbnTest {

	private BooksService booksService;
	private static final String WRONG_ISBN = "##ISBN##";
	private String ISBN;
	@Before
	public void init() {
		BooksApplicationContext.init();
		booksService = BooksApplicationContext.getBooksService();
		ISBN = BooksApplicationContext.ISBN;
	}

	@Test
	public void findBookByIsbnOk() throws BookException {
		Book book = booksService.findBookByIsbn(ISBN);
		Assert.assertNotNull(book);
	}

	@Test(expected = BookException.class)
	public void findBookByIsbnWrong() throws BookException {
		booksService.findBookByIsbn(WRONG_ISBN);
	}

}
