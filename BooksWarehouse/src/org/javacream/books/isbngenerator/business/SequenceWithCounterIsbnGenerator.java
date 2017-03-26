package org.javacream.books.isbngenerator.business;

import org.javacream.books.isbngenerator.IsbnGenerator;

public class SequenceWithCounterIsbnGenerator implements IsbnGenerator {

	private int counter = 0;
	@Override
	public String createIsbn() {
		// TODO Auto-generated method stub
		return "ISBN: " + counter++;
	}

}
