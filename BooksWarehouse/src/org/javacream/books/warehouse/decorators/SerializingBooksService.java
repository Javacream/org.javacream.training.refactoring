package org.javacream.books.warehouse.decorators;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang.SerializationUtils;
import org.javacream.books.warehouse.Book;
import org.javacream.books.warehouse.BookException;

public class SerializingBooksService extends BaseBooksServiceDecorator{
	

	public Book findBookByIsbn(String isbn) throws BookException {
		return (Book)SerializationUtils.clone(delegate.findBookByIsbn(isbn));
	}

	public Book updateBook(Book bookDetailValue) throws BookException {
		return (Book)SerializationUtils.clone(delegate.updateBook((Book)SerializationUtils.clone(bookDetailValue)));
	}

	@SuppressWarnings("unchecked")
	public Collection<Book> findAllBooks() {
		return (Collection<Book>) SerializationUtils.clone(new ArrayList<Book>(delegate.findAllBooks()));
	}

}
