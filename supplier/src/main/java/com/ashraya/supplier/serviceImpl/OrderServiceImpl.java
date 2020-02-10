package com.ashraya.supplier.serviceImpl;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashraya.supplier.LoggerService;
import com.ashraya.supplier.constants.Constants;
import com.ashraya.supplier.constants.DistributionStatus;
import com.ashraya.supplier.domain.OrderResponse;
import com.ashraya.supplier.model.WaterDistribution;
import com.ashraya.supplier.repository.WaterDistributionRepository;
import com.ashraya.supplier.service.OrderService;
import com.ashraya.supplier.util.CommonUtil;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private WaterDistributionRepository waterDistributionRepository;

    @Autowired
    private CommonUtil commonUtil;

    private LoggerService log = LoggerService.createLogger(OrderServiceImpl.class.getName());

    @Override
    public OrderResponse updateOrder(Integer bookingId, String status) throws ParseException {
        log.printStart("updateOrder");
        WaterDistribution waterDistribution = waterDistributionRepository.findOne(bookingId);
        if (null != waterDistribution) {
            if (waterDistribution.getDistributionStatus().equals(DistributionStatus.scheduled)) {
                waterDistribution.setDistributionStatus(DistributionStatus.inprogress);
                waterDistribution.setDateTime(commonUtil.genrateCurrentTimestamp());
                waterDistributionRepository.save(waterDistribution);
                log.printEnd("updateOrder");
                return buildOrderResponse(Constants.ORDER_STATUS, DistributionStatus.inprogress.name());
            } else {
                log.printEnd("updateOrder");
                return buildOrderResponse(Constants.ORDER_STATUS_FAIL, "oreder status already " + waterDistribution.getDistributionStatus().name());
            }
        } else {
            log.printEnd("updateOrder");
            return buildOrderResponse(Constants.ORDER_STATUS_FAIL, Constants.BOOKINGID_INVALID);
        }
    }

    private OrderResponse buildOrderResponse(String status, String message) {
        return OrderResponse.builder().orderStatus(status).message(message).build();
    }
}