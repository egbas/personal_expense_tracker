package com.egbas.personal_expense_tracker.service;

import com.egbas.personal_expense_tracker.payload.request.ExpenseRequest;

import java.util.List;

public interface ExpenseService {

    ExpenseRequest createExpense(Long categoryId, ExpenseRequest request);

    List<ExpenseRequest> getAllExpense();
    ExpenseRequest getExpenseById(Long expenseId);
    List<ExpenseRequest> getAllExpenseByCategory(Long categoryId);
    ExpenseRequest updateExpense(Long expenseId, ExpenseRequest request);
    String deleteExpense(Long expenseId);
}
