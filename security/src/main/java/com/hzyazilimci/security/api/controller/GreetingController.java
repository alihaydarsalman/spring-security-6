package com.hzyazilimci.security.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hzyazilimci
 */

@RestController
@RequestMapping("/api/v1/greeting")
public class GreetingController {

    @GetMapping("/sayHello")
    public ResponseEntity<String> greet(){
        return ResponseEntity.ok("Hello, welcome to secured endpoint.");
    }
}
