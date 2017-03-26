package org.javacream.books.warehouse.notification.impl;

import org.javacream.books.warehouse.notification.BookNotification;
import org.javacream.books.warehouse.notification.BookNotificationListener;

public class SimpleBookNotificationListener implements BookNotificationListener {

	@Override
	public void handle(BookNotification bookNotification) {
		System.out.println("Notification: " + bookNotification + " registered by " + this );
	}

}
