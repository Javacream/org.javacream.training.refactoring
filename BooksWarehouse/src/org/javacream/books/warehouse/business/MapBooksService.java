package org.javacream.books.warehouse.business;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.javacream.books.isbngenerator.IsbnGenerator;
import org.javacream.books.isbngenerator.business.RandomIsbnGenerator;
import org.javacream.books.warehouse.Book;
import org.javacream.books.warehouse.BookException;
import org.javacream.books.warehouse.BooksCreator;
import org.javacream.books.warehouse.BooksService;
import org.javacream.store.StoreService;

/**
 * @author Dr. Rainer Sawitzki
 * @company Javacream
 * @mailto rainer.sawitzki@javacream.org
 * 
 */

public class MapBooksService implements BooksService{

	
	private StoreService storeService;
	
	private IsbnGenerator isbnGenerator;
	private BooksCreator booksCreator;
	
	public void setBooksCreator(BooksCreator booksCreator) {
		this.booksCreator = booksCreator;
	}

	public MapBooksService(){
		
	}
	private Map<String, Book> books;
	
	{
		books = new HashMap<String, Book>();
		//storeService = StoreService.getInstance();//new StoreService();
		isbnGenerator = new RandomIsbnGenerator();
		for (int i = 1; i <= 10; i++){
			Book book = new Book();
			book.setIsbn("ISBN" + i);
			book.setTitle("Title" + i);
			book.setPrice(9.99*i);
			books.put(book.getIsbn(), book);
		}
	}

	

	/* (non-Javadoc)
	 * @see org.javacream.books.warehouse.business.BooksService#newBook(java.lang.String, java.util.Map)
	 */
	@Override
	public String newBook(String title, Map<String, Object> options) throws BookException {
		String isbn = isbnGenerator.createIsbn();
		options.put("isbn", isbn);
		options.put("title", title);
		Book book = booksCreator.create(options);
		books.put(isbn, book);
		return isbn;
	}

	/* (non-Javadoc)
	 * @see org.javacream.books.warehouse.business.BooksService#findBookByIsbn(java.lang.String)
	 */
	@Override
	public Book findBookByIsbn(String isbn) throws BookException {
		Book result = (Book) books.get(isbn);
		if (result == null) {
			throw new BookException(BookException.BookExceptionType.NOT_FOUND,
					isbn);
		}
		int stock = storeService.getStock("books", isbn);
		result.setAvailable(stock > 0);
		return result;
	}

	/* (non-Javadoc)
	 * @see org.javacream.books.warehouse.business.BooksService#updateBook(org.javacream.books.warehouse.Book)
	 */
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

	/* (non-Javadoc)
	 * @see org.javacream.books.warehouse.business.BooksService#deleteBookByIsbn(java.lang.String)
	 */
	@Override
	public void deleteBookByIsbn(String isbn) throws BookException {
		Object result = books.remove(isbn);
		if (result == null) {
			throw new BookException(
					BookException.BookExceptionType.NOT_DELETED, isbn);
		}
	}


	/* (non-Javadoc)
	 * @see org.javacream.books.warehouse.business.BooksService#findAllBooks()
	 */
	@Override
	public Collection<Book> findAllBooks() {
		return new ArrayList<Book>(books.values());
	}

	public void setStoreService(StoreService storeService) {
		this.storeService = storeService;
	}

	public void setIsbnGenerator(IsbnGenerator isbnGenerator) {
		this.isbnGenerator = isbnGenerator;
	}
	
}
