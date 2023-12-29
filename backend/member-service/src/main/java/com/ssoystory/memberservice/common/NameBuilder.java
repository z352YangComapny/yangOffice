package com.ssoystory.memberservice.common;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Component
public class NameBuilder {
    public static String build (String input ){
        String[] inputSplited = input.split("\\.");
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd_HHmmss");
        String formattedTime = currentTime.format(formatter);
        Random rand = new Random();
        int randomValue = rand.nextInt(1000); // 0 이상 999 이하의 난수 생성
        String formattedRandomValue = String.format("%03d", randomValue);

        return formattedTime+"_"+formattedRandomValue+"."+inputSplited[1];
    }
}
