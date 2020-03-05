package com.csl.plus.vo;

import lombok.Data;

@Data
public class ServiceContext implements java.io.Serializable {

    private String serviceId;
    private String token;
    private String nonce;
    private String otpToken;
}
