package org.javacream.books.warehouse.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang3.SerializationUtils;
import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BookException;

public class DeepCopyMapBooksService extends MapBooksService{

	@Override
	public Book findBookByIsbn(String isbn) throws BookException {
		Book result = super.findBookByIsbn(isbn);
		result = (Book) SerializationUtils.clone(result);
		return result;

	}

	@Override
	public Book updateBook(Book bookDetailValue) throws BookException {
		//Take a copy to prevent external manipulation!
		bookDetailValue = (Book) SerializationUtils.clone(bookDetailValue);
		return super.updateBook(bookDetailValue);
	}

	@Override
	public Collection<Book> findAllBooks() {
		Collection<Book> books = super.findAllBooks();
		return SerializationUtils.clone(new ArrayList<>(books));
	}

}
