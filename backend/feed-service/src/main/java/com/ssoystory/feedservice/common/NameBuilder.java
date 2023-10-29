package com.ssoystory.feedservice.common;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class NameBuilder {
    public static String build (String input ){
        String[] inputSplited = input.split("\\.");
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd_HHmmss");
        String formattedTime = currentTime.format(formatter);

        return formattedTime+inputSplited[1];
    }
}
