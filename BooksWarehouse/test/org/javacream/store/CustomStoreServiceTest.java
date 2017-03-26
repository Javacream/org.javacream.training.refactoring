package org.javacream.store;

import org.junit.Test;
import com.javacream.*;

public class CustomStoreServiceTest {

	@Test public void testCustomStoreService(){

		Request request = new Request("this", "that");
		Response response = StoreRepository.read(request);
		System.out.println(response.getAvailableItems());
	}
}
