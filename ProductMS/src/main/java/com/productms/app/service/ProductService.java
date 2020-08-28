package com.productms.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.productms.app.database.ProductMSRepository;
import com.productms.app.database.ProductTb;
import com.productms.app.exception.ServiceException;
import com.productms.app.model.Product;

@Service
public class ProductService {
	
	@Autowired
	StubDataFeederService stubDataFeederService;
	
	@Autowired
	ProductMSRepository repoService;
	
	public List<Product> filterByZip(int zipcode){
		
		Iterable<ProductTb> allProductIterable = repoService.findAll();
		List<ProductTb> zipRelatedproducts = repoService.findAllByZipCode();
		Product product = null;
		List<Product> resultList = new ArrayList<>();
		for(ProductTb product_tb : allProductIterable) {
			if(product_tb.getZipCode()== zipcode) {
				product = new Product();
				product.setCost(product_tb.getCost());
				product.setProductDescription(product_tb.getProductDescription());
				product.setProductId(product_tb.getProductId());
				product.setProductName(product_tb.getProductName());
				product.setZipCode(product_tb.getZipCode());
				product.setImagePath(product_tb.getImagePath());
				resultList.add(product);
			}
		}
		/*
		 * List<Product> allProductList = stubDataFeederService.getProductList();
		 * List<Product> resultList = allProductList .stream() .filter(p ->
		 * zipcode==p.getZipCode()) .collect(Collectors.toList());
		 */
		
		if(null==resultList || CollectionUtils.isEmpty(resultList)) {
			throw new ServiceException("NO_PRODUCTS_AVAILABLE");
		}
		
		return resultList;
	}
	
	public void addProduct(List<Product> list) {
		
		List<ProductTb> productTbList = new ArrayList<>();
		
		ProductTb product_tb = new ProductTb();
		for(Product product : list) {
			product_tb.setCost(product.getCost());
			product_tb.setImagePath(product.getImagePath());
			product_tb.setProductDescription(product.getProductDescription());
			product_tb.setProductId(product.getProductId());
			product_tb.setProductName(product.getProductName());
			product_tb.setZipCode(product.getZipCode());
			productTbList.add(product_tb);
		}
		
		Iterable<ProductTb> iterable = productTbList;
		
		
		
		Iterable<ProductTb> result = repoService.saveAll(iterable);
		
		if(null==result) {
			throw new ServiceException("NOT_ABLE_TO_ADD_PRODUCT");
		}
		
	}

}
