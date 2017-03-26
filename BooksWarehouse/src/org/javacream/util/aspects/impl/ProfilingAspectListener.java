package org.javacream.util.aspects.impl;

import java.lang.reflect.Method;

import org.javacream.util.aspects.AspectListener;

public class ProfilingAspectListener implements AspectListener{

	long start;

	@Override
	public void before(Method method, Object[] args) {
		start = System.currentTimeMillis();
	}

	@Override
	public void returning(Method method, Object[] args, Object result) {
		String methodName = method.getName();
		profile(methodName);
	}

	@Override
	public void throwing(Method method, Object[] args, Throwable t) {
		String methodName = method.getName();
		profile(methodName);
	}

	private void profile(String methodName){
		System.out.println("LISTENER: " + methodName + " took " + (System.currentTimeMillis() - start));
	}
}
