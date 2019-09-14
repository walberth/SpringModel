package com.project.xxxxx.controller;

import com.project.xxxxx.model.JwtRequest;
import com.project.xxxxx.model.JwtResponse;
import com.project.xxxxx.service.ISecurityService;
import com.project.xxxxx.transversal.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import java.util.Objects;

@RestController
public class SecurityController {
    private ISecurityService securityService;

    @Autowired
    public SecurityController(ISecurityService securityService){
        this.securityService = securityService;
    }

    @Value("${jwt.http.request.header}")
    private String tokenHeader;

    @PostMapping("/authenticate")
    public Response<JwtResponse> createAuthenticationToken(@RequestBody JwtRequest request) {
        return this.securityService.createAuthenticationToken(request);
    }

    @GetMapping("/getTest")
    public String GetPersonList() {
        return "Hola Mundo";
    }
}
