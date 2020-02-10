package com.ashraya.supplier.controllerTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.ashraya.supplier.constants.Constants;
import com.ashraya.supplier.constants.DistributionStatus;
import com.ashraya.supplier.controller.OrderController;
import com.ashraya.supplier.domain.OrderResponse;
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

        mockMvc.perform(MockMvcRequestBuilders.get(Constants.SUPPLIER + "/v1/updateOrder/10/scheduled").contentType("application/json").accept("application/json").content(
                        objectMapper.writeValueAsString(OrderResponse.builder().orderStatus(Constants.ORDER_STATUS).message(DistributionStatus.inprogress.name()).build())))
                        .andExpect(status().isOk());
    }
}
