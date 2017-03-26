package org.javacream.books.warehouse;

import java.util.Map;

@FunctionalInterface
public interface BookCreator {

	public Book create(Map<String, Object> options);
}
