package com.productms.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.productms.app.model.Product;

@Service
public class StubDataFeederService {
	
	public List<Product> getProductList(){
		 List<Product> productList = new ArrayList<>();
		 Product product = new Product();
		 
		 for(int i=0; i<10; i++) {
			 product = new Product();
			 product.setCost(10.0 * (i+1));
			 product.setProductDescription("Product Description" + i);
			 product.setProductId(0001 +i);
			 product.setProductName("Product Name " +i);
			 product.setZipCode(100+i);
			 productList.add(product );
			 
		 }
		 
		 
		 return productList;
	}

}
