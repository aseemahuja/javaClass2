package com.productms.app.requestValidator;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.productms.app.exception.ServiceException;
import com.productms.app.model.AddProductRequest;

@Service
public class ProductMSRequestValidator {
	
	public void validateAllByZip(int zipCode) {
		
		if(zipCode ==0) {
			 throw new ServiceException("ZIPCODE_NOT_VALID");
			/*
			 * response.setStatus("Failure"); response.setErrorCode("E001");
			 * response.setErrorDescription("ZipCode is invalid"); return new
			 * ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			 */
		 }
		
	}
	
	public void validateAdd(AddProductRequest request) {
		if(null==request || null==request.getProductList() || CollectionUtils.isEmpty(request.getProductList())) {
			 throw new ServiceException("ADD_REQUEST_INVALID");
		 }
		
	}

}
