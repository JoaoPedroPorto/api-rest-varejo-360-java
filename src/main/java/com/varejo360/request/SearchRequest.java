package com.varejo360.request;

import java.io.Serializable;

public class SearchRequest implements Serializable {
	private static final long serialVersionUID = 8895852303496826582L;
	
	private String keyword;

	// GETTERS AND SETTERS
	
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

}
