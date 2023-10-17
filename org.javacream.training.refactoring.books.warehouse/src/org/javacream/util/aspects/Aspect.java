package org.javacream.util.aspects;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public final class Aspect implements InvocationHandler {
	private Object delegate;
	public void setBefore(Before before) {
		this.before = before;
	}
	public void setReturning(Returning returning) {
		this.returning = returning;
	}
	public void setThrowing(Throwing throwing) {
		this.throwing = throwing;
	}
	private Before before;
	private Returning returning;
	private Throwing throwing;
	public void setDelegate(Object delegate) {
		this.delegate = delegate;
	}
	@Override
	public final Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		String methodName = method.getName();
		Object param = before.call(methodName, args);
		try {
			Object result = method.invoke(delegate, args);
			returning.accept(methodName, result, param);
			return result;
		}
		catch(Throwable t) {
			if (t instanceof InvocationTargetException) {
				t = ((InvocationTargetException)t).getTargetException();
			}
			throwing.accept(methodName, t, param);
			throw t;
		}
	}
	@SuppressWarnings("unchecked")
	public static <T> T aspect(T obj, Before<Object> before, Returning<Object> returning, Throwing<Object> throwing){
		Aspect aspect = new Aspect();
		aspect.setDelegate(obj);
		aspect.setBefore(before);
		aspect.setReturning(returning);
		aspect.setThrowing(throwing);
		ClassLoader classLoader = Aspect.class.getClassLoader();
		Class<?>[] interfaces = obj.getClass().getInterfaces();
		return (T)Proxy.newProxyInstance(classLoader, interfaces, aspect);
	}

	public interface Before<T>{
		T call(String methodName, Object[] args);
	}
	public interface Returning<T>{
		void accept(String methodName, Object result, T param);
	}
	public interface Throwing<T>{
		void accept(String methodName, Throwable t, T param);
	}
}

