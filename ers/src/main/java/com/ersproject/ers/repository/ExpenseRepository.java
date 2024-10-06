package com.ersproject.ers.repository;

import com.ersproject.ers.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense,String> {

}
