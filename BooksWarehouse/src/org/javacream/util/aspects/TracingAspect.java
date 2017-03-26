package org.javacream.util.aspects;

public class TracingAspect extends BaseAspect{


	@Override
	public void before(String methodName, Object[] args){
		System.out.println("Entering method " + methodName);
		
	}
	@Override
	public void after_returning(String methodName, Object[] args, Object result){
		System.out.println("returning from " + methodName + ", result=" + result);
		
	}
	@Override
	public void after_throwing(String methodName, Object[] args, Throwable t){
		System.out.println("throwing from " + methodName + ", throwable=" + t);
		
	}
}
