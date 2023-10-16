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
public class UpdateBooksTest {

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
	public void updateBookOk() throws BookException {
		final String NEW_TITLE = "CHANGED";
		Book book = booksService.findBookByIsbn(ISBN);
		book.setTitle(NEW_TITLE);
		book.setPrice(19.99);
		Book book2 = booksService.updateBook(book);
		Assert.assertTrue(NEW_TITLE.equals(book2.getTitle()));
	}

	@Test(expected = Exception.class)
	public void updateBookWrong() throws BookException {
		Book book = new Book();
		book.setIsbn(WRONG_ISBN);
		book.setPrice(19.99);
		booksService.updateBook(book);
	}
	@Test(expected = Exception.class)
	public void updateBookPriceNotGreaterZeroFails() throws BookException {
		Book book = booksService.findBookByIsbn(ISBN);
		book.setPrice(-19.99);
		booksService.updateBook(book);
	}
	
	@Test
	public void testDeepCopy() throws Exception{
		final String NEW_TITLE="C H A N G E D";
		Book book = booksService.findBookByIsbn(ISBN);
		book.setTitle(NEW_TITLE);
		book.setPrice(19.99);
		Book book2 = booksService.findBookByIsbn(ISBN);
		Assert.assertFalse(NEW_TITLE.equals(book2.getTitle()));
		booksService.updateBook(book);
		Book book3 = booksService.findBookByIsbn(ISBN);
		Assert.assertTrue(NEW_TITLE.equals(book3.getTitle()));
		
	}

}
