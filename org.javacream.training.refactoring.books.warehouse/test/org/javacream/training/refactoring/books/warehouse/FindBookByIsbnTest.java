package org.javacream.training.refactoring.books.warehouse;

import java.util.HashMap;

import org.javacream.books.warehouse.Book;
import org.javacream.books.warehouse.BookException;
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
public class FindBookByIsbnTest {

	private BooksService booksService;
	private String ISBN;
	private static final String WRONG_ISBN = "##ISBN##";
	@Before
	public void init() {
		booksService = new BooksService();
		try {
			ISBN = booksService.newBook("TEST", new HashMap<String, Object>());
		} catch (BookException e) {
			throw new RuntimeException(e.getMessage());
		}

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
