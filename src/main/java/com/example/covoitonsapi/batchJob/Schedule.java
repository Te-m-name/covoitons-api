package com.example.covoitonsapi.batchJob;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Schedule {

    @Scheduled( initialDelay = 10 * 1000, fixedDelay = 10 * 1000)
    public void writeCurrentTime() {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss sss");
        Date now = new Date();

        String nowString = df.format(now);

        System.out.println("Now is: "+ nowString);

    }
}
