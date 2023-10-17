package org.javacream.books.warehouse.impl.decorators;

import java.util.Collection;
import java.util.Map;

import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BookException;
import org.javacream.books.warehouse.api.BooksService;

public class AuditBooksService implements BooksService{
	
	private BooksService delegate;

	public void setDelegate(BooksService delegate) {
		this.delegate = delegate;
	}
	@Override
	public Book findBookByIsbn(String isbn) throws BookException {
		System.out.println("called findBookByIsbn");
		return delegate.findBookByIsbn(isbn);
	}

	@Override
	public Book updateBook(Book bookDetailValue) throws BookException {
		System.out.println("called updateBook");
		return delegate.updateBook(bookDetailValue);
	}

	@Override
	public Collection<Book> findAllBooks() {
		System.out.println("called findAllBooks");
		return delegate.findAllBooks();
	}

	@Override
	public String newBook(String title, Map<String, Object> options) throws BookException {
		System.out.println("called newBook");
		return delegate.newBook(title, options);
	}

	@Override
	public void deleteBookByIsbn(String isbn) throws BookException {
		System.out.println("called deleteBookByIsbn");
		delegate.deleteBookByIsbn(isbn);
	}
}
