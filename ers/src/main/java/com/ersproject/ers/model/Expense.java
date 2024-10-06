package com.ersproject.ers.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;



    private String itemName;
    private Float amount;

    private String merchantName;

    private String Category;
    private String description;

    private String pdfUrl;

    private LocalDate expenseDate;
}
