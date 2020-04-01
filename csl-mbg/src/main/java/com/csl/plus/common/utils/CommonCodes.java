package com.csl.plus.common.utils;

/**
 * 常量
 */
public interface CommonCodes {
    // 帐号启用状态:0->关闭；1->审核中; 2->关闭; 3->暂停
    public static final int STATUS_ACTIVE = 1;
    public static final int STATUS_REVIEW = 0;
    public static final int STATUS_TERMINATED = 2;
    public static final int STATUS_SUSPECTED = 3;

    public static final String ALLOWED = "Y";
    public static final String NOT_ALLOWED = "N";

    public static final Integer INBOX_READ = 1;
    public static final Integer INBOX_UNREAD = 0;

    public static final String SYS_GROUP_RMS_FIN = "rms-fin";
    public static final String SYS_GROUP_RMS_TECH = "rms-tech";
    public static final String SYS_GROUP_RMS_PROD = "rms-prod";
    public static final String SYS_GROUP_RES_EXP = "res-exp";
    public static final String SYS_GROUP_RES_TAL = "res-tal";
    public static final String SYS_GROUP_RES_FIN = "res-fin";
    public static final String SYS_GROUP_RES_PROD = "res-prod";
    public static final String SYS_GROUP_RES_PROJ = "res-proj";
    public static final String SYS_GROUP_RES_TECH = "res-tech";

}
