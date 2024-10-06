package com.ersproject.ers.servics;

import com.ersproject.ers.model.User;
import com.ersproject.ers.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService  {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUserByUserName(String username){
      return userRepository.findByUsername(username);
    }


}
