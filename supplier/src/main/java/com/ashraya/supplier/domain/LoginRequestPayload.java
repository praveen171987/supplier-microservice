package com.ashraya.supplier.domain;

import com.ashraya.supplier.constants.AppUsage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestPayload {

    private String loginType;
    private String firstName;
    private String lastName;
    private String displayName;
    private String emailId;
    private String phoneNumber;
    private String googlePictureUrl;
    private String facebookPictureUrl;
    private String gstNumber;
    private int categoryId;
    private boolean emailVerified;
    private AppUsage appUsage;
}
