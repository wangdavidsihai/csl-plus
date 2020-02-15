package com.csl.plus.portal;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan({"com.csl.plus.mapper", "com.csl.plus.ums.mapper", "com.csl.plus.marking.mapper", "com.csl.plus.cms.mapper", "com.csl.plus.sys.mapper", "com.csl.plus.oms.mapper", "com.csl.plus.pms.mapper", "com.csl.plus.rms.mapper", "com.csl.plus.inbox.mapper", "com.csl.plus.res.mapper"})
@EnableTransactionManagement
@EnableScheduling
public class CslPortalApplication {

    public static void main(String[] args) {

        SpringApplication.run(CslPortalApplication.class, args);
    }

}
