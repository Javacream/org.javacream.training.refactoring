package org.javacream.books.warehouse;

public class RandomIsbnGenerator{
	private String prefix = "ISBN-";
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public String next() {
		return prefix + Math.random();
	}


} 