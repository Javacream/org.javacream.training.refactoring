package org.javacream.books.isbngenerator;

public interface IsbnGenerator {

	String createIsbn();
	
	public enum Strategy{
		SEQUENCE, RANDOM;
	}

}