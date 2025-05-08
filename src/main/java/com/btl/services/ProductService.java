package com.btl.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btl.entities.Product;
import com.btl.repositories.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository repo;
	
	public List<Product> getAll(){
		return repo.findAll();
	}
	public void save(Product product) {
		repo.save(product);
	}
	public Product getById(long id) {
		return repo.findById(id).get();
	}
	public void delete(long id) {
		repo.deleteById(id);
	}
	public List<Product> searchName(String name){
		return repo.findByNameContain(name);
	}
	public List<Product> getByActive(boolean active){
		return repo.findByActive(active);
	}
	
}