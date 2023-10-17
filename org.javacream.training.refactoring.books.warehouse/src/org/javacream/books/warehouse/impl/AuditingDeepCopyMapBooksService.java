package org.javacream.books.warehouse.impl;

import java.util.Collection;
import java.util.Map;

import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BookException;

public class AuditingDeepCopyMapBooksService extends DeepCopyMapBooksService {

	@Override
	public Book findBookByIsbn(String isbn) throws BookException {
		System.out.println("called findBookByIsbn");
		return super.findBookByIsbn(isbn);
	}

	@Override
	public Book updateBook(Book bookDetailValue) throws BookException {
		System.out.println("called updateBook");
		return super.updateBook(bookDetailValue);
	}

	@Override
	public Collection<Book> findAllBooks() {
		System.out.println("called findAllBooks");
		return super.findAllBooks();
	}

	@Override
	public String newBook(String title, Map<String, Object> options) throws BookException {
		System.out.println("called newBook");
		return super.newBook(title, options);
	}

	@Override
	public void deleteBookByIsbn(String isbn) throws BookException {
		System.out.println("called deleteBookByIsbn");
		super.deleteBookByIsbn(isbn);
	}

}
