package com.magnoliaory.scullicommunicationserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.magnoliaory.scullientityoperation",
        "com.magnoliaory.scullicommunicationserver"})
public class SculliCommunicationServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SculliCommunicationServerApplication.class, args);
    }

}
