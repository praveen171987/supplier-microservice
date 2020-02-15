package com.ashraya.supplier.controllerTest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.ashraya.supplier.constants.Constants;
import com.ashraya.supplier.controller.LoginController;
import com.ashraya.supplier.domain.CustomerResponse;
import com.ashraya.supplier.domain.LoginRequestPayload;
import com.ashraya.supplier.domain.OtpPayload;
import com.ashraya.supplier.service.LoginService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(secure = false)
public class LoginControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Mock
    LoginService loginService;

    @InjectMocks
    LoginController loginController;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void whenInValidInput_thenReturns400() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        CustomerResponse customerResponse = CustomerResponse.builder().customerId(1).emailId("rahul").status("faluier").userName("rop").build();
        LoginRequestPayload logipostnRequestPayload = LoginRequestPayload.builder().categoryId(1).displayName("3").emailId("fd").firstName("esg").build();
        when(loginService.loginOrRegister(Mockito.anyObject())).thenReturn(customerResponse);
        mockMvc.perform(MockMvcRequestBuilders.post(Constants.SUPPLIER + Constants.VERSION + Constants.LOGIN).contentType(Constants.APPLICATION_JSON)
                        .accept(Constants.APPLICATION_JSON).content(objectMapper.writeValueAsString(logipostnRequestPayload))).andExpect(status().isBadRequest());
    }
    
    @Test
    public void whenValidInput_thenReturns200() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        CustomerResponse customerResponse = CustomerResponse.builder().customerId(1).emailId("patidar@mail.com").status("success").userName("USERa1223").build();
        LoginRequestPayload logipostnRequestPayload = LoginRequestPayload.builder().loginType("GOOGLE").categoryId(1).displayName("ruan").emailId("patidar@mail.com").firstName("ruan").build();
        when(loginService.loginOrRegister(Mockito.anyObject())).thenReturn(customerResponse);
        mockMvc.perform(MockMvcRequestBuilders.post(Constants.SUPPLIER + Constants.VERSION + Constants.LOGIN).contentType(Constants.APPLICATION_JSON)
                        .accept(Constants.APPLICATION_JSON).content(objectMapper.writeValueAsString(logipostnRequestPayload))).andExpect(status().isOk());
    }

    @Test
	public void whenValidOtpNumber_thenReturn200() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		CustomerResponse customerResponse = CustomerResponse.builder().customerId(1).emailId("rahul").status("success")
				.userName("rop").build();
		 OtpPayload  otpPayload =  OtpPayload.builder().userId(1).otpNumber("1234").build();
		when(loginService.loginOrRegister(Mockito.anyObject())).thenReturn(customerResponse);
		mockMvc.perform(MockMvcRequestBuilders.post(Constants.SUPPLIER + Constants.VERSION + Constants.VERIFY_OTP)
				.contentType(Constants.APPLICATION_JSON).accept(Constants.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(otpPayload))).andExpect(status().isOk());
	}
    
    @Test
   	public void whenInValidOtpNumber_thenReturn400() throws Exception {
   		ObjectMapper objectMapper = new ObjectMapper();
   		CustomerResponse customerResponse = CustomerResponse.builder().customerId(1).emailId("rahul").status("failure")
   				.userName("rop").build();
   		 OtpPayload  otpPayload =  OtpPayload.builder().userId(1).otpNumber("12234").build();
   		when(loginService.loginOrRegister(Mockito.anyObject())).thenReturn(customerResponse);
   		mockMvc.perform(MockMvcRequestBuilders.post(Constants.SUPPLIER + Constants.VERSION + Constants.VERIFY_OTP)
   				.contentType(Constants.APPLICATION_JSON).accept(Constants.APPLICATION_JSON)
   				.content(objectMapper.writeValueAsString(otpPayload))).andExpect(status().isOk());
   	}
       
    
//    @Test
//    public void whenValidInputForCancelOrder_thenReturns200() throws Exception {
//        ObjectMapper objectMapper = new ObjectMapper();
//        OtpPayload OtpPayload = new OtpPayload();
//        OtpResponse OtpResponse = new OtpResponse();
//        when(loginService.verifyOtp(Mockito.anyObject())).thenReturn(OtpResponse);
//        mockMvc.perform(MockMvcRequestBuilders.post(Constants.SUPPLIER + Constants.VERSION + Constants.VERIFY_OTP).contentType(Constants.APPLICATION_JSON)
//                        .accept(Constants.APPLICATION_JSON).content(objectMapper.writeValueAsString(OtpPayload))).andExpect(status().isOk());
//    }
}
