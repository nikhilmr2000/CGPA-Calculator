package com.cgpacalculator.CGPACalculator.Entity;

public class Converter {
	
	
	private double value;
	
	public Converter(){
		super();
	}
	
	public Converter(double value) {
		this.value=value;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Converter [Value=" + value + "]";
	}
	
	
	
}
