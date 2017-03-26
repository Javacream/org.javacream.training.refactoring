package org.javacream.books.isbngenerator.business;

import org.javacream.books.isbngenerator.IsbnGenerator;
import org.javacream.util.IdGenerator;

/**
 * 
 * @author Administrator
 *
 *	Diese Klasse hat die Aufgabe, eine ISBN zu erzeugen
 */

public class RandomIsbnGenerator implements IsbnGenerator{

	public static final String ISBN_PREFIX = "Isbn:";

	private IdGenerator idGenerator;
	public void setIdGenerator(IdGenerator idGenerator) {
		this.idGenerator = idGenerator;
	}
	/* (non-Javadoc)
	 * @see org.javacream.books.isbngenerator.business.IsbnGenerator#createIsbn()
	 */
	@Override
	public String createIsbn() {
		String isbn =  ISBN_PREFIX + idGenerator.next();
		return isbn;
	}

}
