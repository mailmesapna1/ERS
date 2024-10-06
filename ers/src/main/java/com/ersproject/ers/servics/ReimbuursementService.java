package com.ersproject.ers.servics;

import com.ersproject.ers.dto.ReimbursementCountDTO;
import com.ersproject.ers.dto.ReimbursementDTO;
import com.ersproject.ers.model.Expense;
import com.ersproject.ers.model.Reimbursement;
import com.ersproject.ers.model.User;
import com.ersproject.ers.repository.ExpenseRepository;
import com.ersproject.ers.repository.ReimbursementRepository;
import com.ersproject.ers.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReimbuursementService {
    @Autowired
    private ReimbursementRepository reimbursementRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;
    public List<Reimbursement> getReimbursementsByEmployee(User employee) {
        return reimbursementRepository.findByEmployeeId(employee.getId());
    }
    public Reimbursement saveReimbursement(ReimbursementDTO reimbursementDTO, User employee) {
        Expense expense = new Expense();
        expense.setItemName(reimbursementDTO.getItemName());
        expense.setAmount(reimbursementDTO.getAmount());
        expense.setMerchantName(reimbursementDTO.getMerchantName());
        expense.setCategory(reimbursementDTO.getCategory());
        expense.setDescription(reimbursementDTO.getDescription());
        expense.setPdfUrl(reimbursementDTO.getPdfUrl());
        expense.setExpenseDate(reimbursementDTO.getExpenseDate());

        Expense savedExpense = expenseRepository.save(expense);

        Reimbursement reimbursement = new Reimbursement();
        reimbursement.setExpense(savedExpense);
        reimbursement.setEmployee(employee);
        reimbursement.setStatus("PENDING");
        reimbursement.setDate(LocalDate.now());

        return reimbursementRepository.save(reimbursement);
    }

    public List<Reimbursement> getAllReimbursement(){
        return reimbursementRepository.findAll();
    }

    public String approvereimbursement(String id, String password){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        Optional<User> optionalAdmin = userRepository.findByUsername(currentUsername);
        if(!optionalAdmin.isPresent()){
            return "ADMIN NOT FOUND";
        }
        User admin = optionalAdmin.get();

        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(currentUsername, password));
        }
        catch(Exception e){
            return "INVALID ADMIN CREDENTIALS";
        }
        //////////////////////////
        if (admin.getRoles().stream().noneMatch(role -> role.getAuthority().equals("ADMIN"))) {
            return "Unauthorized: Only admins can approve reimbursements";
        }
        //////////////////////////////////
        Optional<Reimbursement> optionalReimbursement = reimbursementRepository.findById(id);
        if (!optionalReimbursement.isPresent()) {
            return "Reimbursement not found";
        }

        Reimbursement reimbursement = optionalReimbursement.get();

        // Update the status of the reimbursement to approved
        reimbursement.setStatus("APPROVED");
        reimbursementRepository.save(reimbursement);

        return "Reimbursement approved successfully";

    }
    public String denyreimbursement(String id, String password){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        Optional<User> optionalAdmin = userRepository.findByUsername(currentUsername);
        if(!optionalAdmin.isPresent()){
            return "ADMIN NOT FOUND";
        }
        User admin = optionalAdmin.get();

        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(currentUsername, password));
        }
        catch(Exception e){
            return "INVALID ADMIN CREDENTIALS";
        }
        //////////////////////////
        if (admin.getRoles().stream().noneMatch(role -> role.getAuthority().equals("ADMIN"))) {
            return "Unauthorized: Only admins can approve reimbursements";
        }
        //////////////////////////////////
        Optional<Reimbursement> optionalReimbursement = reimbursementRepository.findById(id);
        if (!optionalReimbursement.isPresent()) {
            return "Reimbursement not found";
        }

        Reimbursement reimbursement = optionalReimbursement.get();

        // Update the status of the reimbursement to approved
        reimbursement.setStatus("DENIED");
        reimbursementRepository.save(reimbursement);

        return "Reimbursement denied successfully";

    }

    public int pendingReimbursementCount(){
        return reimbursementRepository.getStatusCount("PENDING");
    }
    public int approvedReimbursementCount(){
        return reimbursementRepository.getStatusCount("APPROVED");
    }
    public int deniedReimbursementCount(){
        return reimbursementRepository.getStatusCount("DENIED");
    }
    public Float pendingReimbursementsAmount(){
        return reimbursementRepository.getPendingReimbursementsAmount();
    }
    public Float approvedReimbursementsAmount(){
        return reimbursementRepository.getApprovedReimbursementsAmount();
    }
    public Float deniedReimbursementsAmount(){
        return reimbursementRepository.getDeniedReimbursementsAmount();
    }

    public ReimbursementCountDTO getReimbursementCount(String username){
        Optional<User> userOptional = userRepository.findByUsername(username);
        if(!userOptional.isPresent()){
            throw new RuntimeException("USER NOT FOUND");
        }
        User user = userOptional.get();
        String employeeId = user.getId();
        int total = reimbursementRepository.findByEmployeeId(employeeId).size();
        int approved = reimbursementRepository.countByStatusAndEmployeeId("APPROVED", employeeId);
        int pending = reimbursementRepository.countByStatusAndEmployeeId("PENDING", employeeId);
        int denied = reimbursementRepository.countByStatusAndEmployeeId("DENIED", employeeId);
        ReimbursementCountDTO countDTO = new ReimbursementCountDTO();
        countDTO.setTotal(total);
        countDTO.setApproved(approved);
        countDTO.setPending(pending);
        countDTO.setDenied(denied);

        return countDTO;
    }

}
