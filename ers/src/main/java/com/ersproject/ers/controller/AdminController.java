package com.ersproject.ers.controller;


import com.ersproject.ers.model.Reimbursement;
import com.ersproject.ers.model.User;
import com.ersproject.ers.servics.AdminService;
import com.ersproject.ers.servics.ReimbuursementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")

public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private ReimbuursementService reimbuursementService;


    @GetMapping("/users")
    public List<User> getUser(){
        return adminService.getUsers();
    }
    @GetMapping("/current-user")
    public String getLoggedUser(Principal principal){
        return principal.getName();
    }

    @GetMapping("/all-reimbursement")
    public List<Reimbursement> getAllReimbursement(){
        return reimbuursementService.getAllReimbursement();
    }

    @PutMapping("/approve-reimbursement/{id}")
    public ResponseEntity<?> approveReimbursement(@PathVariable String id, @RequestBody String password){
        String result = reimbuursementService.approvereimbursement(id,password);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/deny-reimbursement/{id}")
    public ResponseEntity<?> denyReimbursement(@PathVariable String id, @RequestBody String password){
        String result = reimbuursementService.denyreimbursement(id,password);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    //COUNT//
    @GetMapping("/pending-reimbursement-count")
    public ResponseEntity<?> pendingReimbursementCount(){
        int count = reimbuursementService.pendingReimbursementCount();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
    @GetMapping("/approved-reimbursement-count")
    public ResponseEntity<?> approvedReimbursementCount(){
        int count = reimbuursementService.approvedReimbursementCount();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
    @GetMapping("/denied-reimbursement-count")
    public ResponseEntity<?> deniedReimbursementCount(){
        int count = reimbuursementService.deniedReimbursementCount();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    //AMOUNT//
    @GetMapping("/awaiting-reimbursement-amount")
    public ResponseEntity<?> pendingReimbursementsAmount(){
        Float totalAmount = reimbuursementService.pendingReimbursementsAmount();
        return new ResponseEntity<>(totalAmount,HttpStatus.OK);
    }
    @GetMapping("/approved-reimbursement-amount")
    public ResponseEntity<?> approvedReimbursementsAmount(){
        Float totalAmount = reimbuursementService.approvedReimbursementsAmount();
        return new ResponseEntity<>(totalAmount,HttpStatus.OK);
    }
    @GetMapping("/denied-reimbursement-amount")
    public ResponseEntity<?> deniedReimbursementsAmount(){
        Float totalAmount = reimbuursementService.deniedReimbursementsAmount();
        return new ResponseEntity<>(totalAmount,HttpStatus.OK);
    }
    /////




}
