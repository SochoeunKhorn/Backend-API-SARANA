package com.sochoeun.controller;

import com.sochoeun.entity.Category;
import com.sochoeun.exception.response.BaseResponse;
import com.sochoeun.service.CategoryService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
@Tag(name = "Categories")
// Name on OpenApi UI
public class CategoryController {

    private final CategoryService categoryService;
    private BaseResponse baseResponse;
    @GetMapping()
    public ResponseEntity<?> getCategories(){
        List<Category> allCategory = categoryService.getAllCategory();
        baseResponse = new BaseResponse();
        baseResponse.createSuccess(allCategory);
        return ResponseEntity.ok(baseResponse);
    }
    @Hidden
    @GetMapping("/{id}")
    public ResponseEntity<?> getCategory(@PathVariable("id") Integer id){
        Category category = categoryService.getCategory(id);
        baseResponse = new BaseResponse();
        baseResponse.getSuccess(category);
        return ResponseEntity.ok(baseResponse);
    }
    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody Category category){
        Category newCategory = categoryService.create(category);
        baseResponse = new BaseResponse();
        baseResponse.createSuccess(newCategory);
        return ResponseEntity.ok(baseResponse);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(
            @PathVariable Integer id,
            @RequestBody Category category){
        Category updated = categoryService.updateCategory(id,category);
        baseResponse = new BaseResponse();
        baseResponse.updatedSuccess(updated);
        return ResponseEntity.ok(baseResponse);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") Integer id){
        categoryService.delete(id);
        baseResponse = new BaseResponse();
        baseResponse.deletedSuccess();
        return ResponseEntity.ok(baseResponse);
    }
}
