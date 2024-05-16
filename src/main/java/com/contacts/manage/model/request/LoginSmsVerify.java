package com.contacts.manage.model.request;

public record LoginSmsVerify(
        String code,
        String user_id
) {

}
