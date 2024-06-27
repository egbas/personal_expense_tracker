package com.egbas.personal_expense_tracker.payload.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CategoryRequest {
    private Long id;
    private String name;
}
