package org.javacream.books.isbngenerator.decorators;

import org.javacream.books.isbngenerator.IsbnGenerator;

public class SimpleIsbnGeneratorDecorator implements IsbnGenerator{
	private IsbnGenerator isbnGenerator;

	public String createIsbn() {
		System.out.println("Decorating...");
		return isbnGenerator.createIsbn();
	}

	public void setIsbnGenerator(IsbnGenerator isbnGenerator) {
		this.isbnGenerator = isbnGenerator;
	}
}
