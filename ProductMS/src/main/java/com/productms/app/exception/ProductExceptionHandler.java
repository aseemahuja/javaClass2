package com.productms.app.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.productms.app.model.CommonResponse;

@ControllerAdvice
@Configuration
@PropertySource("classpath:errormessages.properties")
public class ProductExceptionHandler {
	
	@Autowired
	private Environment env;
	
	private static final Logger logger = LoggerFactory.getLogger(ProductExceptionHandler.class);
	
	@ExceptionHandler(value=NullPointerException.class)
	public ResponseEntity<CommonResponse> catchNullpointer(NullPointerException nullpointerException) {
		CommonResponse response = new CommonResponse();
		response.setStatus("FAILURE");
		response.setErrorCode("10000");
		response.setErrorDescription("Nullpointer Exception came.");
		
		logger.error("Nullpointer Exception happened: {}", nullpointerException);
		
		return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value=ServiceException.class)
	public ResponseEntity<CommonResponse> catchServiceException(ServiceException serviceException) {
		String errorString = env.getProperty(serviceException.getCode());
		String[] errorArray = errorString.split("\\|");
		CommonResponse response = new CommonResponse();
		response.setStatus("FAILURE");
		response.setErrorCode(errorArray[1]);
		response.setErrorDescription(errorArray[2]);
		
		logger.error("Nullpointer Exception happened: {}", serviceException);
		
		return new ResponseEntity<>(response,HttpStatus.valueOf(Integer.parseInt(errorArray[0])));
	}

}
