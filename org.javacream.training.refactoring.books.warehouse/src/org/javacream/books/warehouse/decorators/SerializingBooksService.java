package org.javacream.books.warehouse.decorators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.apache.commons.lang3.SerializationUtils;
import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BookException;
import org.javacream.books.warehouse.api.BooksService;

public class SerializingBooksService implements BooksService{

	private BooksService delegate;

	public String newBook(String title, Map<String, Object> options) throws BookException {
		return delegate.newBook(title, options);
	}

	public void deleteBookByIsbn(String isbn) throws BookException {
		delegate.deleteBookByIsbn(isbn);
	}

	@Override
	public Book findBookByIsbn(String isbn) throws BookException {
		Book book = delegate.findBookByIsbn(isbn);
		return SerializationUtils.clone(book);
	}

	@Override
	public Book updateBook(Book bookDetailValue) throws BookException {
		Book toUpdate = SerializationUtils.clone(bookDetailValue);
		Book updated = delegate.updateBook(toUpdate);
		return SerializationUtils.clone(updated);
	}

	@Override
	public Collection<Book> findAllBooks() {
		ArrayList<Book> result = new ArrayList<>(delegate.findAllBooks());
		return SerializationUtils.clone(result);
	}

	public void setDelegate(BooksService delegate) {
		this.delegate = delegate;
	}
	
	
}
