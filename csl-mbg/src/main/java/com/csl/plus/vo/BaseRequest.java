package com.csl.plus.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class BaseRequest implements java.io.Serializable {
    @TableField(exist = false)
    private ServiceContext serviceContext;
    @TableField(exist = false)
    private ClientContext clientContext;

}
