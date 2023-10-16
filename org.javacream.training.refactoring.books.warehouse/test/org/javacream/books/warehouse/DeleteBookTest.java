package org.javacream.books.warehouse;

import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BookException;
import org.javacream.books.warehouse.api.BooksApplicationContext;
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
public class DeleteBookTest {

	private BooksService booksService;
	private String ISBN;
	private static final String WRONG_ISBN = "##ISBN##";
	@Before
	public void init() {
		BooksApplicationContext.init();
		booksService = BooksApplicationContext.getBooksService();
		ISBN = BooksApplicationContext.ISBN;
	}

	@Test
	public void deleteBookByIsbnOk() throws BookException {
		Book book = booksService.findBookByIsbn(ISBN);
		booksService.deleteBookByIsbn(ISBN);
		Assert.assertFalse(booksService.findAllBooks().contains(book));
	}

	@Test(expected = BookException.class)
	public void deleteBookByIsbnWrong() throws BookException {
		booksService.deleteBookByIsbn(WRONG_ISBN);
	}


}
