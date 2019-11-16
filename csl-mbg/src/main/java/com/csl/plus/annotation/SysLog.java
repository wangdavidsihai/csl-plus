package com.csl.plus.annotation;

import java.lang.annotation.*;

/**
 * 系统日志注解
 *
 * @date 2017年3月8日 上午10:19:56
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

    String MODULE() default "操作模块";
    String REMARK() default "操作日志";
}
