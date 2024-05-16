package com.contacts.manage.service;


import com.contacts.manage.model.request.AuthRequest;
import com.contacts.manage.model.request.LoginSmsVerify;
import com.contacts.manage.model.response.AuthResponse;
import com.contacts.manage.model.response.VerifyResponse;
import org.springframework.http.ResponseEntity;

public interface IAuthService {

    ResponseEntity<AuthResponse> authRequest(AuthRequest authRequest);

    ResponseEntity<VerifyResponse> loginSmsVerification(LoginSmsVerify loginSmsVerify);

}
