package com.epam.orderingsystem;

import com.epam.orderingsystem.processor.WishProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class OrderingSystemApplication {

    @Autowired
    private WishProcessor wishProcessor;
    @Value("${wishes.filename}")
    private String filename;

    public static void main(String[] args)
    {
        SpringApplication.run(OrderingSystemApplication.class, args);
    }

    /**
     * Parse wishes by schedule
     * @throws Exception when exception is thrown
     */
//    @Scheduled(cron = "0 0 2 * * *", zone = "UTC")
    @Scheduled(fixedDelay = 10000)
    private void parse() throws Exception
    {
        wishProcessor.process(filename);
    }
}
