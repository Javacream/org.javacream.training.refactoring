package org.javacream.util.aspects;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

import org.javacream.util.aspects.TracingAspect;
import org.junit.Test;

public class AspectTests {

	
	@SuppressWarnings("unchecked")
	@Test public void testList(){
		ClassLoader classLoader = this.getClass().getClassLoader();
		Class<?>[] toImplement = {List.class};

		List<String> names = new ArrayList<String>();
		TracingAspect tracingAspect = new TracingAspect();
		tracingAspect.setDelegate(names);
		names = (List<String>) Proxy.newProxyInstance(classLoader, toImplement, tracingAspect);
		names.add("Hugo");
		names.add("Emil");
		System.out.println(names.size());
	}
}
