package com.contacts.manage.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AuthRequest(
        @JsonProperty("phone_number")
        String phoneNumber
) {

}
