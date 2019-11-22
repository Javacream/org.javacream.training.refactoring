package org.javacream.books.warehouse.decorators;

import java.util.Map;

import org.javaceream.training.refactoring.notification.Action;
import org.javaceream.training.refactoring.notification.NotificationBus;
import org.javacream.books.warehouse.api.BookException;

public class NotifyingBooksService  extends BaseBooksService{

	private NotificationBus notificationBus;

	public void setNotificationBus(NotificationBus notificationBus) {
		this.notificationBus = notificationBus;
	}

	@Override
	public void deleteBookByIsbn(String isbn) throws BookException {
		super.deleteBookByIsbn(isbn);
		Map<String, Object> payload = Map.of("isbn", isbn);
		notificationBus.publish(new Action("book.deleted", payload));
	}
	
}
