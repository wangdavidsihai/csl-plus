package com.csl.plus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 应用启动入口
 * 
 */
@SpringBootApplication
@MapperScan({"com.csl.plus.mapper", "com.csl.plus.ums.mapper", "com.csl.plus.marking.mapper", "com.csl.plus.cms.mapper", "com.csl.plus.sys.mapper", "com.csl.plus.oms.mapper", "com.csl.plus.pms.mapper"})
@EnableTransactionManagement
public class CslAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(CslAdminApplication.class, args);
    }
}
