package com.contacts.manage.exception;

public record ErrorResponse(
        String message,
        String type
) {

}
