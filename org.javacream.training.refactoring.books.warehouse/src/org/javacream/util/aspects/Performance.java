package org.javacream.util.aspects;

public class Performance{
	public Long before(String methodName, Object[] args){
		return System.currentTimeMillis();
	}
	
	public void returning(String methodName, Object result, Long param) {
		measure(methodName, (long)param);
	}
	private void measure(String methodName, long param) {
		System.out.println("calling " + methodName + " took " + (System.currentTimeMillis() - param) + "msec");
		
	}

	public void throwing(String methodName, Throwable t, Long param) {
		measure(methodName, (long)param);
	}
}
