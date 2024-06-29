package com.egbas.personal_expense_tracker.controller;

import com.egbas.personal_expense_tracker.payload.request.CategoryRequest;
import com.egbas.personal_expense_tracker.payload.request.ExpenseRequest;
import com.egbas.personal_expense_tracker.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/expenses/")
public class ExpenseController {

    private final ExpenseService expenseService;
    @PostMapping("{categoryId}/new")
    public ResponseEntity<ExpenseRequest> createExpense(@PathVariable Long categoryId, @RequestBody ExpenseRequest request){
        ExpenseRequest expenseRequest = expenseService.createExpense(categoryId, request);

        return new ResponseEntity<>(expenseRequest, HttpStatus.CREATED);
    }
    @GetMapping("getAllExpenses")
    public ResponseEntity<List<ExpenseRequest>> getAllExpense(){
        List<ExpenseRequest> expenseRequest = expenseService.getAllExpense();

        return ResponseEntity.ok(expenseRequest);
    }
    @GetMapping("{categoryId}/getExpenseByCategory")
    public ResponseEntity<List<ExpenseRequest>> getExpenseByCategory(@PathVariable Long categoryId){
        List<ExpenseRequest> expenseRequest = expenseService.getAllExpenseByCategory(categoryId);

        return ResponseEntity.ok(expenseRequest);
    }

    @GetMapping("{expenseId}/getExpense")
    public ResponseEntity<ExpenseRequest> getExpenseById(@PathVariable Long expenseId){
        ExpenseRequest expenseRequest = expenseService.getExpenseById(expenseId);

        return ResponseEntity.ok(expenseRequest);
    }

    @PutMapping("{expenseId}/updateExpense")
    public ResponseEntity<ExpenseRequest> updateExpense(@PathVariable Long expenseId, ExpenseRequest request){
        ExpenseRequest expenseRequest = expenseService.updateExpense(expenseId, request);

        return ResponseEntity.ok(expenseRequest);
    }
    @DeleteMapping("{expenseId}/deleteExpense")
    public ResponseEntity<String> deleteExpense(@PathVariable Long expenseId){
        String deleted = expenseService.deleteExpense(expenseId);

        return new ResponseEntity<>(deleted, HttpStatus.NO_CONTENT);
    }


}
