package com.ashraya.supplier.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Component;

import com.ashraya.supplier.constants.Constants;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CommonUtil {

    public Timestamp covertStringToTimestamp(String dateTime) throws ParseException {
        log.info(CommonUtil.class + ":: Starting covertStringToTimestamp");
        Timestamp timestamp = null;
        if (dateTime != null && !dateTime.isEmpty()) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.DATEANDTIME_FORMAT);
            Date parsedDate = dateFormat.parse(dateTime);
            timestamp = new Timestamp(parsedDate.getTime());
        }
        log.info(CommonUtil.class + ":: End covertStringToTimestamp");
        return timestamp;
    }

    public static int generateOTP() {
        Random random = new Random();
        return 1000 + random.nextInt(9000);
    }
}
