package com.ashraya.supplier.serviceImpl;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashraya.supplier.constants.Constants;
import com.ashraya.supplier.constants.DistributionStatus;
import com.ashraya.supplier.domain.OrderResponse;
import com.ashraya.supplier.model.WaterDistribution;
import com.ashraya.supplier.repository.WaterDistributionRepository;
import com.ashraya.supplier.service.OrderService;
import com.ashraya.supplier.util.CommonUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private WaterDistributionRepository waterDistributionRepository;

    @Autowired
    private CommonUtil commonUtil;

    @Override
    public OrderResponse updateOrder(Integer bookingId, String status) throws ParseException {
        log.info(OrderServiceImpl.class + ":: Starting updateOrder");
        WaterDistribution waterDistribution = waterDistributionRepository.findOne(bookingId);
        if (null != waterDistribution) {
            if (waterDistribution.getDistributionStatus().equals(DistributionStatus.scheduled)) {
                waterDistribution.setDistributionStatus(DistributionStatus.inprogress);
                waterDistribution.setDateTime(commonUtil.genrateCurrentTimestamp());
                waterDistributionRepository.save(waterDistribution);
                log.info(OrderServiceImpl.class + "::End updateOrder");
                return OrderResponse.builder().Orderstatus(Constants.ORDER_STATUS).status(DistributionStatus.inprogress.name()).build();
            } else {
                log.info(OrderServiceImpl.class + ":: End updateOrder");
                return OrderResponse.builder().Orderstatus(Constants.ORDER_STATUS_FAIL).status("oreder status already " + waterDistribution.getDistributionStatus().name()).build();
            }
        } else {
            log.info(OrderServiceImpl.class + ":: End updateOrder");
            return OrderResponse.builder().Orderstatus(Constants.ORDER_STATUS_FAIL).status(Constants.BOOKINGID_INVALID).build();
        }
    }

}
