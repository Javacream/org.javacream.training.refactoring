package org.javacream.util.aspects;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class TracingAspect implements InvocationHandler {
	private Object delegate;
	public void setDelegate(Object delegate) {
		this.delegate = delegate;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		String methodName = method.getName();
		String arguments = args != null ? Arrays.asList(args).toString(): "none";
		System.out.println("entering " + methodName +", params=" + arguments);
		try {
			Object result = method.invoke(delegate, args);
			System.out.println("returning from " + methodName +", result=" + result);
			return result;
		}
		catch(Throwable t) {
			if (t instanceof InvocationTargetException) {
				t = ((InvocationTargetException)t).getTargetException();
			}
			System.out.println("throwing from " + methodName +", throwable=" + t);
			throw t;
		}
	}
		
	@SuppressWarnings("unchecked")
	public static <T> T aspect(T obj){
		TracingAspect auditAspect = new TracingAspect();
		auditAspect.setDelegate(obj);
		ClassLoader classLoader = TracingAspect.class.getClassLoader();
		Class<?>[] interfaces = obj.getClass().getInterfaces();
		return (T)Proxy.newProxyInstance(classLoader, interfaces, auditAspect);
	}
}
