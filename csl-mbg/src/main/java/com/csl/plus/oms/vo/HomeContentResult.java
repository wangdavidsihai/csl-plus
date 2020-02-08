package com.csl.plus.oms.vo;

import com.csl.plus.cms.entity.CmsArticle;
import com.csl.plus.cms.entity.CmsExpert;
import com.csl.plus.cms.entity.CmsNotice;
import com.csl.plus.marking.entity.SmsHomeAdvertise;
import com.csl.plus.pms.entity.PmsBrand;
import com.csl.plus.pms.entity.PmsProduct;
import com.csl.plus.pms.entity.PmsProductCategory;
import com.csl.plus.rms.entity.FinRequirementCategory;
import com.csl.plus.rms.entity.PrdRequirementCategory;
import com.csl.plus.rms.entity.TechRequirementCategory;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 首页内容返回信息封装
 */
@Getter
@Setter
public class HomeContentResult {
    // 轮播广告
    private List<SmsHomeAdvertise> advertiseList;
    private List<CmsArticle> cmsArticleList;
    private List<CmsNotice> cmsNoticeList;
    private List<CmsExpert> cmsExpertList;
    // 推荐品牌
    private List<PmsBrand> brandList;
    // 当前秒杀场次
    //private SmsFlashPromotion homeFlashPromotion;
    // 新品推荐
    private List<PmsProduct> newProductList;
    // 人气推荐
    //private List<PmsProduct> hotProductList;

    private List<PmsProductCategory> pmsProductCategoryList;

    private List<PrdRequirementCategory> prdRequirementCategoryList;

    private List<TechRequirementCategory> techRequirementCategoryList;

    private List<FinRequirementCategory> finRequirementCategoryList;
//	// 推荐专题
//	private List<CmsSubject> subjectList;

    //private List<PmsProductAttributeCategory> cat_list;
}
