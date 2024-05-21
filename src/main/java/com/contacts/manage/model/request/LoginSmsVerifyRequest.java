package com.contacts.manage.model.request;

public record LoginSmsVerifyRequest(
        String code,
        String user_id
) {

}
