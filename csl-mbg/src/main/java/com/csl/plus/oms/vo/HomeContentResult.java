package com.csl.plus.oms.vo;

import com.csl.plus.cms.entity.CmsArticle;
import com.csl.plus.cms.entity.CmsNotice;
import com.csl.plus.marking.entity.SmsHomeAdvertise;
import com.csl.plus.pms.entity.PmsBrand;
import com.csl.plus.pms.entity.PmsProduct;
import com.csl.plus.pms.entity.PmsProductCategory;
import com.csl.plus.res.entity.*;
import com.csl.plus.rms.entity.*;
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
    // 推荐品牌
    private List<PmsBrand> brandList;
    // 当前秒杀场次
    //private SmsFlashPromotion homeFlashPromotion;
    // 新品推荐
    private List<PmsProduct> newProductList;
    // 人气推荐
    //private List<PmsProduct> hotProductList;


    private List<PmsProductCategory> pmsProductCategoryList;

    private List<PrdRequirementCategory> prdRequirementCategories;
    private List<PrdRequirement> prdRequirementLis;
    private List<TechRequirementCategory> techRequirementCategories;
    private List<TechRequirement> techRequirementList;
    private List<FinRequirementCategory> finRequirementCategories;
    private List<FinRequirement> finRequirementList;

    //Resources start

    private List<ResFinanceCategory> finResourceCategories;
    private List<ResFinance> finResourceList;
    private List<ResTechnicalCategory> techResourceCategories;
    private List<ResTechnical> techResourceList;
    private List<ResProductCategory> prodResourceCategories;
    private List<ResProduct> prodResourceList;
    private List<ResProjCategory> projectResourceCategories;
    private List<ResProjProduction> projectResourceList;
    private List<ResExpert> expertResourceList;
    private List<ResTalent> talentResourceList;

    //Resources end
}
