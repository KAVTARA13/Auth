package com.example.oauth2.controller;

import com.example.oauth2.entity.AuthRequest;
import com.example.oauth2.entity.UserInfo;
import com.example.oauth2.publisher.RabbitMQProducer;
import com.example.oauth2.service.JwtService;
import com.example.oauth2.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {
    private final UserInfoService service;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;
    private final RabbitMQProducer producer;

    @Autowired
    public UserController(UserInfoService service, JwtService jwtService, AuthenticationManager authenticationManager, RabbitMQProducer producer) {
        this.service = service;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.producer = producer;
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @PostMapping("/addNewUser")
    public String addNewUser(@RequestBody UserInfo userInfo) {
        producer.sendMessage(userInfo);
        return service.addUser(userInfo);
    }

    @GetMapping("/user/userProfile")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String userProfile() {
        return "Welcome to User Profile";
    }

    @GetMapping("/admin/adminProfile")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminProfile() {
        return "Welcome to Admin Profile";
    }

    @PostMapping("/generateToken")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            String token =  jwtService.generateToken(authRequest.getUsername());
            System.out.println(token);
            return token;
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }
}
