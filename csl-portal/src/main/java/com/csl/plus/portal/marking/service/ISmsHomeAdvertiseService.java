package com.csl.plus.portal.marking.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.plus.cms.entity.CmsSubject;
import com.csl.plus.marking.entity.SmsHomeAdvertise;
import com.csl.plus.oms.vo.HomeContentResult;
import com.csl.plus.pms.entity.PmsBrand;
import com.csl.plus.pms.entity.PmsProduct;

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
}
