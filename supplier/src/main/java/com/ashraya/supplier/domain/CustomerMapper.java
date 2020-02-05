package com.ashraya.supplier.domain;

import com.ashraya.supplier.model.Category;
import com.ashraya.supplier.model.FacebookAccountInfo;
import com.ashraya.supplier.model.GoogleAccountInfo;
import com.ashraya.supplier.model.WaterSupplier;

public class CustomerMapper {

    public static GoogleAccountInfo mapToGoogleAccountInfo(LoginRequestPayload payload) {
        return GoogleAccountInfo.builder()
        		    .firstName(payload.getFirstName())
        		    .lastName(payload.getLastName())
                    .displayName(payload.getFirstName())
                    .emailId(payload.getEmailId())
                    .emailVerified(payload.isEmailVerified())
                    .mobileNumber(payload.getPhoneNumber())
                    .googlePictureUrl(payload.getGooglePictureUrl())
                    .build();
    }

    public static FacebookAccountInfo mapToFacebookAccountInfo(LoginRequestPayload payload) {
        return FacebookAccountInfo.builder()
        		.displayName(payload.getFirstName())
        		.firstName(payload.getFirstName())
        		.lastName(payload.getLastName())
                .emailId(payload.getEmailId())
                .emailVerified(payload.isEmailVerified())
                .facebookPictureUrl(payload.getFacebookPictureUrl())
                .mobileNumber(payload.getPhoneNumber())
                .build();
    }

    public static WaterSupplier mapToWaterSupplier(LoginRequestPayload payload, FacebookAccountInfo facebookAccountInfo, GoogleAccountInfo googleAccountInfo, Category category) {
        return WaterSupplier.builder()
        		.facebookAccountInfo(facebookAccountInfo)
        		.googleAccountInfo(googleAccountInfo)
        		.gstNum(payload.getGstNumber())
                .mobileNumber(payload.getPhoneNumber())
                .userName(payload.getDisplayName())
                .appUsage(payload.getAppUsage())
                .category(category)
                .loginAccount(payload.getLoginType())
                .build();
    }
}
