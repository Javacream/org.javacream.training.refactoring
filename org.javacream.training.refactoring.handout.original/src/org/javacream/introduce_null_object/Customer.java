package org.javacream.introduce_null_object;

public class Customer {

	private String name;
	private BillingPlan billingPlan;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BillingPlan getBillingPlan() {
		return billingPlan;
	}
	public void setBillingPlan(BillingPlan billingPlan) {
		this.billingPlan = billingPlan;
	}
	
	public Customer(String name){
		this.name = name;
		billingPlan = new BillingPlan("billingPlan for " + name);
	}
}
