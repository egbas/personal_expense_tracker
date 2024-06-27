package com.egbas.personal_expense_tracker.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "categories_tbl")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
