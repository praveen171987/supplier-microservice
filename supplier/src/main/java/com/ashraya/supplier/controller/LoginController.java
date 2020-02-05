package com.ashraya.supplier.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ashraya.supplier.constants.Constants;
import com.ashraya.supplier.domain.CustomerResponse;
import com.ashraya.supplier.domain.LoginRequestPayload;
import com.ashraya.supplier.domain.OtpPayload;
import com.ashraya.supplier.domain.OtpResponse;
import com.ashraya.supplier.service.LoginService;

@RestController
@RequestMapping(Constants.SUPPLIER)
@Slf4j
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping(path = Constants.VERSION + Constants.LOGIN, consumes = Constants.APPLICATION_JSON, produces = Constants.APPLICATION_JSON)
    public CustomerResponse login(@RequestBody LoginRequestPayload payload) {
        log.info(LoginController.class + ":: Starting login --:" + payload);
        return loginService.loginOrRegister(payload);
    }

    @PostMapping(path = Constants.VERSION + Constants.VERIFY_OTP, consumes = Constants.APPLICATION_JSON, produces = Constants.APPLICATION_JSON)
    public OtpResponse verifyOtp(@RequestBody OtpPayload otpPayload) {
        log.info(LoginController.class + "Starting verifyOtp");
        return loginService.verifyOtp(otpPayload);
    }
}
