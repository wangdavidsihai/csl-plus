package com.csl.plus.res.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author David
 * @email
 * @date 2020-02-29 12:55:20
 */
@Data
@TableName("res_finance_company")
public class ResFinanceCompany implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     *
     */
    private String name;
    /**
     *
     */
    private String subname;
    /**
     *
     */
    private String status;
    /**
     *
     */
    private Date createDate;
    /**
     *
     */
    private String info;
    /**
     *
     */
    private Date updateDate;

}
