package com.ashraya.supplier.service;

import java.util.List;

import com.ashraya.supplier.domain.FeedbackPayload;
import com.ashraya.supplier.domain.OrderResponse;
import com.ashraya.supplier.model.Questions;

public interface FeedbackService {

	public List<Questions> getAllQuestion();

	public OrderResponse feedback(FeedbackPayload feedbackPayload);

}
