package org.javacream.training.refactoring.books.warehouse;

import java.util.HashMap;

import org.javacream.books.warehouse.BookException;
import org.javacream.books.warehouse.BooksService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CreateBookTest {

	private BooksService booksService;
	@Before
	public void setUp() {
		booksService = new BooksService();
	}
	@Test public void newBookWithTitleJavaRetrievesIsbn() throws BookException {
		final String TITLE = "Java";
		HashMap<String, Object> options = new HashMap<>();
		String isbn = booksService.newBook(TITLE, options);
		Assert.assertTrue(isbn != null);
	}
}
