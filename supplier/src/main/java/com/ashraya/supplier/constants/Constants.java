package com.ashraya.supplier.constants;

public class Constants {

    public static final String CUSTOMER = "customer";
    public static final String LOGIN_STATUS = "User LoggedIn";
    public static final String REGISTER_STATUS = "User Registered";
    public static final String GST_NOT_FOUND = "GST number can't be null";
    public static final String LOGIN_TYPE_NOT_FOUND = "LoginType can't be null";
    public static final String EMAIL_NOT_FOUND = "Email can't be null";
    public static final String PHONE_NUMBER_NOT_FOUND = "Phone number can't be null";
    public static final String NULL_MESSAGE = " can't be null";

    public static final String OTP_VERIFY = "SUCCESS";
    public static final String OTP_NOT_VERIED = "FAIL";
    public static final String VERSION = "/v1";

    public static final String ORDER_STATUS = "SUCCESS";
    public static final String ORDER_STATUS_FAIL = "FAILED";
    public static final String ORDER_CURRENCY = "Rupee";
    public static final String ORDER_MESSAGE_SUCCESS = "Order Scheduled";
    public static final String ORDER_MESSAGE_FAILURE = "No water supplier available near by please retry";
    public static final String CANCEL_ORDER_FAILED = "Order not found";
    public static final String CANCEL_ORDER_SUCCESS = "Order cancelled success";
    public static final String SUPPLIER = "/supplier";
    public static final String LOGIN = "/login";
    public static final String VERIFY_OTP = "/verifyOtp";

    public static final String APPLICATION_JSON = "application/json";

    public static final String UPDATE_ORDER = "/updateOrder/{bookingId}/{status}";

    public static final String BOOKINGID_INVALID = "bookingId is Invalid";

    public final static String APPLICATION_PROPERTIES_PATH = "F:/Personal-Data/ongoing-work/microservice-freelancer/workspace/supplier-application.properties";

}
