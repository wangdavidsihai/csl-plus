package com.csl.plus.pms.vo;


import lombok.Data;

import java.util.List;
import java.util.Map;

import com.csl.plus.marking.entity.SmsGroupMember;
import com.csl.plus.pms.entity.PmsProduct;

/**
 * 创建和修改商品时使用的参数
 * https://github.com/shenzhuan/mallplus on 2018/4/26.
 */
@Data
public class PmsProductAndGroup extends PmsProduct {
    private Map<String, List<SmsGroupMember>> map;
    private String isGroup = "1"; //1 可以发起团购
    private String  is_favorite;
}
