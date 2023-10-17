package org.javacream.util.aspects;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class BaseAspect implements InvocationHandler {
	private Object delegate;
	public void setDelegate(Object delegate) {
		this.delegate = delegate;
	}
	@Override
	public final Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		String methodName = method.getName();
		before(methodName, args);
		try {
			Object result = method.invoke(delegate, args);
			returning(methodName, result);
			return result;
		}
		catch(Throwable t) {
			if (t instanceof InvocationTargetException) {
				t = ((InvocationTargetException)t).getTargetException();
			}
			throwing(methodName, t);
			throw t;
		}
	}

	protected abstract void before(String methodName, Object[] args) throws Throwable;
	
	protected abstract void returning(String methodName, Object result);
	protected abstract void throwing(String methodName, Throwable t);

}
