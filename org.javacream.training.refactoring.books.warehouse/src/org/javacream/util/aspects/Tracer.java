package org.javacream.util.aspects;

import java.util.Arrays;

public class Tracer {
	public Object before(String methodName, Object[] args){
		String arguments = args != null ? Arrays.asList(args).toString(): "none";
		System.out.println("entering " + methodName +", params=" + arguments);
		return null;
	}
	
	public void returning(String methodName, Object result, Object param) {
		System.out.println("returning from " + methodName +", result=" + result);
	}
	public void throwing(String methodName, Throwable t, Object param) {
		System.out.println("throwing from " + methodName +", throwable=" + t);
	}

}
