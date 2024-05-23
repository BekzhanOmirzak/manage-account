package com.contacts.manage.util;

import org.springframework.stereotype.Component;

import javax.xml.stream.events.Characters;
import java.util.ArrayList;
import java.util.List;

@Component
public class PhoneNumberUtils {

    //+7 (776) 365 56 65
    public String trimPhoneNumber(String phoneNumber) {
        var builder = new StringBuilder();
        for (int i = 0; i < phoneNumber.length(); i++) {
            var curChar = phoneNumber.charAt(i);
            if (Character.isDigit(curChar)) {
                builder.append(curChar);
            }
        }
        return builder.toString();
    }

}
