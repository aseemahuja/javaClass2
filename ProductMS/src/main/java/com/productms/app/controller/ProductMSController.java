package com.productms.app.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.productms.app.model.AddProductRequest;
import com.productms.app.model.AllProductsResponse;
import com.productms.app.model.CommonResponse;
import com.productms.app.model.DeleteMultipleRequest;
import com.productms.app.model.FindByPriceRangeRequest;
import com.productms.app.model.Product;
import com.productms.app.requestValidator.ProductMSRequestValidator;
import com.productms.app.service.ProductService;

@RestController
@RequestMapping(value = "/product")
public class ProductMSController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	ProductMSRequestValidator validator;
	
	private static final Logger logger = LoggerFactory.getLogger(ProductMSController.class);
	
	@Value("${greeting.message}")
	String greetingMessage;

	@RequestMapping(value = "/greeting", method = RequestMethod.GET)
	public ResponseEntity<String> greeting() {

		return new ResponseEntity<>(greetingMessage, HttpStatus.OK);

	}

	@RequestMapping(value = "/bye", method = RequestMethod.GET)
	public ResponseEntity<String> bye() {

		return new ResponseEntity<>("Sure, Bye for now", HttpStatus.OK);

	}

	 @RequestMapping(value = "/all/{zipCode}", method = RequestMethod.GET)
	 public ResponseEntity<AllProductsResponse>
	 allProductsbyZip(@PathVariable(value="zipCode") int zipCode) { 
		 
		validator.validateAllByZip(zipCode);
		 
		List<Product> productList = productService.filterByZip(zipCode);
		
		AllProductsResponse response = new AllProductsResponse();
		response.setZipCode(zipCode);
		response.setStatus("SUCCESS");
		response.setProductList(productList );
		response.setNumberOfProducts(productList.size());
		return new ResponseEntity<>(response, HttpStatus.OK);
	 }
	 
	//{{url}}/product/findByPriceRange
		 @RequestMapping(value="/findByPriceRange", method = RequestMethod.POST)
		 public ResponseEntity<CommonResponse> findByPriceRange(@RequestBody FindByPriceRangeRequest request){
			 CommonResponse response = new CommonResponse();
			 //Validate the request
			 
			 if(null==request || (request.getMaxValue() == 0.0 && request.getMinValue() ==0.0)) {
				 response.setErrorCode("E101");
				 response.setErrorDescription("Request is not valid. Please provide correct price range");
				 response.setStatus("Failure");
				 return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			 } else {
				 response.setStatus("SUCCESS");
				 return new ResponseEntity<>(response, HttpStatus.OK);
			 }
			 
		 }
	 
	 
	 
	 //{{url}}/product/add
	 @RequestMapping(value="/add", method = RequestMethod.POST)
	 public ResponseEntity<CommonResponse> addProduct(@RequestBody AddProductRequest request){
		 CommonResponse response = new CommonResponse();
		 //Validate the request
		 validator.validateAdd(request);
		 
		 response.setStatus("SUCCESS");
		 return new ResponseEntity<>(response, HttpStatus.OK);
		 
	 }
	 
	 // {{url}}/product/update
	 @RequestMapping(value="/update", method = RequestMethod.PUT)
	 public ResponseEntity<CommonResponse> updateProduct(@RequestBody Product request){
		 CommonResponse response = new CommonResponse();
		 //Validate the request
		 
		 if(null==request || request.getProductId() ==0) {
			 response.setErrorCode("E102");
			 response.setErrorDescription("Request is not valid.");
			 response.setStatus("Failure");
			 return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		 } else if(request.getCost()==0.0 
				 && (null==request.getProductDescription() || ""== request.getProductDescription())
				 && (null==request.getProductName() || ""== request.getProductName())) {
			 response.setErrorCode("E103");
			 response.setErrorDescription("No new data to update.");
			 response.setStatus("Failure");
			 return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		 }
		 else {
			 response.setStatus("SUCCESS");
			 return new ResponseEntity<>(response, HttpStatus.OK);
		 }
		 
	 }
	 
	 // {{url}}/product/delete?productId=101
	 @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	 public ResponseEntity<CommonResponse>
	 delete(@RequestParam("productId") Integer productId) { 
		
		 CommonResponse response = new CommonResponse();
		 
		 if(null==productId || productId == 0) {
			 response.setErrorCode("E104");
			 response.setErrorDescription("Product id is invalid.");
			 response.setStatus("Failure");
			 return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		 } else {
			 response.setStatus("SUCCESS");
			 return new ResponseEntity<>(response, HttpStatus.OK);
		 }
		
		 
	 }
	 
	 //{{url}}/product/deleteMultiple
	 @RequestMapping(value = "/deleteMultiple", method = RequestMethod.DELETE)
	 public ResponseEntity<CommonResponse>
	 delete(@RequestBody DeleteMultipleRequest request) { 
		 
		 logger.info("Entered deleteMultiple with request: {}", request);
		
		 CommonResponse response = new CommonResponse();
		 
		 if(null==request || null== request.getProductIdList() || request.getProductIdList().isEmpty()) {
			 logger.info("Entered deleteMultiple request is invalid");
			 response.setErrorCode("E104");
			 response.setErrorDescription("Product id is invalid.");
			 response.setStatus("Failure");
			 return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		 } else {
			 logger.info("Existing deleteMultiple request validated so returning success");
			 response.setStatus("SUCCESS");
			 return new ResponseEntity<>(response, HttpStatus.OK);
		 }

	 }

}
