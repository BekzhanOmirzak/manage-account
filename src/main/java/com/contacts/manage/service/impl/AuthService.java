package com.contacts.manage.service.impl;

import com.contacts.manage.model.response.RefreshTokenResponse;
import com.contacts.manage.security.JwtTokenUtill;
import com.contacts.manage.feign.SmsVerifyClient;
import com.contacts.manage.model.entity.User;
import com.contacts.manage.model.request.AuthRequest;
import com.contacts.manage.model.request.LoginSmsVerifyRequest;
import com.contacts.manage.model.response.AuthResponse;
import com.contacts.manage.model.response.VerifyResponse;
import com.contacts.manage.repository.UserRepository;
import com.contacts.manage.service.IAuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    private final SmsVerifyClient smsVerifyClient;
    private final UserRepository userRepository;
    private final JwtTokenUtill jwtTokenUtill;

    @Override
    public ResponseEntity<AuthResponse> authRequest(AuthRequest authRequest) {
        var trimPhoneNumber = authRequest.phoneNumber();
        var existUser = userRepository.findByPhonenumber(trimPhoneNumber);
        String userID;
        if (existUser.isEmpty()) {
            userID = UUID.randomUUID().toString();
            userRepository.save(User.builder().userid(userID).phonenumber(trimPhoneNumber).build());
        } else {
            userID = existUser.get().getUserid();
        }

        System.out.println("Making call to SmsVerifyClient : " + userID);
//        smsVerifyClient.authWithSmsCode(
//                AuthSMSRequest.builder()
//                        .phone_number(authRequest.phoneNumber())
//                        .user_id(userID)
//                        .build());

        return ResponseEntity.ok(AuthResponse.builder().user_id(userID).build());
    }

    @Override
    public ResponseEntity<VerifyResponse> loginSmsVerification(LoginSmsVerifyRequest loginSmsVerify) {
        var user = userRepository.findByUserid(loginSmsVerify.user_id()).get();
        var jwtToken = jwtTokenUtill.generateAccessToken(user);
        var refreshToken = jwtTokenUtill.generateRefreshToken(user);
//        smsVerifyClient.verifySmsCode(loginSmsVerify);
        return ResponseEntity.ok(VerifyResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build());
    }

    @Override
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userId;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userId = jwtTokenUtill.getUserId(refreshToken);
        if (userId != null) {
            var userDetails = userRepository.findByUserid(userId).orElseThrow(()
                    -> new RuntimeException("User not found by userid: " + userId));
            if (jwtTokenUtill.validateToken(refreshToken, userDetails)) {
                var accessToken = jwtTokenUtill.generateAccessToken(userDetails);
                new ObjectMapper().writeValue(
                        response.getOutputStream(),
                        new RefreshTokenResponse(accessToken, refreshToken));
            }
        }
    }

}
