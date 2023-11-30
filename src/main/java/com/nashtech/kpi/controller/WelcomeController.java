package com.nashtech.kpi.controller;

import com.nashtech.kpi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {
    @Autowired
    private UserService userService;

    @GetMapping("/welcome")
    public String welcome(OAuth2AuthenticationToken principal) {
        userService.saveGoogleAuthenticationUser(principal.getPrincipal());
        return "Welcome" ;
    }
}
