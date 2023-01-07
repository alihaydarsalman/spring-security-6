package com.hzyazilimci.security.api.controller;

import com.hzyazilimci.security.authDtos.AuthenticationDto;
import com.hzyazilimci.security.authDtos.AuthenticationResponse;
import com.hzyazilimci.security.authDtos.RegisterDto;
import com.hzyazilimci.security.service.abstracts.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hzyazilimci
 */

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterDto registerDto){
        return ResponseEntity.ok(userService.register(registerDto));
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationDto authenticationDto){
        return ResponseEntity.ok(userService.authenticate(authenticationDto));
    }
}
