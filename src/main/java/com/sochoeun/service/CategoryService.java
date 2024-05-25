package com.sochoeun.service;

import com.sochoeun.entity.Category;

import java.util.List;

public interface CategoryService {
    Category create(Category category);
    List<Category> getAllCategory();
    Category getCategory(Integer id);
    Category updateCategory(Integer id,Category category);
    void delete(Integer id);
}
