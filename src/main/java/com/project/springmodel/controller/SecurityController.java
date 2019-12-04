package com.project.springmodel.controller;

import com.project.springmodel.model.JwtRequest;
import com.project.springmodel.model.JwtResponse;
import com.project.springmodel.model.Person;
import com.project.springmodel.model.User;
import com.project.springmodel.service.ISecurityService;
import com.project.springmodel.transversal.Constant;
import com.project.springmodel.transversal.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

@RestController
public class SecurityController {
    private ISecurityService securityService;

    @Autowired
    public SecurityController(ISecurityService securityService){
        this.securityService = securityService;
    }

    @PostMapping("/authenticate")
    public Response<JwtResponse> authenticate(@RequestBody JwtRequest request) {
        return this.securityService.authenticate(request);
    }

    @GetMapping("/refresh")
    public Response<JwtResponse> refresh(HttpServletRequest request) {
        double test = 1/0;
        return this.securityService.refresh(request.getHeader(Constant.Authorization).substring(7));
    }

    @PostMapping("/create")
    public Response<User> create(@RequestBody Person person,
                                 @RequestParam(value="username") String username,
                                 @RequestParam(value="password") String password) {
        return this.securityService.create(person, username, password);
    }
}
