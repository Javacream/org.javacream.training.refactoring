package org.javacream.books.warehouse;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BooksCreator {

	private Map<Set<String>, BookCreator> creators = new HashMap<Set<String>, BookCreator>();


	public void addBookCreator(Set<String> parameters, BookCreator creator) {
		creators.put(parameters, creator);
	}

	public void removeBookCreator(Set<String> parameters, BookCreator creator) {
		creators.remove(parameters, creator);
	}

	public Book create(Map<String, Object> options) {
		return creators.get(options.keySet()).create(options);
	}
}
