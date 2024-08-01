package com.codewithakansha.blog.blogappapis.security;

import com.codewithakansha.blog.blogappapis.entities.User;
import com.codewithakansha.blog.blogappapis.exceptions.ResourceNotFoundException;
import com.codewithakansha.blog.blogappapis.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Loading user from database by username
        User user = this.userRepo.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("user", "email : " + username, 0));

        return user;
    }
}
