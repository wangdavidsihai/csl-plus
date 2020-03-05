package com.csl.plus.vo;

import lombok.Data;

@Data
public class BaseRequest implements java.io.Serializable {
    private ServiceContext serviceContext;
    private ClientContext clientContext;

}
