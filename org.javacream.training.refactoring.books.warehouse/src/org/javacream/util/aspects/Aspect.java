package org.javacream.util.aspects;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.function.BiConsumer;

public final class Aspect implements InvocationHandler {
	private Object delegate;
	public void setBefore(BiConsumer<String, Object[]> before) {
		this.before = before;
	}
	public void setReturning(BiConsumer<String, Object> returning) {
		this.returning = returning;
	}
	public void setThrowing(BiConsumer<String, Throwable> throwing) {
		this.throwing = throwing;
	}
	private BiConsumer<String, Object[]> before;
	private BiConsumer<String, Object> returning;
	private BiConsumer<String, Throwable> throwing;
	public void setDelegate(Object delegate) {
		this.delegate = delegate;
	}
	@Override
	public final Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		String methodName = method.getName();
		before.accept(methodName, args);
		try {
			Object result = method.invoke(delegate, args);
			returning.accept(methodName, result);
			return result;
		}
		catch(Throwable t) {
			if (t instanceof InvocationTargetException) {
				t = ((InvocationTargetException)t).getTargetException();
			}
			throwing.accept(methodName, t);
			throw t;
		}
	}
	@SuppressWarnings("unchecked")
	public static <T> T aspect(T obj, BiConsumer<String, Object[]> before, BiConsumer<String, Object> returning, BiConsumer<String, Throwable> throwing){
		Aspect aspect = new Aspect();
		aspect.setDelegate(obj);
		aspect.setBefore(before);
		aspect.setReturning(returning);
		aspect.setThrowing(throwing);
		ClassLoader classLoader = TracingAspect.class.getClassLoader();
		Class<?>[] interfaces = obj.getClass().getInterfaces();
		return (T)Proxy.newProxyInstance(classLoader, interfaces, aspect);
	}

}
