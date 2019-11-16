package com.csl.plus.oms.vo;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

import com.csl.plus.cms.entity.CmsSubject;
import com.csl.plus.marking.entity.SmsFlashPromotion;
import com.csl.plus.marking.entity.SmsHomeAdvertise;
import com.csl.plus.pms.entity.PmsBrand;
import com.csl.plus.pms.entity.PmsProduct;
import com.csl.plus.pms.entity.PmsProductAttributeCategory;

/**
 * 首页内容返回信息封装
 * https://github.com/shenzhuan/mallplus on 2019/1/28.
 */
@Getter
@Setter
public class HomeContentResult {
    //轮播广告
    private List<SmsHomeAdvertise> advertiseList;
    //推荐品牌
    private List<PmsBrand> brandList;
    //当前秒杀场次
    private SmsFlashPromotion homeFlashPromotion;
    //新品推荐
    private List<PmsProduct> newProductList;
    //人气推荐
    private List<PmsProduct> hotProductList;
    //推荐专题
    private List<CmsSubject> subjectList;

    private List<PmsProductAttributeCategory> cat_list;
}
