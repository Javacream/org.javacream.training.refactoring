package org.javacream.isbngenerator.ws;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

@WebService(targetNamespace = "http://services.javacream.org/isbngenerator")
public class FetchingIsbnGeneratorWebService {
	private static long counter = 0;
	
	@WebMethod(operationName="GenerateNextIsbn")
	public @WebResult(name = "GeneratedIsbns") List<String> next(int fetchSize) {
		System.out.println("Generating isbns...");
		ArrayList<String> result = new ArrayList<>();
		for (int i = 0; i < fetchSize; i++){
			result.add("WS-ISBN:" + counter++ + "-de");
		}
		return result;
	}

	public static void main(String[] args) {
		Endpoint.publish("http://localhost:9902/FetchingIsbnGeneratorWebService", new FetchingIsbnGeneratorWebService());
	}

}
