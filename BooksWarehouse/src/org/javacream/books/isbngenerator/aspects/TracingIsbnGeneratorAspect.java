package org.javacream.books.isbngenerator.aspects;

import org.javacream.books.isbngenerator.IsbnGenerator;

public class TracingIsbnGeneratorAspect implements IsbnGenerator{
	private IsbnGenerator delegate;

	public String createIsbn() {
		System.out.println("Entering method createIsbn");
		try {
			String isbn = delegate.createIsbn();
			System.out.println("returning from createIsbn");
			return isbn;
		} catch (RuntimeException e) {
			System.out.println("throwing from createIsbn");
			throw e;
		}
	}

	public void setDelegate(IsbnGenerator delegate) {
		this.delegate = delegate;
	}
}
