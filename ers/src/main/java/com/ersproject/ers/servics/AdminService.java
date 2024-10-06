package com.ersproject.ers.servics;

import com.ersproject.ers.model.Role;
import com.ersproject.ers.model.User;
import com.ersproject.ers.repository.RoleRepository;
import com.ersproject.ers.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdminService {

//    private List<User> store = new ArrayList<>();

//    public AdminService(){
//        store.add(new User(UUID.randomUUID().toString(),"8804506100","AmanPass",null));
//        store.add(new User(UUID.randomUUID().toString(),"1804506100","wmanPass",null));
//        store.add(new User(UUID.randomUUID().toString(),"2804506100","qmanPass",null));
//        store.add(new User(UUID.randomUUID().toString(),"3804506100","tmanPass",null));
//    }
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;
    public List<User> getUsers(){

        return userRepository.findAll();
    }

    public User createUser(User user){

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        for(Role role : user.getRoles()){
            Role existingRole = roleRepository.findByAuthority(role.getAuthority());
            if(existingRole != null){
                roles.add(existingRole);
            }
            else{
                roleRepository.save(role);
                roles.add(role);
            }
        }
        user.setRoles(roles);
        return userRepository.save(user);
    }
}
