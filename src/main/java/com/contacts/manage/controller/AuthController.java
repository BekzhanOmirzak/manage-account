package com.contacts.manage.controller;

import com.contacts.manage.model.request.AuthRequest;
import com.contacts.manage.model.request.LoginSmsVerifyRequest;
import com.contacts.manage.model.response.AuthResponse;
import com.contacts.manage.model.response.VerifyResponse;
import com.contacts.manage.service.IAuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final IAuthService authService;

    @PostMapping("/auth")
    public ResponseEntity<AuthResponse> authWithPhoneNumber(
            @RequestBody AuthRequest authRequest) {
        return authService.authRequest(authRequest);
    }

    @PostMapping("/auth-verify")
    public ResponseEntity<VerifyResponse> authWithSmsCode(
            @RequestBody LoginSmsVerifyRequest loginSmsVerify) {
        return authService.loginSmsVerification(loginSmsVerify);
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        authService.refreshToken(request, response);
    }

}
