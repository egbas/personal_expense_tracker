package com.egbas.personal_expense_tracker.controller;

import com.egbas.personal_expense_tracker.payload.request.CategoryRequest;
import com.egbas.personal_expense_tracker.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("new")
    public ResponseEntity<CategoryRequest> createCategory(@RequestBody CategoryRequest request) {
        CategoryRequest categoryRequest = categoryService.createCategory(request);
        return new ResponseEntity<>(categoryRequest, HttpStatus.CREATED);
    }
    @GetMapping("{id}/category")
    public ResponseEntity<CategoryRequest> getCategoryById(@PathVariable Long id) {
        CategoryRequest categoryRequest = categoryService.getCategoryByid(id);
        return ResponseEntity.ok(categoryRequest);
    }

    @GetMapping()
    public ResponseEntity<List<CategoryRequest>> getAllCategory() {
        List<CategoryRequest> categoryRequest = categoryService.getAllCategory();
        return ResponseEntity.ok(categoryRequest);
    }

    @PutMapping("{id}/update")
    public ResponseEntity<CategoryRequest> updateCategory(@PathVariable Long id, @RequestBody CategoryRequest request) {
        CategoryRequest categoryRequest = categoryService.updateCategory(id, request);
        return new ResponseEntity<>(categoryRequest, HttpStatus.OK);
    }

    @DeleteMapping("{id}/delete")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>("Deleted Successfully", HttpStatus.NO_CONTENT);
    }


}
