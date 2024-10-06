package com.ersproject.ers.repository;

import com.ersproject.ers.model.Reimbursement;
import com.ersproject.ers.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReimbursementRepository extends JpaRepository<Reimbursement,String> {

    List<Reimbursement> findByEmployeeId(String employeeId);


    @Query("SELECT COUNT(r) FROM Reimbursement r WHERE r.status = :status")
    int getStatusCount(@Param("status") String status);

    @Query("SELECT SUM(r.expense.amount) FROM Reimbursement r WHERE r.status = 'PENDING'")
    Float getPendingReimbursementsAmount();

    @Query("SELECT SUM(r.expense.amount) FROM Reimbursement r WHERE r.status = 'APPROVED'")
    Float getApprovedReimbursementsAmount();
    @Query("SELECT SUM(r.expense.amount) FROM Reimbursement r WHERE r.status = 'DENIED'")
    Float getDeniedReimbursementsAmount();

    @Query("SELECT COUNT(r) FROM Reimbursement r WHERE r.status = :status AND r.employee.id = :employeeId")
    int countByStatusAndEmployeeId(@Param("status") String status, @Param("employeeId") String employeeId);

}
