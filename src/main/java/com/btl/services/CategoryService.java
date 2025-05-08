package com.btl.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btl.entities.Category;
import com.btl.repositories.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public List<Category> getAll() {
        return repository.findByParentNull(); // Nếu có hàm này
    }

    public List<Category> getByStatus(boolean status) {
        return repository.findByStatus(status);
    }

    public List<Category> searchByName(String searchname) {
        return repository.findByCategoryNameContainingIgnoreCase(searchname);
    }

    public List<Category> findAll() {
        return repository.findAll();
    }

    public Category getById(int id) {
        return repository.findById(id).orElse(null);
    }

    public void save(Category category) {
        repository.save(category);
    }

    public void delete(int id) {
        Category category = repository.findById(id).orElse(null);
        if (category != null && category.getProducts().isEmpty() && category.getSubcategories().isEmpty()) {
            repository.delete(category);
        }
    }
}
