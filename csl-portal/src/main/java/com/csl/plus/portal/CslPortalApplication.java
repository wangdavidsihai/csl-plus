package com.csl.plus.portal;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan({"com.csl.plus.mapper", "com.csl.plus.*.mapper"})
@EnableTransactionManagement
@EnableScheduling
public class CslPortalApplication {

    public static void main(String[] args) {

        SpringApplication.run(CslPortalApplication.class, args);
    }

}
