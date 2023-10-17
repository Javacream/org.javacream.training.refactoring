package org.javacream.util.aspects;

import java.lang.reflect.Proxy;
import java.util.Arrays;

public class TracingAspect extends BaseAspect{
	@Override
	protected void before(String methodName, Object[] args) throws Throwable{
		String arguments = args != null ? Arrays.asList(args).toString(): "none";
		System.out.println("entering " + methodName +", params=" + arguments);
	}
	
	protected void returning(String methodName, Object result) {
		System.out.println("returning from " + methodName +", result=" + result);
	}
	protected void throwing(String methodName, Throwable t) {
		System.out.println("throwing from " + methodName +", throwable=" + t);
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
