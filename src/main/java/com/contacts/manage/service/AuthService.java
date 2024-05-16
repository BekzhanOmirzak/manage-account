package com.contacts.manage.service;

import com.contacts.manage.feign.SmsVerifyClient;
import com.contacts.manage.model.request.AuthRequest;
import com.contacts.manage.model.request.AuthSMSRequest;
import com.contacts.manage.model.request.LoginSmsVerify;
import com.contacts.manage.model.response.AuthResponse;
import com.contacts.manage.model.response.VerifyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    private final SmsVerifyClient smsVerifyClient;

    @Override
    public ResponseEntity<AuthResponse> authRequest(AuthRequest authRequest) {
        var userID = UUID.randomUUID().toString();
        smsVerifyClient.authWithSmsCode(
                AuthSMSRequest.builder()
                        .user_id(userID)
                        .phone_number(authRequest.phoneNumber())
                        .build()
        );
        return ResponseEntity.ok(AuthResponse.builder().user_id(userID).build());
    }

    @Override
    public ResponseEntity<VerifyResponse> loginSmsVerification(LoginSmsVerify loginSmsVerify) {
        smsVerifyClient.verifySmsCode(loginSmsVerify);
        return null;
    }

}
