package com.csl.plus.portal.marking.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.plus.cms.entity.CmsArticle;
import com.csl.plus.cms.entity.CmsNotice;
import com.csl.plus.cms.entity.CmsSubject;
import com.csl.plus.marking.entity.SmsHomeAdvertise;
import com.csl.plus.oms.vo.HomeContentResult;
import com.csl.plus.pms.entity.PmsBrand;
import com.csl.plus.pms.entity.PmsProduct;
import com.csl.plus.pms.entity.PmsProductCategory;
import com.csl.plus.res.entity.*;
import com.csl.plus.rms.entity.*;

import java.util.List;

/**
 * <p>
 * 首页轮播广告表 服务类
 * </p>
 *
 * @since 2019-04-19
 */
public interface ISmsHomeAdvertiseService extends IService<SmsHomeAdvertise> {

    HomeContentResult singelContent();

    List<PmsBrand> getRecommendBrandList(int pageNum, int pageSize);

    List<PmsProduct> getNewProductList(int pageNum, int pageSize);

    List<PmsProduct> getHotProductList(int pageNum, int pageSize);

    List<CmsSubject> getRecommendSubjectList(int pageNum, int pageSize);

    List<SmsHomeAdvertise> getHomeAdvertiseList();

    List<CmsArticle> getArticleList(int pageNum, int pageSize);

    List<CmsNotice> getNoticeList(int pageNum, int pageSize);

    List<PmsProductCategory> getPmsProductCategoryList(int pageNum, int pageSize);

    List<PrdRequirementCategory> getPrdRequirementCategoryList(int pageNum, int pageSize);

    List<PrdRequirement> getPrdRequirementList(int pageNum, int pageSize);

    List<TechRequirementCategory> getTechRequirementCategoryList(int pageNum, int pageSize);

    List<TechRequirement> getTechRequirementList(int pageNum, int pageSize);

    List<FinRequirementCategory> getFinRequirementCategoryList(int pageNum, int pageSize);

    List<FinRequirement> getFinRequirementList(int pageNum, int pageSize);

    List<ResFinanceCategory> getFinResourceCategoryList(int pageNum, int pageSize);

    List<ResFinance> getFinResourceList(int pageNum, int pageSize);

    List<ResTechnicalCategory> getTechResourceCategoryList(int pageNum, int pageSize);

    List<ResTechnical> getTechResourceList(int pageNum, int pageSize);

    List<ResProductCategory> getProdResourceCategoryList(int pageNum, int pageSize);

    List<ResProduct> getProdResourceList(int pageNum, int pageSize);

    List<ResProjCategory> getProjectResourceCategoryList(int pageNum, int pageSize);

    List<ResProjProduction> getProjectResourceList(int pageNum, int pageSize);

    List<ResExpert> getExpertList(int pageNum, int pageSize);
}
