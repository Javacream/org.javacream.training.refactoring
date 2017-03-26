package org.javacream.books.warehouse;

import org.javacream.books.context.ApplicationContext;
import org.javacream.books.isbngenerator.IsbnGenerator;
import org.javacream.books.isbngenerator.business.RandomIsbnGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class IsbnGeneratorTest {

	private IsbnGenerator isbnGenerator;

	@Before
	public void initTest() {
		isbnGenerator = ApplicationContext.getIsbnGenerator();
	}

	@Test
	public void creates_an_isbn() {
		String isbn = isbnGenerator.createIsbn();
		Assert.assertNotNull(isbn);

	}

	@Test
	public void creates_unique_isbns() {
		String isbn1 = isbnGenerator.createIsbn();
		String isbn2 = isbnGenerator.createIsbn();
		Assert.assertNotEquals(isbn1, isbn2);

	}

	@Test
	public void created_isbn_starts_with_isbnprefix() {
		String isbn = isbnGenerator.createIsbn();
		Assert.assertTrue(isbn.startsWith(RandomIsbnGenerator.ISBN_PREFIX));

	}

}
