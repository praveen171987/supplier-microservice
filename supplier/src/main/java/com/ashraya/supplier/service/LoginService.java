package com.ashraya.supplier.service;

import com.ashraya.supplier.domain.CustomerResponse;
import com.ashraya.supplier.domain.LoginRequestPayload;
import com.ashraya.supplier.domain.OtpPayload;
import com.ashraya.supplier.domain.OtpResponse;

public interface LoginService {

    public CustomerResponse loginOrRegister(LoginRequestPayload payload);

    public OtpResponse verifyOtp(OtpPayload otpPayload);

}
