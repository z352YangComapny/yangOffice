package com.ssoystory.s3service.common;

import org.springframework.stereotype.Component;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

@Component
public class NameBuilder {
    public static String build (String input, String authName){
        String[] inputSplited = input.split("\\.");
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd_HHmmss");
        String formattedTime = currentTime.format(formatter);

        return authName+"_"+formattedTime+inputSplited[1];
    }
}
