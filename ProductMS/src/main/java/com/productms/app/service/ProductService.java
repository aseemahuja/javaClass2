package com.productms.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.productms.app.exception.ServiceException;
import com.productms.app.model.Product;

@Service
public class ProductService {
	
	@Autowired
	StubDataFeederService stubDataFeederService;
	
	public List<Product> filterByZip(int zipcode){
		List<Product> allProductList = stubDataFeederService.getProductList();
		List<Product> resultList =  allProductList
				.stream()
				.filter(p -> zipcode==p.getZipCode())
				.collect(Collectors.toList());
		
		if(null==resultList || CollectionUtils.isEmpty(resultList)) {
			throw new ServiceException("NO_PRODUCTS_AVAILABLE");
		}
		
		return resultList;
	}

}
