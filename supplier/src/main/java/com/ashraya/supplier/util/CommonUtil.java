package com.ashraya.supplier.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;
@Component
@Slf4j
public class CommonUtil {

    public Timestamp genrateCurrentTimestamp() throws ParseException {
        log.info(CommonUtil.class + ":: Starting genrateCurrentTimestamp");
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy, hh:mm:ss");
        String timeStamp = formatter.format(new Date());
        Date time = formatter.parse(timeStamp);
        Timestamp tism = new Timestamp(time.getTime());
        log.info(CommonUtil.class + ":: End genrateCurrentTimestamp");
        return tism;
    }
}
