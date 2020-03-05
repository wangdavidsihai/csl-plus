package com.csl.plus.vo;

import lombok.Data;

@Data
public class ClientContext implements java.io.Serializable {
    private String channel;
    private String appVersion;
    private String deviceInfo;
}
