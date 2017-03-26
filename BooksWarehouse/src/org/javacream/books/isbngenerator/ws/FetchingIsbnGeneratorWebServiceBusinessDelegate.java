package org.javacream.books.isbngenerator.ws;

import java.util.LinkedList;

import org.javacream.books.isbngenerator.IsbnGenerator;
import org.javacream.books.isbngenerator.ws.fetching.FetchingIsbnGeneratorWebService;

public class FetchingIsbnGeneratorWebServiceBusinessDelegate implements IsbnGenerator {

	private FetchingIsbnGeneratorWebService delegate;
	private LinkedList<String> isbns = new LinkedList<>();

	public void setDelegate(FetchingIsbnGeneratorWebService delegate) {
		this.delegate = delegate;
	}

	@Override
	public String createIsbn() {
		if (isbns.size() == 0) {
			isbns.addAll(delegate.generateNextIsbn(1000));
		}
		return isbns.removeFirst();
	}

}
