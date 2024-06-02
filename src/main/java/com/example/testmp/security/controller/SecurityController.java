package com.example.testmp.security.controller;

import com.example.testmp.exception.ApiException;
import com.example.testmp.security.model.JwtResponse;
import com.example.testmp.security.model.UserAuthData;
import com.example.testmp.user.data.UserEntity;
import com.example.testmp.security.service.UserService;
import com.example.testmp.security.util.JwtUtils;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SecurityController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserService userService;

    @SecurityRequirements(/*none*/)
    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody UserAuthData user) {
        Authentication authentication;

        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    user.getLogin(), user.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>("Incorrect credentials", HttpStatus.BAD_REQUEST);
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String jwt = jwtUtils.generateToken(userDetails);
        JwtResponse jwtResponse = JwtResponse.builder()
                                             .type("Bearer")
                                             .username(user.getLogin())
                                             .token(jwt)
                                             .build();

        return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
    }

    @SecurityRequirements(/*none*/)
    @PostMapping("/user/register")
    public ResponseEntity<?> registerUser(@RequestBody UserEntity user) {
        UserEntity existingUser = userService.getUserByLogin(user.getLogin());

        if (existingUser != null) {
            return ResponseEntity.badRequest().body("User already exists");
        }
        userService.createNewUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ExceptionHandler(ApiException.class)
    protected ResponseEntity<ApiException.ApiExceptionMessage> handleException(ApiException ex) {
        return ResponseEntity.status(ex.getStatus()).body(ex.getApiMessage());
    }


}
