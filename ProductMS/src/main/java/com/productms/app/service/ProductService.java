package com.productms.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.productms.app.model.Product;

@Service
public class ProductService {
	
	public List<Product> filterByZip(int zipcode, List<Product> allProductList){
		List<Product> resultList =  allProductList
				.stream()
				.filter(p -> zipcode==p.getZipCode())
				.collect(Collectors.toList());
		
		return resultList;
	}

}
