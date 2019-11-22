package org.javaceream.training.refactoring.notification;

import java.util.Map;

@FunctionalInterface
public interface ActionHandler {

	public void handleAction(Map<String, Object> payload);
}
