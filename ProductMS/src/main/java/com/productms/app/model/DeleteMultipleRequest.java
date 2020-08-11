package com.productms.app.model;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class DeleteMultipleRequest {
	
	List<String> productIdList;

	public List<String> getProductIdList() {
		return productIdList;
	}

	public void setProductIdList(List<String> productIdList) {
		this.productIdList = productIdList;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	

}
