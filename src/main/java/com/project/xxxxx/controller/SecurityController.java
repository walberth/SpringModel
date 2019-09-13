package com.project.xxxxx.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/test")
public class SecurityController {
    @GetMapping("/getTest")
    public String GetPersonList() throws Exception {
        throw new Exception();
    }
}
