package com.egbas.personal_expense_tracker.service.Impl;

import com.egbas.personal_expense_tracker.entities.Category;
import com.egbas.personal_expense_tracker.payload.request.CategoryRequest;
import com.egbas.personal_expense_tracker.repository.CategoryRepository;
import com.egbas.personal_expense_tracker.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryRequest createCategory(CategoryRequest request) {
        Category category = Category.builder()
                .name(request.getName())
                .build();
        categoryRepository.save(category);

        return request;
    }

    @Override
    public CategoryRequest getCategoryByid(Long id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with ID" + id));

        return CategoryRequest.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    @Override
    public List<CategoryRequest> getAllCategory() {

        List<Category> category = categoryRepository.findAll();

        return convertToList(category);
    }

    @Override
    public CategoryRequest updateCategory(Long id, CategoryRequest request) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with ID" + id));

        category.setName(request.getName());
        categoryRepository.save(category);

        return CategoryRequest.builder()
                .id(category.getId())
                .name(category.getName())
                .build();


    }

    @Override
    public String deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with ID" + id));

        categoryRepository.delete(category);

        return category.getName() + " Category deleted successfully";
    }

    private List<CategoryRequest> convertToList(List<Category> requests){
        List<CategoryRequest> requestList = new ArrayList<>();
        for (Category category : requests){
            CategoryRequest categories = CategoryRequest.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .build();

            requestList.add(categories);
        }

        return requestList;
    }
}
