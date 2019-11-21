package org.javacream.books.warehouse;

public class RandomIsbnGenerator{
	private String prefix;
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	String nextIsbn() {
		return prefix + Math.random();
	}

	
}