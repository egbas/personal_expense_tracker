package com.egbas.personal_expense_tracker.repository;

import com.egbas.personal_expense_tracker.entities.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpensesRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByCategoryId(Long id);
}
