package com.egbas.personal_expense_tracker.payload.request;

import com.egbas.personal_expense_tracker.entities.Category;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExpenseRequest {

    private BigDecimal amount;
    private LocalDate expenseDate;
    private Category category;
}
