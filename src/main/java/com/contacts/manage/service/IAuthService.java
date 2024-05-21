package com.contacts.manage.service;


import com.contacts.manage.model.request.AuthRequest;
import com.contacts.manage.model.request.LoginSmsVerifyRequest;
import com.contacts.manage.model.response.AuthResponse;
import com.contacts.manage.model.response.RefreshTokenResponse;
import com.contacts.manage.model.response.VerifyResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface IAuthService {

    ResponseEntity<AuthResponse> authRequest(AuthRequest authRequest);

    ResponseEntity<VerifyResponse> loginSmsVerification(LoginSmsVerifyRequest loginSmsVerify);

    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;

}
