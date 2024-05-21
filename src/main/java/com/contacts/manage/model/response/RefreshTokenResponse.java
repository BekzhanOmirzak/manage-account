package com.contacts.manage.model.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

public record RefreshTokenResponse(
        @JsonProperty("access_token")
        String accessToken,
        @JsonProperty("refresh_token")
        String refreshToken
) {
}
