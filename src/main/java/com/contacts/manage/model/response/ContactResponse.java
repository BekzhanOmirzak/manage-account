package com.contacts.manage.model.response;


public record ContactResponse(
        String name,
        String phone_number,
        Boolean isHidden
) {

}
