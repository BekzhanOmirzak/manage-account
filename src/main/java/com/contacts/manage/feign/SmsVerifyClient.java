package com.contacts.manage.feign;

import com.contacts.manage.model.request.AuthSMSRequest;
import com.contacts.manage.model.request.LoginSmsVerify;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(
        value = "sms-verify-api",
        url = "https://localhost:1240/v1"
)
public interface SmsVerifyClient {

    @RequestMapping(method = RequestMethod.POST, value = "/fire_sms_code")
    void authWithSmsCode(AuthSMSRequest authSMSRequest);

    @RequestMapping(method = RequestMethod.POST, value = "/validate_sms_code")
    void verifySmsCode(LoginSmsVerify loginSmsVerify);

}
