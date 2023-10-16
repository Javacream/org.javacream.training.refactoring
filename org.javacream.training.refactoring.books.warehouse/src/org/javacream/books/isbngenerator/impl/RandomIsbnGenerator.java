package org.javacream.books.isbngenerator.impl;

import org.javacream.books.isbngennerator.api.IsbnGenerator;

public class RandomIsbnGenerator implements IsbnGenerator{
	private String prefix;
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	@Override
	public String next() {
		return prefix + Math.random();
	}


} 