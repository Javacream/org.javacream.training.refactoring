package org.javacream.books.warehouse.impl.decorators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.apache.commons.lang3.SerializationUtils;
import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BookException;
import org.javacream.books.warehouse.api.BooksService;

public class DeepCopyBooksService implements BooksService{

	private BooksService delegate;

	public String newBook(String title, Map<String, Object> options) throws BookException {
		return delegate.newBook(title, options);
	}

	public void deleteBookByIsbn(String isbn) throws BookException {
		delegate.deleteBookByIsbn(isbn);
	}

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
