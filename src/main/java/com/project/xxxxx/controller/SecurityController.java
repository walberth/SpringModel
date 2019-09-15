package com.project.xxxxx.controller;

import com.project.xxxxx.model.JwtRequest;
import com.project.xxxxx.model.JwtResponse;
import com.project.xxxxx.model.Person;
import com.project.xxxxx.model.User;
import com.project.xxxxx.service.ISecurityService;
import com.project.xxxxx.transversal.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

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
    public Response<JwtResponse> authenticate(@RequestBody JwtRequest request) {
        return this.securityService.authenticate(request);
    }

    @GetMapping("/refresh")
    public Response<JwtResponse> refresh(HttpServletRequest request) {
        return this.securityService.refresh(request.getHeader(tokenHeader).substring(7));
    }

    @PostMapping("/create")
    public Response<User> create(@RequestBody Person person, @RequestParam(value="username") String username) {
        return this.securityService.create(person, username);
    }
}
