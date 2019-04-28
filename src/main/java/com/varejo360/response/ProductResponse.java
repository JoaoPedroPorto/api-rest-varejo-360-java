package com.varejo360.response;

import java.io.Serializable;

public class ProductResponse implements Serializable {
	private static final long serialVersionUID = -7209835153575761403L;
	
	private String barCode;
	private String productName;
	private String description;
	private Double probability;
	
	// GETTERS AND SETTERS
	
	public String getBarCode() {
		return barCode;
	}
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getProbability() {
		return probability;
	}
	public void setProbability(Double probability) {
		this.probability = probability;
	}
	
}
