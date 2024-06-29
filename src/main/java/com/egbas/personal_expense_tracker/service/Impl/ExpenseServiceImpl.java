package com.egbas.personal_expense_tracker.service.Impl;

import com.egbas.personal_expense_tracker.entities.Category;
import com.egbas.personal_expense_tracker.entities.Expense;
import com.egbas.personal_expense_tracker.payload.request.ExpenseRequest;
import com.egbas.personal_expense_tracker.repository.CategoryRepository;
import com.egbas.personal_expense_tracker.repository.ExpensesRepository;
import com.egbas.personal_expense_tracker.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpensesRepository expensesRepository;
    private final CategoryRepository categoryRepository;
    @Override
    public ExpenseRequest createExpense(Long categoryId, ExpenseRequest request) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("No category selected"));

        Expense expense = Expense.builder()
                .amount(request.getAmount())
                .expenseDate(LocalDate.now())
                .category(category)
                .build();

        expensesRepository.save(expense);

        // Incase you decide to use beanUtils.properties
//        Expense newExpense = expensesRepository.save(expense);
//        ExpenseRequest savedRequest = new ExpenseRequest();
//        BeanUtils.copyProperties(newExpense, savedRequest);
//        return savedRequest;

        return ExpenseRequest.builder()
                .amount(expense.getAmount())
                .expenseDate(expense.getExpenseDate())
                .category(expense.getCategory())
                .build();
    }

    @Override
    public List<ExpenseRequest> getAllExpense() {
        List<Expense> expenseList = expensesRepository.findAll();
        List<ExpenseRequest> expenseRequestList = convertToList(expenseList);
        return expenseRequestList;
    }

    @Override
    public ExpenseRequest getExpenseById(Long expenseId) {
        Expense expense = expensesRepository.findById(expenseId)
                .orElseThrow(() -> new RuntimeException("Expense not found with Id " + expenseId));

        return ExpenseRequest.builder()
                .amount(expense.getAmount())
                .expenseDate(expense.getExpenseDate())
                .build();
    }

    @Override
    public List<ExpenseRequest> getAllExpenseByCategory(Long categoryId) {
        List<Expense> expense = expensesRepository.findByCategoryId(categoryId);

        List<ExpenseRequest> expenseRequestList = convertToList(expense);
        return expenseRequestList;
    }

    @Override
    public ExpenseRequest updateExpense(Long expenseId, ExpenseRequest request) {
        Expense expense = expensesRepository.findById(expenseId)
                .orElseThrow(() -> new RuntimeException("Expense not found with ID " + expenseId));
        expense.setAmount(request.getAmount());

        expensesRepository.save(expense);

        return ExpenseRequest.builder()
                .amount(expense.getAmount())
                .expenseDate(expense.getExpenseDate())
                .build();
    }

    @Override
    public String deleteExpense(Long expenseId) {

        Expense expense = expensesRepository.findById(expenseId)
                .orElseThrow(() -> new RuntimeException("Expense not found with ID " + expenseId));
        expensesRepository.delete(expense);

        return "Expense deleted successfully";
    }

    private List<ExpenseRequest> convertToList(List<Expense> expenseList){
        List<ExpenseRequest> expenseRequestList = new ArrayList<>();

        for (Expense expense : expenseList){
            ExpenseRequest expenseRequest = ExpenseRequest.builder()
                    .amount(expense.getAmount())
                    .expenseDate(expense.getExpenseDate())
                    .build();

            expenseRequestList.add(expenseRequest);
        }

        return expenseRequestList;
    }
}
