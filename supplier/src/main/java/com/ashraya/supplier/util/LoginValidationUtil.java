package com.ashraya.supplier.util;

import java.util.Optional;

import com.ashraya.supplier.constants.Constants;
import com.ashraya.supplier.constants.LoginType;
import com.ashraya.supplier.domain.LoginRequestPayload;
import com.ashraya.supplier.exception.ValidationException;

public class LoginValidationUtil {

    public static void validate(LoginRequestPayload loginRequestPayload) {
        if (loginRequestPayload.getLoginType() == LoginType.PHONE) {
            validatePhone(loginRequestPayload.getPhoneNumber());
        }
        if (loginRequestPayload.getGstNumber() == null) {
            throw new ValidationException(Constants.GST_NOT_FOUND);
        }
    }

    public static LoginType validateLogintype(LoginType loginType) {
        return Optional.ofNullable(loginType).orElseThrow(() -> new ValidationException(Constants.LOGIN_TYPE_NOT_FOUND));
    }

    public static String validateEmail(String emailId) {
        return Optional.ofNullable(emailId).orElseThrow(() -> new ValidationException(Constants.EMAIL_NOT_FOUND));
    }

    public static String validatePhone(String phonenumber) {
        return Optional.ofNullable(phonenumber).orElseThrow(() -> new ValidationException(Constants.PHONE_NUMBER_NOT_FOUND));
    }
}
