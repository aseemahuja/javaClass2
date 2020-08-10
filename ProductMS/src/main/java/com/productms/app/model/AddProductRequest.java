package com.productms.app.model;

import java.util.List;

public class AddProductRequest {
	
	List<Product> productList;

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

}
