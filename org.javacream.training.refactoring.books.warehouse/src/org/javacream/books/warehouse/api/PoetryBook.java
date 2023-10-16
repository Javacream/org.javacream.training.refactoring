package org.javacream.books.warehouse.api;

public class PoetryBook extends Book{

	private static final long serialVersionUID = 1L;
	
	private String epoch;

	public String getEpoch() {
		return epoch;
	}

	public void setEpoch(String epoch) {
		this.epoch = epoch;
	}
}
