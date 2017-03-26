package org.javacream.store.ws;

import java.util.Random;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

@WebService(targetNamespace = "http://services.javacream.org/store")
public class StoreWebService {

	private Random random = new Random(this.hashCode() + System.currentTimeMillis());

	@WebMethod(operationName="RetrieveStock")
	public @WebResult(name = "Stock") int stock(@WebParam(name = "Category") String category,
			@WebParam(name = "ItemId") String id) {
		return Math.abs(random.nextInt(1000));
	}

	public static void main(String[] args) {
		Endpoint.publish("http://localhost:9900/StoreWebService", new StoreWebService());
	}

}
