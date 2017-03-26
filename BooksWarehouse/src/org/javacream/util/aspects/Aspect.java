package org.javacream.util.aspects;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.ClassUtils;

public final class Aspect implements InvocationHandler {

	
	Object delegate;

	public void setDelegate(Object delegate) {
		this.delegate = delegate;
	}
	
	private List<AspectListener> listeners = new LinkedList<AspectListener>();
	public void addAspectListener(AspectListener listener){
		listeners.add(listener);
	}
	public void removeAspectListener(AspectListener listener){
		listeners.remove(listener);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		for (AspectListener listener: listeners){
			listener.before(method, args);
		}
		try {
			Object result = method.invoke(delegate, args);
			for (AspectListener listener: listeners){
				listener.returning(method, args, result);
			}
			return result;
		} catch (Throwable t) {
			if (t instanceof InvocationTargetException) {
				t = ((InvocationTargetException) t).getTargetException();
			}
			for (AspectListener listener: listeners){
				listener.returning(method, args, t);
			}
			throw t;
		}
	}

	@SuppressWarnings( { "unchecked" })
	public static <T> T decorate(T delegate, AspectListener... aspectListeners ) {
		
		Aspect decorator = new Aspect();
		
		decorator.setDelegate(delegate);
		for(AspectListener aspectListener: aspectListeners){
			decorator.addAspectListener(aspectListener);
		}
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		List<?> classes = ClassUtils.getAllInterfaces(delegate.getClass());
		Class<?>[] interfaces = new Class<?>[classes.size()];
		classes.toArray(interfaces);
		Object proxy = Proxy.newProxyInstance(classLoader, interfaces,
				decorator);
		return (T) proxy;
	}
	
}
