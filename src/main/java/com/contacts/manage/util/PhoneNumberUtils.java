package com.contacts.manage.util;

import org.springframework.stereotype.Component;

@Component
public class PhoneNumberUtils {

    public String trimPhoneNumber(String phoneNumber) {
        var result = new StringBuilder();
        for (int i = 0; i < phoneNumber.length(); i++) {
            if (Character.isDigit(phoneNumber.charAt(i))) {
                result.append(phoneNumber.charAt(i));
            }
        }
        if (result.charAt(0) == '8') {
            result.setCharAt(0, '7');
        }
        result.insert(0, '+');
        return result.toString();
    }

}
