package org.javacream.books.warehouse.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang3.SerializationUtils;
import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BookException;
import org.javacream.books.warehouse.api.BooksService;

public class DeepCopyBooksService {

	private BooksService delegate;

	public Book findBookByIsbn(String isbn) throws BookException {
		Book result = delegate.findBookByIsbn(isbn);
		result = (Book) SerializationUtils.clone(result);
		return result;
	}

	public Book updateBook(Book bookDetailValue) throws BookException {
		//Take a copy to prevent external manipulation!
		bookDetailValue = (Book) SerializationUtils.clone(bookDetailValue);
		return delegate.updateBook(bookDetailValue);
	}

	public Collection<Book> findAllBooks() {
		Collection<Book> books = delegate.findAllBooks();
		return SerializationUtils.clone(new ArrayList<>(books));
	}

	public void setDelegate(BooksService delegate) {
		this.delegate = delegate;
	}
}
