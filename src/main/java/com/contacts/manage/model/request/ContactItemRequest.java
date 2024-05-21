package com.contacts.manage.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ContactItemRequest(
        @JsonProperty("phone_number")
        String phoneNumber,
        @JsonProperty("name")
        String name
) {

}
