package org.javacream.books.warehouse.decorators;

import java.util.Map;

import org.javacream.books.warehouse.Book;
import org.javacream.books.warehouse.BookException;
import org.javacream.books.warehouse.notification.BookNotificationSupport;

public class NotifyingBooksService extends BaseBooksServiceDecorator{
	

	private BookNotificationSupport bookNotificationSupport;
	
	
	public void setBookNotificationSupport(BookNotificationSupport bookNotificationSupport) {
		this.bookNotificationSupport = bookNotificationSupport;
	}

	@Override
	public String newBook(String title, Map<String, Object> options) throws BookException {
		String isbn = super.newBook(title, options);
		bookNotificationSupport.fireBookCreatedNotification(isbn);
		return isbn;
	}

	@Override
	public void deleteBookByIsbn(String isbn) throws BookException {
		super.deleteBookByIsbn(isbn);
		bookNotificationSupport.fireBookDeletedNotification(isbn);
	}

	public Book updateBook(Book bookDetailValue) throws BookException {
		Book updated = delegate.updateBook(bookDetailValue);
		bookNotificationSupport.fireBookUpdatedNotification(bookDetailValue.getIsbn());
		return updated;
	}


}
