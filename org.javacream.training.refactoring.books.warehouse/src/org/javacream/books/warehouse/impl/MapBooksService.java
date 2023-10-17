package org.javacream.books.warehouse.impl;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.javacream.books.isbngenerator.api.IsbnGenerator;
import org.javacream.books.store.api.StoreService;
import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BookException;
import org.javacream.books.warehouse.api.BooksService;

public class MapBooksService implements BooksService{

	private IsbnGenerator isbnGenerator;
	public MapBooksService(){
	}
	private Map<String, Book> books;
	private StoreService storeService;
	private Map<Set<String>, BookCreator> booksCreators;
	public void setBooksCreators(Map<Set<String>, BookCreator> booksCreators) {
		this.booksCreators = booksCreators;
	}

	public void setBooks(Map<String, Book> books) {
		this.books = books;
	}

	@Override
	public String newBook(String title, Map<String, Object> options) throws BookException {
		String isbn = isbnGenerator.next(); 
		Book book = booksCreators.get(options.keySet()).create(isbn,  title, options);
		books.put(isbn, book);
		return isbn;
	}

	@Override
	public Book findBookByIsbn(String isbn) throws BookException {
		Book result = (Book) books.get(isbn);
		if (result == null) {
			throw new BookException(BookException.BookExceptionType.NOT_FOUND,
					isbn);
		}
		int stock = storeService.getStock(isbn);

		result.setAvailable(stock > 0);
		return result;
	}

	@Override
	public Book updateBook(Book bookDetailValue) throws BookException {
		if (bookDetailValue.getPrice() <= 0) {
			throw new BookException(BookException.BookExceptionType.CONSTRAINT,
					"price <= 0");
		}

		Book value = books.get(bookDetailValue.getIsbn());
		value.setTitle(bookDetailValue.getTitle());
		value.setPrice(bookDetailValue.getPrice());
		value.setAvailable(bookDetailValue.isAvailable());
		return value;
	}

	@Override
	public void deleteBookByIsbn(String isbn) throws BookException {
		Object result = books.remove(isbn);
		if (result == null) {
			throw new BookException(
					BookException.BookExceptionType.NOT_DELETED, isbn);
		}
	}


	@Override
	public Collection<Book> findAllBooks() {
		return books.values();
	}

	public void setRandomIsbnGenerator(IsbnGenerator randomIsbnGenerator) {
		this.isbnGenerator = randomIsbnGenerator;
	}

	public void setStoreService(StoreService storeService) {
		this.storeService = storeService;
	}
	
}
