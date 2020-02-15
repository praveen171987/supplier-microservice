package com.ashraya.supplier.serviceImpl;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashraya.supplier.LoggerService;
import com.ashraya.supplier.constants.Constants;
import com.ashraya.supplier.constants.DistributionStatus;
import com.ashraya.supplier.domain.GeoLocationPayload;
import com.ashraya.supplier.domain.OrderResponse;
import com.ashraya.supplier.domain.UpdateOrderPayload;
import com.ashraya.supplier.model.GeoLocation;
import com.ashraya.supplier.model.WaterDistribution;
import com.ashraya.supplier.model.WaterSupplier;
import com.ashraya.supplier.repository.GeoLocationRepository;
import com.ashraya.supplier.repository.WaterDistributionRepository;
import com.ashraya.supplier.repository.WaterSupplierRepository;
import com.ashraya.supplier.service.OrderService;
import com.ashraya.supplier.util.CommonUtil;
import com.ashraya.supplier.util.OrderValidationUtil;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private WaterDistributionRepository waterDistributionRepository;

    @Autowired
    private WaterSupplierRepository waterSupplierRepository;

    @Autowired
    private GeoLocationRepository geoLocationRepository;

    @Autowired
    private OrderValidationUtil orderValidationUtil;

    @Autowired
    private CommonUtil commonUtil;

    private LoggerService log = LoggerService.createLogger(OrderServiceImpl.class.getName());

    private OrderResponse buildOrderResponse(String status, String message) {
        return OrderResponse.builder().orderStatus(status).message(message).build();
    }

    @Override
    public OrderResponse updateOrder(UpdateOrderPayload updateOrderPayload) throws ParseException {
        log.printStart("updateOrder");
        orderValidationUtil.validateUpdateOrder(updateOrderPayload);
        GeoLocationPayload geoLocationPayload = updateOrderPayload.getGeoLocation();
        WaterSupplier waterSupplier = waterSupplierRepository.findOne(updateOrderPayload.getSupplierId());
        if (null == waterSupplier) {
            return buildOrderResponse(Constants.ORDER_STATUS_FAIL, Constants.INVALID_SUPPLIRIED + updateOrderPayload.getSupplierId());
        }
        WaterDistribution waterDistribution = waterDistributionRepository.findByIdAndWaterSupplierSupplierId(updateOrderPayload.getBookingId(), updateOrderPayload.getSupplierId());

        if (null != waterDistribution) {
            if (waterDistribution.getDistributionStatus().equals(DistributionStatus.scheduled)) {
                waterDistribution.setDistributionStatus(DistributionStatus.inprogress);
                waterDistribution.setDateTime(commonUtil.covertStringToTimestamp(updateOrderPayload.getDateTime()));
                if (null != geoLocationPayload) {
                    GeoLocation geoLocation = geoLocationRepository.findByWaterSupplierSupplierId(waterSupplier.getSupplierId());
                    if (geoLocation == null) {
                        geoLocation = new GeoLocation();
                    }
                    geoLocation.setAccuracy(geoLocationPayload.getAccuracy());
                    geoLocation.setAddress(geoLocationPayload.getAddress());
                    geoLocation.setBearing(geoLocationPayload.getBearing());
                    geoLocation.setDistance(geoLocationPayload.getDistance());
                    geoLocation.setLatitude(geoLocationPayload.getLatitude());
                    geoLocation.setLongitude(geoLocationPayload.getLongitude());
                    geoLocation.setProvider(geoLocationPayload.getProvider());
                    geoLocation.setSpeed(geoLocationPayload.getSpeed());
                    geoLocation.setDateTime(commonUtil.covertStringToTimestamp(updateOrderPayload.getDateTime()));
                    geoLocation = geoLocationRepository.save(geoLocation);
                    waterDistribution.setGeoLocation(geoLocation);
                }
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

    @Override
    public OrderResponse saveGeoloaction(GeoLocationPayload geoLocationPayload) throws ParseException {
        log.printStart("saveGeoloaction");
        orderValidationUtil.validateGeolocation(geoLocationPayload);
        WaterSupplier waterSupplier = waterSupplierRepository.findOne(geoLocationPayload.getUserId());
        if (waterSupplier == null) {
            return buildOrderResponse(Constants.ORDER_STATUS_FAIL, Constants.INVALID_SUPPLIRIED + geoLocationPayload.getUserId());
        }
        GeoLocation geoLocation = geoLocationRepository.findByWaterSupplierSupplierId(geoLocationPayload.getUserId());
        if (null == geoLocation) {
            geoLocationRepository.save(GeoLocation.builder().accuracy(geoLocationPayload.getAccuracy()).address(geoLocationPayload.getAddress())
                            .bearing(geoLocationPayload.getBearing()).dateTime(commonUtil.covertStringToTimestamp(geoLocationPayload.getDateTime()))
                            .speed(geoLocationPayload.getSpeed()).latitude(geoLocationPayload.getLatitude()).longitude(geoLocationPayload.getLongitude())
                            .provider(geoLocationPayload.getProvider()).waterSupplier(waterSupplier).build());
            log.printEnd("saveGeoloaction");
            return buildOrderResponse(Constants.ORDER_STATUS, Constants.GEOLOCATION_SAVED);
        } else {
            geoLocation.setAccuracy(geoLocationPayload.getAccuracy());
            geoLocation.setAddress(geoLocationPayload.getAddress());
            geoLocation.setBearing(geoLocationPayload.getBearing());
            geoLocation.setDistance(geoLocationPayload.getDistance());
            geoLocation.setLatitude(geoLocationPayload.getLatitude());
            geoLocation.setLongitude(geoLocationPayload.getLongitude());
            geoLocation.setProvider(geoLocationPayload.getProvider());
            geoLocation.setSpeed(geoLocationPayload.getSpeed());
            geoLocation.setWaterSupplier(waterSupplier);
            geoLocation.setDateTime(commonUtil.covertStringToTimestamp(geoLocationPayload.getDateTime()));
            geoLocationRepository.save(geoLocation);
            log.printEnd("saveGeoloaction");
            return buildOrderResponse(Constants.ORDER_STATUS, Constants.GEOLOCATION_UPDATED);
        }
    }
}