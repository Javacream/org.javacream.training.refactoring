package org.javacream.store.business;

import org.javacream.store.StoreService;

public class StoreServiceImpl implements StoreService {

	/* (non-Javadoc)
	 * @see org.javacream.store.StoreService#getStock(java.lang.String, java.lang.String)
	 */
	@Override
	public int getStock(String category, String id) {
		//System.out.println("stock request at " + new Date());
		// retrieve stock from external service, e.g. web service call...
		return 42;
	}
}