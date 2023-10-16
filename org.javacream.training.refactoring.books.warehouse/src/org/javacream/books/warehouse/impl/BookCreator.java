package org.javacream.books.warehouse.impl;

import java.util.Map;

import org.javacream.books.warehouse.api.Book;

@FunctionalInterface
public interface BookCreator {

	Book create(String isbn, String title, Map<String, Object> options);
}
