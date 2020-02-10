package com.ashraya.supplier.controllerTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.ashraya.supplier.constants.Constants;
import com.ashraya.supplier.controller.FeedbackController;
import com.ashraya.supplier.service.FeedbackService;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(secure = false)
public class FeedbackControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    FeedbackService feedbackService;

    @InjectMocks
    FeedbackController feedbackController;

    @Test
    public void whenValidInputForGetQuetions_thenReturns200() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(MockMvcRequestBuilders.get(Constants.SUPPLIER + Constants.VERSION + "/getQuestion")
                        .content(objectMapper.writeValueAsString(FeedbackControllerTestData.getAllQuetions()))).andExpect(status().isOk());
    }

    @Test
    public void whenValidInputForFeedback_thenReturns200() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(MockMvcRequestBuilders.post(Constants.SUPPLIER + Constants.VERSION + "/feedback").contentType("application/json")
                        .content(objectMapper.writeValueAsString(FeedbackControllerTestData.feedback()))).andExpect(status().isOk());
    }
}
