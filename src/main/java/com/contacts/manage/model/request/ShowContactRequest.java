package com.contacts.manage.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ShowContactRequest(
        String name,

        @JsonProperty("phone_number")
        String phoneNumber) {

}
