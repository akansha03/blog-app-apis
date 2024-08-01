package com.codewithakansha.blog.blogappapis.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JWTTokenHelper jwtTokenHelper;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String username = null; String token = null;
        // Step 1: Get Token

        String requestToken = request.getHeader("Authorization");
        System.out.println(requestToken);

        //Step 2: Extract the token and username

        if(requestToken != null && requestToken.startsWith("Bearer")) {
            token = requestToken.substring(7);
            try {
                username = this.jwtTokenHelper.getUsernameFromToken(token);
            } catch(IllegalArgumentException e) {
                System.out.println("Unable to get JWT Token");
            }
            catch(ExpiredJwtException e){
                System.out.println("Expired JWT token");
            }
            catch(MalformedJwtException e){
                System.out.println("Malformed JWT token");
            }
        } else {
            System.out.println("JWT Token does not begin with Bearer");
        }
        // Step 3: Validate the username and the token

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails details = this.userDetailsService.loadUserByUsername(username);
            if(this.jwtTokenHelper.validateToken(token, details)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(details, null,
                        details.getAuthorities());

                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
            else {
                System.out.println("Invalid JWT Token");
            }
        }
        else {
             System.out.println("Username is null and context is not null");
        }

        filterChain.doFilter(request, response);
    }
}
