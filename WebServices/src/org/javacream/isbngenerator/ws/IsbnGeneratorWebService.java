package org.javacream.isbngenerator.ws;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

@WebService(targetNamespace = "http://services.javacream.org/store")
public class IsbnGeneratorWebService {
	private static long counter = 0;
	
	@WebMethod(operationName="GenerateNextIsbn")
	public @WebResult(name = "GeneratedIsbn") String next() {
		//System.out.println("Generating isbn...");
		return "WS-ISBN:" + counter++ + "-de";
	}

	public static void main(String[] args) {
		Endpoint.publish("http://localhost:9901/IsbnGeneratorWebService", new IsbnGeneratorWebService());
	}

}
