package com.sochoeun.service.impl;

import com.sochoeun.entity.Category;
import com.sochoeun.exception.NotFoundException;
import com.sochoeun.repository.CategoryRepository;
import com.sochoeun.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    @Override
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategory(Integer id) {
        return categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Category",id));
    }

    @Override
    public Category updateCategory(Integer id, Category category) {
        Category update = getCategory(id);
        update.setNameEn(category.getNameEn());
        update.setNameKh(category.getNameKh());
        return categoryRepository.save(update);
    }

    @Override
    public void delete(Integer id) {
        categoryRepository.deleteById(id);
    }
}
