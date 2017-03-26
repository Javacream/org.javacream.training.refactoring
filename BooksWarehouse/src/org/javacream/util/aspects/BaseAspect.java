package org.javacream.util.aspects;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class BaseAspect implements InvocationHandler{

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
			after_returning(methodName, args, result);
			return result;
		} catch (Throwable t) {
			if (t instanceof InvocationTargetException){
				t = ((InvocationTargetException)t).getTargetException();
			}
			after_throwing(methodName, args, t);
			throw t;
		}
	}

	public abstract void before(String methodName, Object[] args);
	public abstract void after_returning(String methodName, Object[] args, Object result);
	public abstract void after_throwing(String methodName, Object[] args, Throwable t);
}
