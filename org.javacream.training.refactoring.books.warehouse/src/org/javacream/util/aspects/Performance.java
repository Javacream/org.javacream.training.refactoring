package org.javacream.util.aspects;

public class Performance{
	public Object before(String methodName, Object[] args){
		return System.currentTimeMillis();
	}
	
	public void returning(String methodName, Object result, Object param) {
		measure(methodName, (long)param);
	}
	private void measure(String methodName, long param) {
		System.out.println("calling " + methodName + " took " + (System.currentTimeMillis() - param) + "msec");
		
	}

	public void throwing(String methodName, Throwable t, Object param) {
		measure(methodName, (long)param);
	}
}
