package com.ashraya.supplier.util;

import org.springframework.stereotype.Component;

import com.ashraya.supplier.constants.Constants;
import com.ashraya.supplier.domain.FeedbackPayload;
import com.ashraya.supplier.dto.DeliveryGeoLocationDto;
import com.ashraya.supplier.exception.ValidationException;

@Component
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

	public void validateFeedback(FeedbackPayload feedbackPayload) {
		if (feedbackPayload == null) {
			throw new ValidationException("feedbackPayload" + Constants.NULL_MESSAGE);
		}

		if (feedbackPayload.getOrderId() == null) {
			throw new ValidationException("OrderId" + Constants.NULL_MESSAGE);
		}

		if (feedbackPayload.getUserId() == null) {
			throw new ValidationException("UserId" + Constants.NULL_MESSAGE);
		}

		if (null == feedbackPayload.getQuestionFeedbackPayload()
				|| feedbackPayload.getQuestionFeedbackPayload().isEmpty()) {
			throw new ValidationException("QuestionFeedbackPayload" + Constants.NULL_MESSAGE);
		}
	}
}
