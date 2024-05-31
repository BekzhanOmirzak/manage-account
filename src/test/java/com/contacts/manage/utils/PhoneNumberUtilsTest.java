package com.contacts.manage.utils;


import com.contacts.manage.util.PhoneNumberUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class PhoneNumberUtilsTest {

    @Autowired
    private PhoneNumberUtils phoneNumberUtils;

    @Test
    void rightVersionOfNumberIsReturned() {
        var phoneNumber = "+7 776 365 56 65";

        var result = phoneNumberUtils.trimPhoneNumber(phoneNumber);

        assertEquals(result, "77763655665");
    }

    @Test
    void phoneNumberStartingPlus8() {
        var phoneNumber = "8 776 365 56 65";

        var result = phoneNumberUtils.trimPhoneNumber(phoneNumber);

        assertEquals(result, "77763655665");
    }

    @Test
    void phoneNumberFormattedBrackets() {
        var phoneNumber = "+7 (776) 365 56 65";

        var result = phoneNumberUtils.trimPhoneNumber(phoneNumber);

        assertEquals(result, "77763655665");
    }

}
