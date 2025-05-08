package com.btl.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.btl.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	@Query("SELECT p FROM Product p WHERE productName LIKE %:name%")
	List<Product> findByNameContain(@Param("name") String name);
	List<Product> findByActive(boolean active);
}
