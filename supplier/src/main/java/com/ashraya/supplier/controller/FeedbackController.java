package com.ashraya.supplier.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ashraya.supplier.LoggerService;
import com.ashraya.supplier.constants.Constants;
import com.ashraya.supplier.domain.FeedbackPayload;
import com.ashraya.supplier.domain.OrderResponse;
import com.ashraya.supplier.model.Questions;
import com.ashraya.supplier.service.FeedbackService;

@RestController
@RequestMapping(Constants.SUPPLIER)
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    private LoggerService log = LoggerService.createLogger(FeedbackController.class.getName());

    @GetMapping(value = Constants.VERSION + "/getQuestion")
    public List<Questions> getAllQuestion() {
        log.printStart("getAllQuestion");
        return feedbackService.getAllQuestion();
    }

    @PostMapping(value = Constants.VERSION + "/feedback")
    public OrderResponse feedback(@RequestBody FeedbackPayload feedbackPayload) {
        log.printStart("feedback");
        return feedbackService.feedback(feedbackPayload);
    }
}
