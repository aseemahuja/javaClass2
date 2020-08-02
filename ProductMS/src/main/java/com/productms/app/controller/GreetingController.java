package com.productms.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
	
	
	@RequestMapping(value = "/greeting", method = RequestMethod.GET)
	public ResponseEntity<String> greeting() {
		
		return new ResponseEntity<>("Hi, How are you?", HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/bye", method = RequestMethod.GET)
	public ResponseEntity<String> bye() {
		
		return new ResponseEntity<>("Sure, Bye for now", HttpStatus.OK);
		
	}
	
	/*
	 * @RequestMapping(value = "/all/{zipCode}", method = RequestMethod.GET) public
	 * ResponseEntity<AllProuctsResponse>
	 * allProductsbyZip(@PathVariable(value="zipCode") int zipCode) {
	 * 
	 * }
	 */

}
