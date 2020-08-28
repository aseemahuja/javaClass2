package com.productms.app.database;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProductMSRepository extends CrudRepository<ProductTb, Integer>{
	
	@Query(value="SELECT * FROM product_tb WHERE zip_code=12345", nativeQuery= true)
	List<ProductTb> findAllByZipCode();

}
