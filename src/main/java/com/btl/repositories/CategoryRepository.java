package com.btl.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.btl.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findByStatus(boolean status);
    List<Category> findByCategoryNameContainingIgnoreCase(String categoryName);
	List<Category> findByParentNull();
	
}
