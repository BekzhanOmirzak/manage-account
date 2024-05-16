package com.contacts.manage.controller;

import com.contacts.manage.model.request.AuthRequest;
import com.contacts.manage.model.request.LoginSmsVerify;
import com.contacts.manage.model.response.AuthResponse;
import com.contacts.manage.model.response.VerifyResponse;
import com.contacts.manage.service.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final IAuthService authService;

    @PostMapping("/auth")
    public ResponseEntity<AuthResponse> authWithPhoneNumber(@RequestBody AuthRequest authRequest) {
        return authService.authRequest(authRequest);
    }

    @PostMapping("/auth_verify")
    public ResponseEntity<VerifyResponse> authWithSmsCode(@RequestBody LoginSmsVerify loginSmsVerify) {
        return authService.loginSmsVerification(loginSmsVerify);
    }

}
