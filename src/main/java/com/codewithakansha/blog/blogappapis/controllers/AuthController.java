package com.codewithakansha.blog.blogappapis.controllers;

import com.codewithakansha.blog.blogappapis.entities.User;
import com.codewithakansha.blog.blogappapis.exceptions.APIException;
import com.codewithakansha.blog.blogappapis.payloads.JWTAuthResponse;
import com.codewithakansha.blog.blogappapis.payloads.JwtAuthRequest;
import com.codewithakansha.blog.blogappapis.payloads.UserDto;
import com.codewithakansha.blog.blogappapis.security.JWTTokenHelper;
import com.codewithakansha.blog.blogappapis.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth/")
@Tag(name = "Auth Token" , description = "This is Auth API")
public class AuthController {

    @Autowired
    private JWTTokenHelper jwtTokenHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<JWTAuthResponse> createToken(
        @RequestBody JwtAuthRequest request) {

        this.authenticate(request.getEmail(), request.getPassword());
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getEmail());
        String token = this.jwtTokenHelper.generateToken(userDetails);

        JWTAuthResponse response = new JWTAuthResponse();
        response.setToken(token);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    private void authenticate(String email, String password) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(email, password);
        try {
            this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        }
        catch (APIException e) {
             /* Use this for more customization
             throw new APIException(" Invalid Username or Password  !!"); */
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerNewUser(@RequestBody UserDto userDto) {
        UserDto registerUser = this.userService.registerNewUser(userDto);
        return new ResponseEntity<>(registerUser, HttpStatus.CREATED);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }
}
