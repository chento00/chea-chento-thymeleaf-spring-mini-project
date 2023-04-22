package com.example.homework_thymeleaf.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CategoryServiceImp implements CategoryService{
    private final CategoryRepository categoryRepository;
    @Override
    public List<Category> findAllCategory() {
        return categoryRepository.findAllCategory();
    }

    @Override
    public Category findById(String id) {
        return categoryRepository.findById(id);
    }

    @Override
    public void addCategory(Category category) {
        categoryRepository.addCategory(category);
    }
}
