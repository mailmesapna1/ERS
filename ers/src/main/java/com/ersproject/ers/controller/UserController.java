package com.ersproject.ers.controller;


import com.ersproject.ers.dto.ReimbursementCountDTO;
import com.ersproject.ers.dto.ReimbursementDTO;
import com.ersproject.ers.model.Reimbursement;
import com.ersproject.ers.model.User;
import com.ersproject.ers.servics.CustomUserDetailService;
import com.ersproject.ers.servics.ReimbuursementService;
import com.ersproject.ers.servics.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")

public class UserController {

    @Autowired
    private ReimbuursementService reimbursementService;

    @Autowired
    private UserService userService;
    @GetMapping("/test")
    public String test(){
        return "IN USER API..";
    }

    @GetMapping("/reimbursement")
    public List<Reimbursement> getMyReimbursements(Authentication authentication) {
        String username = authentication.getName();
        Optional<User> optionalUser = userService.getUserByUserName(username);

        User employee = optionalUser.orElseThrow(() -> new RuntimeException("User not found"));

        return reimbursementService.getReimbursementsByEmployee(employee);
    }
    @PostMapping("/reimbursement")
    public ResponseEntity<Reimbursement> createReimbursement(@Valid @RequestBody ReimbursementDTO reimbursementDTO, Authentication authentication){
        // Validate the input if necessary
        String username = authentication.getName();
        User employee = userService.getUserByUserName(username).orElseThrow(() -> new RuntimeException("User not found"));
        // Save the reimbursement
        Reimbursement savedReimbursement = reimbursementService.saveReimbursement(reimbursementDTO, employee);

        // Return a response entity with the created reimbursement and HTTP status 201 (Created)
        return new ResponseEntity<>(savedReimbursement, HttpStatus.CREATED);
    }

    @GetMapping("/reimbursement-count")
    public ResponseEntity<ReimbursementCountDTO> getReimbursementCount(Authentication authentication){
        String username = authentication.getName();
        ReimbursementCountDTO countDTO = reimbursementService.getReimbursementCount(username);
        return ResponseEntity.ok(countDTO);
    }



//
//    @GetMapping("/reimbursement-amount")
}
//io.jsonwebtoken.security.SignatureException: JWT signature does not match locally computed signature. JWT validity cannot be asserted and should not be trusted.
//	at io.jsonwebtoken.impl.DefaultJwtParser.parse(DefaultJwtParser.java:399)