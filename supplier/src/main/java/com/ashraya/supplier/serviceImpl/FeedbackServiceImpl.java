package com.ashraya.supplier.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashraya.supplier.LoggerService;
import com.ashraya.supplier.constants.Constants;
import com.ashraya.supplier.constants.State;
import com.ashraya.supplier.domain.FeedbackPayload;
import com.ashraya.supplier.domain.OrderResponse;
import com.ashraya.supplier.domain.QuestionFeedbackPayload;
import com.ashraya.supplier.model.Questions;
import com.ashraya.supplier.model.SupplierFeedback;
import com.ashraya.supplier.model.WaterDistribution;
import com.ashraya.supplier.model.WaterSupplier;
import com.ashraya.supplier.repository.QuestionsRepository;
import com.ashraya.supplier.repository.SupplierFeedbackRepository;
import com.ashraya.supplier.repository.WaterDistributionRepository;
import com.ashraya.supplier.repository.WaterSupplierRepository;
import com.ashraya.supplier.service.FeedbackService;
import com.ashraya.supplier.util.OrderValidationUtil;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private WaterDistributionRepository waterDistributionRepository;

    @Autowired
    private WaterSupplierRepository waterSupplierRepository;

    @Autowired
    private SupplierFeedbackRepository supplierFeedbackRepository;

    @Autowired
    private QuestionsRepository questionsRepository;

    @Autowired
    private OrderValidationUtil orderValidationUtil;

    private LoggerService log = LoggerService.createLogger(OrderServiceImpl.class.getName());

    private OrderResponse buildOrderResponse(String status, String message) {
        return OrderResponse.builder().orderStatus(status).message(message).build();
    }

    @Override
    public List<Questions> getAllQuestion() {
        log.printStart("getAllQuestion");
        return questionsRepository.findQuestionsByState(State.active);
    }

    @Override
    public OrderResponse feedback(FeedbackPayload feedbackPayload) {
        log.printStart("feedback");
        orderValidationUtil.validateFeedback(feedbackPayload);
        List<SupplierFeedback> supplierFeedbacks = supplierFeedbackRepository.findByWaterDistributionIdOrWaterSupplierSupplierId(feedbackPayload.getOrderId(),
                        feedbackPayload.getUserId());
        if (null != supplierFeedbacks && !supplierFeedbacks.isEmpty()) {
            log.printEnd("feedback");
            return buildOrderResponse(Constants.ORDER_STATUS_FAIL, Constants.SUPPLIER_FEEDBACK_ALREADY);
        }
        WaterDistribution waterDistribution = waterDistributionRepository.findOne(feedbackPayload.getOrderId());
        if (null == waterDistribution) {
            log.printEnd("feedback");
            return buildOrderResponse(Constants.ORDER_STATUS_FAIL, Constants.ORDERID_NOT_FOUND);
        }
        WaterSupplier waterSupplier = waterSupplierRepository.findOne(feedbackPayload.getUserId());
        if (null == waterSupplier) {
            log.printEnd("feedback");
            return buildOrderResponse(Constants.ORDER_STATUS_FAIL, Constants.INVALID_USERID);
        }

        for (QuestionFeedbackPayload questionFeedbackPayload : feedbackPayload.getQuestionFeedbackPayload()) {
            if (null == questionFeedbackPayload.getId()) {
                log.printEnd("feedback");
                return buildOrderResponse(Constants.ORDER_STATUS_FAIL, "id " + Constants.NULL_MESSAGE);
            }
            Questions question = questionsRepository.findOne(questionFeedbackPayload.getId());
            if (null == question) {
                log.printEnd("feedback");
                return buildOrderResponse(Constants.ORDER_STATUS_FAIL, Constants.INVALID_QUETIONSID);
            }
            supplierFeedbackRepository.save(SupplierFeedback.builder().waterSupplier(waterSupplier).waterDistribution(waterDistribution).value(questionFeedbackPayload.getValue())
                            .questions(question).build());
        }
        log.printEnd("feedback");
        return buildOrderResponse(Constants.ORDER_STATUS, Constants.SUPPLIER_FEEDBACK_SUCCESS);
    }
}
