package com.example.oauth2.controller;

import com.example.oauth2.service.UserInfoDetails;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@RestController
public class HomeController {

    @GetMapping("/")
    public String hole(){
        return "Hello, Home!";
    }

    @GetMapping("/secured")
    public String secured(
//            @AuthenticationPrincipal OAuth2User principal
    ){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof OAuth2AuthenticationToken oauth2Token) {
            // The user is authenticated using OAuth2
            System.out.println(("OAUTH2 : " + oauth2Token.getPrincipal().getAttributes()));
        } else if (authentication instanceof UsernamePasswordAuthenticationToken) {
            // The user is authenticated using JWT
            System.out.println("UserInfoDetails JWT : "+authentication.getPrincipal().toString());
            // Access JWT specific details if needed
        } else {
            System.out.println(authentication.getClass().getName());
        }

//        if (authentication != null && authentication.isAuthenticated()) {
//            System.out.println(authentication.getPrincipal().toString());
//        }
//        System.out.println(Objects.requireNonNull(principal.getAttribute("name")).toString());
//        System.out.println(Objects.requireNonNull(principal.getAttributes()).toString());
        return "Hello, Secured!";
    }
}
