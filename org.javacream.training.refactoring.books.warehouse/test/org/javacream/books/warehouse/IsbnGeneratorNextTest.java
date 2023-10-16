package org.javacream.books.warehouse;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class IsbnGeneratorNextTest {

	private RandomIsbnGenerator isbnGenerator;

	@Before
	public void initTest() {
		isbnGenerator = new RandomIsbnGenerator();
	}

	@Test
	public void creates_an_isbn() {
		String isbn = isbnGenerator.next();
		Assert.assertNotNull(isbn);

	}
	@Test
	public void createdIsbnStartsWithISBN() {
		String isbn = isbnGenerator.next();
		Assert.assertTrue(isbn.startsWith("ISBN-"));

	}

	@Test
	public void creates_unique_isbns() {
		String isbn1 = isbnGenerator.next();
		String isbn2 = isbnGenerator.next();
		Assert.assertNotEquals(isbn1, isbn2);

	}


}