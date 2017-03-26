package org.javacream.util.aspects;

import java.lang.reflect.Method;

public interface AspectListener {

	public abstract void returning(Method method, Object[] args, Object result);

	public abstract void throwing(Method method, Object[] args, Throwable t);

	public abstract void before(Method method, Object[] args);

}
