package org.javacream.util.aspects.impl;

import java.lang.reflect.Method;

import org.javacream.util.aspects.AspectListener;

public class TracingAspectListener implements AspectListener{

	@Override
	public void before(Method method, Object[] args) {
		String methodName = method.getName();
		System.out.println("LISTENER: entering " + methodName);
	}

	@Override
	public void returning(Method method, Object[] args, Object result) {
		String methodName = method.getName();
		System.out.println("LISTENER: returning from " + methodName
				+ ", result = " + result);
	}

	@Override
	public void throwing(Method method, Object[] args, Throwable t) {
		String methodName = method.getName();
		System.out.println("LISTENER: throwing from " + methodName
				+ ", throwable = " + t);
	}
}
