package com.ersproject.ers.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ReimbursementDTO {


    @NotNull(message = "Item name is required")
    private String itemName;

    @NotNull(message = "Amount is required")
    private Float amount;

    @NotNull(message = "Merchant name is required")
    private String merchantName;

    @NotNull(message = "Category is required")
    private String category;

    private String description;

    private String pdfUrl;

    @NotNull(message = "Expense date is required")
    private LocalDate expenseDate;


    @NotNull(message = "Date is required")
    private LocalDate date;
}
