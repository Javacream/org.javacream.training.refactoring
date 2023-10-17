package org.javacream.util.aspects;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class PerformanceAspect implements InvocationHandler {
	private Object delegate;
	public void setDelegate(Object delegate) {
		this.delegate = delegate;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		String methodName = method.getName();
		long start = System.currentTimeMillis();
		try {
			Object result = method.invoke(delegate, args);
			return result;
		}
		catch(Throwable t) {
			if (t instanceof InvocationTargetException) {
				t = ((InvocationTargetException)t).getTargetException();
			}
			throw t;
		}
		finally {
			System.out.println("calling " + methodName + " took " + (System.currentTimeMillis() - start) +"msec");
		}
	}
		
	@SuppressWarnings("unchecked")
	public static <T> T aspect(T obj){
		PerformanceAspect aspect = new PerformanceAspect();
		aspect.setDelegate(obj);
		ClassLoader classLoader = PerformanceAspect.class.getClassLoader();
		Class<?>[] interfaces = obj.getClass().getInterfaces();
		return (T)Proxy.newProxyInstance(classLoader, interfaces, aspect);
	}
}
