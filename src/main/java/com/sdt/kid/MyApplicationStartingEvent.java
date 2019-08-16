package com.sdt.kid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.boot.context.event.SpringApplicationEvent;
import org.springframework.context.ApplicationListener;


public class MyApplicationStartingEvent implements ApplicationListener<SpringApplicationEvent> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onApplicationEvent(SpringApplicationEvent event) {
        if (event instanceof ApplicationStartingEvent) {//启动之前
            logger.info("ApplicationStartingEvent...");
        } else if (event instanceof ApplicationReadyEvent) {//启动成功之后
            logger.info("ApplicationReadyEvent...");
        }
    }
}
