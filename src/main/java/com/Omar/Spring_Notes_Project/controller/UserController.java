package com.Omar.Spring_Notes_Project.controller;


import com.Omar.Spring_Notes_Project.dto.AuthResponse;
import com.Omar.Spring_Notes_Project.dto.LoginRequest;
import com.Omar.Spring_Notes_Project.exception.ErrorResponse;
import com.Omar.Spring_Notes_Project.exception.GlobalExceptionHandler;
import com.Omar.Spring_Notes_Project.exception.NotFoundException;
import com.Omar.Spring_Notes_Project.exception.UnauthorizedException;
import com.Omar.Spring_Notes_Project.model.User;
import com.Omar.Spring_Notes_Project.model.UserPrinciple;
import com.Omar.Spring_Notes_Project.service.JwtService;
import com.Omar.Spring_Notes_Project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<?> register (@RequestBody User user) {

        try {

            if(user.getFirstName() == null || user.getPassword() == null || user.getFirstName().isBlank() || user.getLastName().isBlank()) {
                throw new NotFoundException("Incorrect username or Password");
            }

            String token = jwtService.generateToken(user);

            userService.saveUser(user);

            return ResponseEntity.ok(new AuthResponse(token));
        }

        catch (BadCredentialsException ex) {
            throw new UnauthorizedException(ex.getMessage());
        }

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        try {

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.email(),
                            request.password()
                    )
            );


            User user = userService.getUserByEmail(request.email());
            String token = jwtService.generateToken(user);

            return ResponseEntity.ok(new AuthResponse(token));

        } catch (BadCredentialsException ex) {
            throw new UnauthorizedException(ex.getMessage());
        }
    }

}
