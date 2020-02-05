package com.ashraya.supplier.util;

import com.ashraya.supplier.constants.Constants;
import com.ashraya.supplier.dto.DeliveryGeoLocationDto;
import com.ashraya.supplier.exception.ValidationException;

public class OrderValidationUtil {

    public static DeliveryGeoLocationDto validateGeoLocationParams(DeliveryGeoLocationDto deliveryGeoLocationDto) {
        if (deliveryGeoLocationDto == null) {
            throw new ValidationException("GeoLocation" + Constants.NULL_MESSAGE);
        }
        if (deliveryGeoLocationDto.getLatitute() == null) {
            throw new ValidationException("Latitute" + Constants.NULL_MESSAGE);
        }
        if (deliveryGeoLocationDto.getLongitute() == null) {
            throw new ValidationException("Longitute" + Constants.NULL_MESSAGE);
        }
        if (deliveryGeoLocationDto.getRadius() == 0) {
            throw new ValidationException("Radius" + Constants.NULL_MESSAGE);
        }
        return deliveryGeoLocationDto;
    }
}
