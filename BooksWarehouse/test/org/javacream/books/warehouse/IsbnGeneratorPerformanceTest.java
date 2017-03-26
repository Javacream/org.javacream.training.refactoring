package org.javacream.books.warehouse;

import org.javacream.books.context.ApplicationContext;
import org.javacream.books.isbngenerator.IsbnGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class IsbnGeneratorPerformanceTest {

	private IsbnGenerator isbnGenerator;

	@Before
	public void initTest() {
		isbnGenerator = ApplicationContext.getIsbnGenerator();
	}

	@Test
	public void creates_2000_isbns() {
		for (int i = 0; i < 2000; i++){
			String isbn = isbnGenerator.createIsbn();
			Assert.assertNotNull(isbn);
		}

	}

}
