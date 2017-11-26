package com.rafsan.service;

import com.rafsan.model.Category;
import com.rafsan.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getCategories(){

        return categoryRepository.findAll();
    }
}
