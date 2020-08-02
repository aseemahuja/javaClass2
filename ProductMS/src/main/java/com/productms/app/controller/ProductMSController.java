package com.productms.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.productms.app.model.AllProductsResponse;
import com.productms.app.model.Product;

@RestController
@RequestMapping(value = "/product")
public class ProductMSController {

	@RequestMapping(value = "/greeting", method = RequestMethod.GET)
	public ResponseEntity<String> greeting() {

		return new ResponseEntity<>("Hi, How are you?", HttpStatus.OK);

	}

	@RequestMapping(value = "/bye", method = RequestMethod.GET)
	public ResponseEntity<String> bye() {

		return new ResponseEntity<>("Sure, Bye for now", HttpStatus.OK);

	}

	 @RequestMapping(value = "/all/{zipCode}", method = RequestMethod.GET)
	 public ResponseEntity<AllProductsResponse>
	 allProductsbyZip(@PathVariable(value="zipCode") int zipCode) { 
		 AllProductsResponse response = new AllProductsResponse();
		 if(zipCode ==0) {
			 response.setStatus("Failure");
			 response.setErrorCode("E001");
			 response.setErrorDescription("ZipCode is invalid");
			 return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		 }
		 
		 response.setZipCode(zipCode);
		 List<Product> productList = new ArrayList<>();
		 Product product = new Product();
		 product.setCost(10.0);
		 product.setProductDescription("Product Description 1");
		 product.setProductId(0001);
		 product.setProductName("Product Name 1");
		productList.add(product );
		if(!CollectionUtils.isEmpty(productList)) {
			response.setStatus("SUCCESS");
			response.setProductList(productList );
			response.setNumberOfProducts(productList.size());
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
		}
		
		
		 
	 }

}
