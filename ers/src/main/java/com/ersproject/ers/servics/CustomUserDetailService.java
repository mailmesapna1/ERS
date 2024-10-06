package com.ersproject.ers.servics;

import com.ersproject.ers.model.Role;
import com.ersproject.ers.model.User;
import com.ersproject.ers.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //load user from database

        User user = userRepository.findByUsername(username).orElseThrow(()-> new RuntimeException("USER NOT FOUND"));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),user.getPassword(),getAuthorities(user.getRoles())
        );
    }
    private Set<GrantedAuthority> getAuthorities(Set<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getAuthority()))
                .collect(Collectors.toSet());
    }
}
