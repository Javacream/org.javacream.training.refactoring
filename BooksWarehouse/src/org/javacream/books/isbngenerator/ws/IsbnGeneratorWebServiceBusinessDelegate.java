package org.javacream.books.isbngenerator.ws;

import org.javacream.books.isbngenerator.IsbnGenerator;

public class IsbnGeneratorWebServiceBusinessDelegate implements IsbnGenerator{

	private IsbnGeneratorWebService delegate;
	public void setDelegate(IsbnGeneratorWebService delegate) {
		this.delegate = delegate;
	}
	@Override
	public String createIsbn() {
		return delegate.generateNextIsbn();
	}

}
