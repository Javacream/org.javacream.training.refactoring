package org.javacream.books.warehouse;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class IsbnGeneratorNextIsbnTest {

	private RandomIsbnGenerator isbnGenerator;

	@Before
	public void initTest() {
		isbnGenerator = BooksWarehouseApplicationContext.randomIsbnGenerator();
	}

	@Test
	public void creates_an_isbn() {
		String isbn = isbnGenerator.nextIsbn();
		Assert.assertNotNull(isbn);

	}

	@Test
	public void creates_unique_isbns() {
		String isbn1 = isbnGenerator.nextIsbn();
		String isbn2 = isbnGenerator.nextIsbn();
		Assert.assertNotEquals(isbn1, isbn2);

	}


}
