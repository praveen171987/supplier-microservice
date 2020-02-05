package com.ashraya.supplier.serviceImpl;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashraya.supplier.constants.Constants;
import com.ashraya.supplier.constants.LoginType;
import com.ashraya.supplier.domain.CustomerMapper;
import com.ashraya.supplier.domain.CustomerResponse;
import com.ashraya.supplier.domain.LoginRequestPayload;
import com.ashraya.supplier.domain.OtpPayload;
import com.ashraya.supplier.domain.OtpResponse;
import com.ashraya.supplier.model.FacebookAccountInfo;
import com.ashraya.supplier.model.GoogleAccountInfo;
import com.ashraya.supplier.model.Otp;
import com.ashraya.supplier.model.WaterSupplier;
import com.ashraya.supplier.repository.CategoryRepository;
import com.ashraya.supplier.repository.FacebookAccountRepository;
import com.ashraya.supplier.repository.GoogleAccountRepository;
import com.ashraya.supplier.repository.OtpRepository;
import com.ashraya.supplier.repository.WaterSupplierRepository;
import com.ashraya.supplier.service.LoginService;
import com.ashraya.supplier.util.LoginValidationUtil;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Autowired
    private GoogleAccountRepository googleAccountRepository;

    @Autowired
    private FacebookAccountRepository facebookAccountRepository;

    @Autowired
    private OtpRepository otpRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private WaterSupplierRepository waterSupplierRepository;

    private String status;
    private WaterSupplier waterSupplier;

    public CustomerResponse loginOrRegister(LoginRequestPayload payload) {
        log.info(LoginServiceImpl.class + ":: Starting loginOrRegister");
        LoginType loginType = LoginValidationUtil.validateLogintype(payload.getLoginType());
        if (loginType.equals(LoginType.PHONE)) {
            loginWithMobile(payload);
        } else if (loginType.equals(LoginType.GOOGLE)) {
            loginWithGoogle(payload);
        } else if (loginType.equals(LoginType.FACEBOOK)) {
            loginWithFacebook(payload);
        }
        log.info(LoginServiceImpl.class + ":: End loginOrRegister");
        return createResponse(status, payload, waterSupplier);
    }

    private void loginWithMobile(LoginRequestPayload payload) {
        log.info(LoginServiceImpl.class + ":: Starting loginWithMobile");
        String phoneNumber = LoginValidationUtil.validatePhone(payload.getPhoneNumber());
        waterSupplier = waterSupplierRepository.findByMobileNumber(phoneNumber);
        if (waterSupplier != null) {
            status = Constants.LOGIN_STATUS;
        } else {
            LoginValidationUtil.validate(payload);
            createWaterSupplier(payload, null, null);
        }
        log.info(LoginServiceImpl.class + ":: End loginWithMobile");
    }

    private void loginWithFacebook(LoginRequestPayload payload) {
        log.info(LoginServiceImpl.class + ":: Starting loginWithFacebook");
        Integer id = facebookAccountRepository.findByEmailId(LoginValidationUtil.validateEmail(payload.getEmailId()));
        if (id != null) {
            waterSupplier = waterSupplierRepository.findByFacebookAccountInfoId(id);
            status = Constants.LOGIN_STATUS;
        } else {
            LoginValidationUtil.validate(payload);
            FacebookAccountInfo facebookAccountInfo = facebookAccountRepository.save(CustomerMapper.mapToFacebookAccountInfo(payload));
            createWaterSupplier(payload, facebookAccountInfo, null);
        }
        log.info(LoginServiceImpl.class + ":: End loginWithFacebook");
    }

    private void loginWithGoogle(LoginRequestPayload payload) {
        log.info(LoginServiceImpl.class + ":: Starting loginWithGoogle");
        Integer id = googleAccountRepository.findByEmail(LoginValidationUtil.validateEmail(payload.getEmailId()));
        if (id != null) {
            waterSupplier = waterSupplierRepository.findByGoogleAccountInfoId(id);
            status = Constants.LOGIN_STATUS;
        } else {
            LoginValidationUtil.validate(payload);
            GoogleAccountInfo googleAccountInfo = googleAccountRepository.save(CustomerMapper.mapToGoogleAccountInfo(payload));
            createWaterSupplier(payload, null, googleAccountInfo);
        }
        log.info(LoginServiceImpl.class + ":: End loginWithGoogle");
    }

    private void createWaterSupplier(LoginRequestPayload payload, FacebookAccountInfo facebookAccountInfo, GoogleAccountInfo accountInfo) {
        log.info(LoginServiceImpl.class + ":: Starting createWaterSupplier");
        waterSupplier = waterSupplierRepository.save(CustomerMapper.mapToWaterSupplier(payload, facebookAccountInfo, accountInfo,
                        categoryRepository.findOne(payload.getCategoryId())));
        status = Constants.REGISTER_STATUS;
        log.info(LoginServiceImpl.class + ":: End createWaterSupplier");
    }

    private CustomerResponse createResponse(String status, LoginRequestPayload payload, WaterSupplier waterSupplier) {
        log.info(LoginServiceImpl.class + ":: Starting createResponse");
        return CustomerResponse.builder().status(status).customerId(waterSupplier.getSupplierId()).userName(payload.getFirstName()).emailId(payload.getEmailId()).build();
    }

    @Override
    public OtpResponse verifyOtp(OtpPayload otpPayload) {
        log.info(LoginServiceImpl.class + ":: Starting verifyOtp");
        OtpResponse otpResponse = new OtpResponse();
        Otp otp = otpRepository.findByWaterSupplierSupplierIdAndOtpNumber(otpPayload.getUserId(), otpPayload.getOtpNumber());
        if (otp != null) {
            otpRepository.delete(otp);
            otpResponse.setStatus(Constants.OTP_VERIFY);
        } else {
            otpResponse.setStatus(Constants.OTP_NOT_VERIED);
        }
        log.info(LoginServiceImpl.class + ":: End verifyOtp");
        return otpResponse;
    }
}