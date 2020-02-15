package com.ashraya.supplier.controllerTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.ashraya.supplier.constants.Constants;
import com.ashraya.supplier.controller.OrderController;
import com.ashraya.supplier.domain.GeoLocationPayload;
import com.ashraya.supplier.domain.UpdateOrderPayload;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(secure = false)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    OrderController orderController;

    @Test
    public void whenValidInputForPlaceOrder_thenReturns200() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        UpdateOrderPayload updateOrderPayload = UpdateOrderPayload.builder().bookingId(1).staus("inprogress").supplierId(3).dateTime(Mockito.anyString())
                        .geoLocation(GeoLocationPayload.builder().accuracy(12f).address("Indore").bearing(23f).latitude(12.21).longitude(223.211).distance(12d).userId(1).build())
                        .build();
        mockMvc.perform(MockMvcRequestBuilders.post(Constants.SUPPLIER + "/v1/updateOrder").contentType("application/json").accept("application/json")
                        .content(objectMapper.writeValueAsString(updateOrderPayload))).andExpect(status().isOk());
    }

    @Test
    public void whenValidInputForSaveGeolocation_thenReturns200() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(MockMvcRequestBuilders.post(Constants.SUPPLIER + "/v1/geoLocation")
                        .contentType("application/json").accept("application/json").content(objectMapper.writeValueAsString(GeoLocationPayload.builder().accuracy(12f)
                                        .address("Indore").bearing(23f).latitude(12.21).longitude(223.211).dateTime("02/09/2020 19:50:48").distance(12d).userId(1).build())))
                        .andExpect(status().isOk());
    }
}
