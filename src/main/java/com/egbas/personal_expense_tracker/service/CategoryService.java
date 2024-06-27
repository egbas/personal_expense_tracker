package com.egbas.personal_expense_tracker.service;

import com.egbas.personal_expense_tracker.payload.request.CategoryRequest;

import java.util.List;

public interface CategoryService {

    CategoryRequest createCategory(CategoryRequest request);
    CategoryRequest getCategoryByid(Long id);
    List<CategoryRequest> getAllCategory();
    CategoryRequest updateCategory(Long id, CategoryRequest request);
    String deleteCategory(Long id);
}
