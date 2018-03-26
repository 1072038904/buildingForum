package com.util;

public class TestDemo {
	private String name;
	private String priClassification;
	private String secClassification;
	private String packMethod;
	private double price;
	public TestDemo() {
		// TODO Auto-generated constructor stub
	}

	public TestDemo(String pri) {
		// TODO Auto-generated constructor stub
		this.setPriClassification(pri);
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPriClassification() {
		return priClassification;
	}

	public void setPriClassification(String priClassification) {
		this.priClassification = priClassification;
	}

	public String getSecClassification() {
		return secClassification;
	}

	public void setSecClassification(String secClassification) {
		this.secClassification = secClassification;
	}

	public String getPackMethod() {
		return packMethod;
	}

	public void setPackMethod(String packMethod) {
		this.packMethod = packMethod;
	}

	public static void main(String args[]) {

	}

}
