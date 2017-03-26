package org.javacream.util.aspects;

public class ProfilingAspect extends BaseAspect {

	private long start;

	@Override
	public void before(String methodName, Object[] args) {
		start = System.currentTimeMillis();
	}

	@Override
	public void after_returning(String methodName, Object[] args, Object result) {
		System.out.println("Return from " + methodName + " took " + (System.currentTimeMillis() - start));
	}

	@Override
	public void after_throwing(String methodName, Object[] args, Throwable t) {
		System.out.println("Throw from " + methodName + " took " + (System.currentTimeMillis() - start));
	}
}
